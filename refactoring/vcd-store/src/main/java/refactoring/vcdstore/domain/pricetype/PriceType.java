package refactoring.vcdstore.domain.pricetype;

public abstract class PriceType {
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int CHILDREN = 2;
    protected int code;

    public PriceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract double calAmount(int daysRented);

    public abstract int calFrequentPoints(int daysRented);
}
