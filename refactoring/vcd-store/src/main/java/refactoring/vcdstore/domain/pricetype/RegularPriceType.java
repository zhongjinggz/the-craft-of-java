package refactoring.vcdstore.domain.pricetype;

public class RegularPriceType extends PriceType {

    public RegularPriceType(int code) {
        super(code);
    }

    @Override
    public double calAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    @Override
    public int calFrequentPoints(int daysRented) {
        return 1;
    }
}
