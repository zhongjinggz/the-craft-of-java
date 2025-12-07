package refactoring.performanceinvoice.application;

import java.util.ArrayList;
import java.util.List;

public class PerformanceSummary {
    private final String customerName;
    private final List<Performance> performances = new ArrayList<>();

    public PerformanceSummary(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public void addPerformance(String playId, int audienceCount) {
        performances.add(new Performance(playId, audienceCount));
    }
}
