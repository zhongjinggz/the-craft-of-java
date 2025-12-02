package refactoring.vcdstore;

public class ChildrenPriceType extends PriceType {

    public ChildrenPriceType(int code) {
        super(code);
    }

    @Override
    double calAmount(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3) {
            amount += (daysRented - 3) * 1.5;
        }
        return amount;
    }

    @Override
    int calFrequentPoints(int daysRented) {
        return 1;
    }
}
