package refactoring.vcdstore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StatementServiceTest {

    private Customer customer;
    private Movie regularMovie;
    private Movie newReleaseMovie;
    private Movie childrenMovie;
    private StatementService statementService;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe");
        regularMovie = new Movie("Regular Movie", Movie.REGULAR);
        newReleaseMovie = new Movie("New Release Movie", Movie.NEW_RELEASE);
        childrenMovie = new Movie("Children Movie", Movie.CHILDREN);
        statementService = new StatementService();
    }

    @Test
    public void should_print_statement_without_rentals() {
        String result = statementService.printStatement(customer);

        assertTrue(result.contains("Rental Record for John Doe"));
        assertTrue(result.contains("Amount owed is 0.0"));
        assertTrue(result.contains("You earned 0 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_regular_movie_under_two_days() {
        customer.addRental(new Rental(regularMovie, 1));
        String result = statementService.printStatement(customer);

        // Regular movie: 2 yuan for first 2 days
        assertTrue(result.contains("Regular Movie\t2.0"));
        assertTrue(result.contains("Amount owed is 2.0"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_regular_movie_over_two_days() {
        customer.addRental(new Rental(regularMovie, 4));
        String result = statementService.printStatement(customer);

        // Regular movie: 2 + (4-2)*1.5 = 5.0 yuan
        assertTrue(result.contains("Regular Movie\t5.0"));
        assertTrue(result.contains("Amount owed is 5.0"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_new_release_movie() {
        customer.addRental(new Rental(newReleaseMovie, 3));
        String result = statementService.printStatement(customer);

        // New release: 3 * 3 = 9.0 yuan
        assertTrue(result.contains("New Release Movie\t9.0"));
        assertTrue(result.contains("Amount owed is 9.0"));
        // Bonus point because it's a new release rented for more than 1 day
        assertTrue(result.contains("You earned 2 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_children_movie_under_three_days() {
        customer.addRental(new Rental(childrenMovie, 2));
        String result = statementService.printStatement(customer);

        // Children movie: 1.5 yuan for first 3 days
        assertTrue(result.contains("Children Movie\t1.5"));
        assertTrue(result.contains("Amount owed is 1.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_children_movie_over_three_days() {
        customer.addRental(new Rental(childrenMovie, 5));
        String result = statementService.printStatement(customer);

        // Children movie: 1.5 + (5-3)*1.5 = 4.5 yuan
        assertTrue(result.contains("Children Movie\t4.5"));
        assertTrue(result.contains("Amount owed is 4.5"));
        assertTrue(result.contains("You earned 1 frequent renter points"));
    }

    @Test
    public void should_print_statement_with_multiple_movies() {
        customer.addRental(new Rental(regularMovie, 1));
        customer.addRental(new Rental(newReleaseMovie, 1));
        customer.addRental(new Rental(childrenMovie, 1));
        String result = statementService.printStatement(customer);

        assertTrue(result.contains("Regular Movie\t2.0"));
        assertTrue(result.contains("New Release Movie\t3.0"));
        assertTrue(result.contains("Children Movie\t1.5"));
        // Total: 2 + 3 + 1.5 = 6.5
        assertTrue(result.contains("Amount owed is 6.5"));
        // 1 point for each rental = 3 points
        assertTrue(result.contains("You earned 3 frequent renter points"));
    }
}
