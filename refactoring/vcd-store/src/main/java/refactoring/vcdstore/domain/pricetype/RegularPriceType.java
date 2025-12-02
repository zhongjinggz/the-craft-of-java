package refactoring.vcdstore.domain.pricetype;

public class RegularPriceType extends PriceType {

    public static final int CODE = 0;

    private static final int BASE_PRICE = 2;
    private static final int AMOUNT_THRESHOLD = 2;
    private static final double UNIT_PRICE = 1.5;
    private static final int DEFAULT_POINTS = 1;

    public RegularPriceType() {
        super(CODE);
    }

    @Override
    public double calAmount(int daysRented) {
        double amount = BASE_PRICE;
        if (daysRented > AMOUNT_THRESHOLD)
            amount += (daysRented - AMOUNT_THRESHOLD) * UNIT_PRICE;
        return amount;
    }

    @Override
    public int calFrequentPoints(int daysRented) {
        return DEFAULT_POINTS;
    }
}
