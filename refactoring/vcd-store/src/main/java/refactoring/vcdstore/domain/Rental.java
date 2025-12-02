package refactoring.vcdstore.domain;

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

    @Override
    public String toString() {
        return "Rental{" +
            "movie=" + movie +
            ", daysRented=" + daysRented +
            '}';
    }

    public double getAmount() {
        return amount;
    }

    // 计算常客积点
    int calFrequentPoints() {

        this.frequentPoints = movie.calFrequentPoints(daysRented);
        return this.frequentPoints;
    }

    double calAmount() {
        this.amount = movie.calAmount(daysRented);
        return this.amount;
    }

}
