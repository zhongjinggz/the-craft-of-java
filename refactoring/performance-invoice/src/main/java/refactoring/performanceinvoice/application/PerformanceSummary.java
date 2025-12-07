package refactoring.performanceinvoice.application;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSummary {
    private String customer;
    private final List<Performance> performances = new ArrayList<>();

    public PerformanceSummary() {}

    public PerformanceSummary(String customer) {
        this.customer = customer;
    }

    public String getCustomerName() {
        return customer;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void addPerformance(String playId, int audience) {
        Performance p = new Performance(playId, audience);
        performances.add(p);
    }
}
