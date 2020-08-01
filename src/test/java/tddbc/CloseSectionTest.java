package tddbc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

@RunWith(Enclosed.class)
public class CloseSectionTest {
    /*
    [x] 下端点と上端点を持つ(仕様)
        [x] 文字列表現も返せる（テスト対象）
            [x] [3.8   ] -> “[3.8]”
            [x] [2,9   ] -> “[2,9]”
        [x] 上端点より下端点が大きい閉区間は作れない（テスト対象）
            [x] [9,2] -> Exception
    [ ] 指定した整数が閉区間に含まれているか判定できる
        [ ] [3.8]で5は含まれる（テスト対象）
        [ ] [3.8]で9は含まれない（テスト対象）
    [ ] 2つの閉区間は等価
        [ ] [3.8]と[3.8]は等価
        [ ] [3.8]と[3,9]は等価ではない
    [ ] 閉区間が閉鎖区間を含む
        [ ] [3.8]に[2,7]は含まない
        [ ] [3.8]に[4.9]は含まない
        [ ] [3.8]に[3.7]は含まれる
     */

    public static class 下端点と上端点を持つ {
        @Test
        public void 下端と上端を表す文字列を返す() throws Exception {
            // Setup
            CloseSection closeSection = new CloseSection(3, 8);
            // Exercise
            String actual = closeSection.strigify();
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
            CloseSection closeSection = new CloseSection(9, 2);
        }

    }
}
