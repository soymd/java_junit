package tddbc;

public class CloseSection {
    //変数名はlower, upperが用語としては正しいらしい
    private int bottom;
    private int top;

    public CloseSection(int bottom, int top) {
        //不等号の向きはプロジェクトで統一したほうが良い
        if (bottom > top) throw new IllegalArgumentException("上端点より下端点が大きい閉区間は作れない");

        this.bottom = bottom;
        this.top = top;
    }

    public String stringify() {
        return String.format("[%s, %s]", bottom, top);
    }

    //includeとcontainsの意味が似ていてわかりにくい
    public boolean includes(int num) {
        return bottom <= num && num <= top;
    }

    public boolean equals(CloseSection target) {
        return this.stringify().equals(target.stringify());
    }

    public boolean contains(CloseSection check) {
        //不等号の向き
        return this.bottom <= check.bottom && this.top >= check.top;
    }
}
