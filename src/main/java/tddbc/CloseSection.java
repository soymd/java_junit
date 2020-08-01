package tddbc;

public class CloseSection {
    private int bottom;
    private int top;

    public CloseSection(int bottom, int top) {
        if(bottom > top) throw new IllegalArgumentException("hoge");

        this.bottom = bottom;
        this.top = top;
    }

    public String strigify() {
        return  String.format("[%s, %s]", bottom, top);
    }
}
