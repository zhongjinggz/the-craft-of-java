package refactoring.vcdstore;

public class Rental {

    private final Movie movie;
    private final int daysRented;
    private double amount;

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
}
