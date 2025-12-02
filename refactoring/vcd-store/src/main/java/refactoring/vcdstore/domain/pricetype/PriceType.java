package refactoring.vcdstore.domain.pricetype;

public abstract class PriceType {
    protected final int code;

    public PriceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract double calAmount(int daysRented);

    public abstract int calFrequentPoints(int daysRented);
}
