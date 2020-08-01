package tddbc;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SampleTest {
    /*
    [ ] 整数閉区間を示すクラス
        [ ] 下端点と上端点を持つ(仕様)
        [ ] 文字列表現も返せる（テスト対象）
            [ ] [3.8   ] -> “[3.8]”
        [ ] 上端点より下端点が大きい閉区間は作れない（テスト対象）
            [ ] [7,4] -> Exception
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

    @Test
    public void 文字列表現を返す() throws Exception {
        // Setup
        CloseSection closeSection = new CloseSection();
        // Exercise
        String actual = closeSection.strigify();
        // Verify
        assertThat(actual, equalTo("[3, 8]"));
    }
}
