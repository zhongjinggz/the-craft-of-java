package refactoring.vcdstore.domain;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    private final String name;
    private final List<Rental> rentals;
    private double amount;

    public int getFrequentPoints() {
        return frequentPoints;
    }

    public double getAmount() {
        return amount;
    }

    private int frequentPoints;

    public Customer(String name) {
        this.name = name;
        rentals = new ArrayList<>();
    }

    public void addRental(Rental rental) {
        this.rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", rentalList=" + rentals +
                '}';
    }

    public void calRentals() {
        this.amount = 0; // 总消费金。
        this.frequentPoints = 0; // 常客积点

        for (Rental rental : getRentals()) {
            this.amount += rental.calAmount();
            this.frequentPoints += rental.calFrequentPoints();
        }
    }
}
