package tddbc;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class CloseSectionTest {
    /*
    [x] 下端点と上端点を持つ(仕様)
        [x] 文字列表現も返せる（テスト対象）
            [x] [3.8   ] -> “[3.8]”
            [x] [2,9   ] -> “[2,9]”
        [x] 上端点より下端点が大きい閉区間は作れない（テスト対象）
            [x] [9,2] -> Exception
    [x] 指定した整数が閉区間に含まれているか判定できる
        [x] [3.8]で5は含まれる（テスト対象）
        [x] [3.8]で9は含まれない（テスト対象）
    [x] 2つの閉区間は等価
        [x] [3.8]と[3.8]は等価
        [x] [3.8]と[3,9]は等価ではない
    [x] 閉区間が閉区間を含む
        [x] [3.8]に[3.7]は含まれる
        [x] [3.8]に[2,7]は含まない
        [x] [3.8]に[4.9]は含まない
     */

    public static class 下端点と上端点を持つ {
        private CloseSection closeSection;

        @Test
        public void 下端と上端を表す文字列を返す() throws Exception {
            // Setup
            closeSection = new CloseSection(3, 8);
            // Exercise
            String actual = closeSection.stringify();
            // Verify
            assertThat(actual, equalTo("[3, 8]"));
        }

        @Rule
        public ExpectedException thrown = ExpectedException.none();

        @Test
        public void 上端点より下端点が大きい閉区間は作れない() {
            // Setup
            thrown.expect(IllegalArgumentException.class);
            thrown.expectMessage("上端点より下端点が大きい閉区間は作れない");

            // Exercise
            closeSection = new CloseSection(9, 2);
        }
    }

    public static class 指定した整数が閉区間に含まれているか判定できる {
        private CloseSection closeSection;

        @Before
        public void setUp() {
            closeSection = new CloseSection(3, 8);
        }

        @Test
        public void 下端点以上かつ上端点以下の整数は閉区間に含まれる() {
            // Exercise
            boolean actual = closeSection.included(5);
            // Verify
            assertThat(actual, equalTo(true));
        }

        @Test
        public void 上端点より大きい整数は閉区間に含まれない() {
            // Exercise
            boolean actual = closeSection.included(9);
            // Verify
            assertThat(actual, equalTo(false));
        }

        @Test
        public void 下端点より小さい整数は閉区間に含まれない() {
            // Exercise
            boolean actual = closeSection.included(2);
            // Verify
            assertThat(actual, equalTo(false));
        }

        @Test
        public void 下端点と同じ整数は閉区間に含まれる() {
            // Exercise
            boolean actual = closeSection.included(3);
            // Verify
            assertThat(actual, equalTo(true));
        }

        @Test
        public void 上端点と同じ整数は閉区間に含まれる() {
            // Exercise
            boolean actual = closeSection.included(8);
            // Verify
            assertThat(actual, equalTo(true));
        }
    }

    public static class _2つの閉区間は等価か比較できる {
        private CloseSection closeSection;

        @Before
        public void setUp() {
            closeSection = new CloseSection(3, 8);
        }

        @Test
        public void _2つの閉区間は等価である() {
            CloseSection check = new CloseSection(3, 8);
            boolean actual = closeSection.equals(check);
            assertThat(actual, equalTo(true));
        }

        @Test
        public void _2つの閉区間は等価ではない() {
            CloseSection check = new CloseSection(2, 8);
            boolean actual = closeSection.equals(check);
            assertThat(actual, equalTo(false));
        }
    }

    public static class 閉区間が閉区間を含む {
        private CloseSection closeSection;

        @Before
        public void setup() {
            closeSection = new CloseSection(3, 8);
        }

        @Test
        public void _3_8は3_7を含む() {
            CloseSection check = new CloseSection(3, 7);
            boolean actual = closeSection.contains(check);
            assertThat(actual, equalTo(true));
        }

        @Test
        public void _3_8は2_7を含まない() {
            CloseSection check = new CloseSection(2, 7);
            boolean actual = closeSection.contains(check);
            assertThat(actual, equalTo(false));
        }

        @Test
        public void _3_8は3_9を含まない() {
            CloseSection check = new CloseSection(3, 9);
            boolean actual = closeSection.contains(check);
            assertThat(actual, equalTo(false));
        }
    }
}
