package refactoring.vcdstore;

public abstract class AbstractPriceType {
    protected int code;

    public AbstractPriceType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    abstract double calAmount(int daysRented);

    abstract int calFrequentPoints(int daysRented);
}
