package refactoring.performanceinvoice.domain.performanceinvoice;

import refactoring.performanceinvoice.domain.play.Play;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PerformanceInvoice {
    private Long id;

    private String customerName;

    private int amount = 0;

    private int audiencePoints = 0;

    private final List<PerformanceInvoiceItem> items = new ArrayList<>();

    public PerformanceInvoice(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public List<PerformanceInvoiceItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public int getAmount() {
        return amount;
    }

    public int getAudiencePoints() {
        return audiencePoints;
    }

    public void addItem(Play play, int itemAmount, int itemPoints, int audienceCount) {
        items.add(new PerformanceInvoiceItem(play.getName()
            , itemAmount, audienceCount));
        amount += itemAmount;
        audiencePoints += itemPoints;
    }
}
