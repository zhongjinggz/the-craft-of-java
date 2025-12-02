package refactoring.vcdstore;

public class Rental {

    private final Movie movie;
    private final int daysRented;
    private double amount;
    private int frequentPoints;

    public Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getDaysRented() {
        return daysRented;
    }

    @Override
    public String toString() {
        return "Rental{" +
            "movie=" + movie +
            ", daysRented=" + daysRented +
            '}';
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    // 计算常客积点
    int calFrequentPoints() {
        int thisPoints = 1;

        // add bonus for a two days new release rental
        if ((getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
            getDaysRented() > 1) {
            thisPoints++;
        }

       this.frequentPoints = thisPoints;
        return thisPoints;
    }

    double calAmount() {
        double thisAmount = 0;

        // 取得影片出租价格
        switch (getMovie().getPriceCode()) {
            // 普通片
            case Movie.REGULAR:
                thisAmount += 2;
                if (getDaysRented() > 2)
                    thisAmount += (getDaysRented() - 2) * 1.5;
                break;
            // 新片
            case Movie.NEW_RELEASE:
                thisAmount += getDaysRented() * 3;
                break;
            // 儿童片
            case Movie.CHILDREN:
                thisAmount += 1.5;
                if (getDaysRented() > 3)
                    thisAmount += (getDaysRented() - 3) * 1.5;
                break;
        }
        this.amount = thisAmount;
        return thisAmount;
    }
}
