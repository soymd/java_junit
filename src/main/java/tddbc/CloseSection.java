package tddbc;

public class CloseSection {
    private int bottom;
    private int top;

    public CloseSection(int bottom, int top) {
        if (bottom > top) throw new IllegalArgumentException("上端点より下端点が大きい閉区間は作れない");

        this.bottom = bottom;
        this.top = top;
    }

    public String stringify() {
        return String.format("[%s, %s]", bottom, top);
    }

    public boolean included(int num) {
        return bottom <= num && num <= top;
    }

    public boolean equals(CloseSection target) {
        return this.stringify().equals(target.stringify());
    }

    public boolean contains(CloseSection check) {
        return this.bottom <= check.bottom && this.top >= check.top;
    }
}
