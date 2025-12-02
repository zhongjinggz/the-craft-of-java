package refactoring.vcdstore;

public class RegularPriceType extends PriceType {

    public RegularPriceType(int code) {
        super(code);
    }

    @Override
    double calAmount(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return amount;
    }

    @Override
    int calFrequentPoints(int daysRented) {
        return 1;
    }
}
