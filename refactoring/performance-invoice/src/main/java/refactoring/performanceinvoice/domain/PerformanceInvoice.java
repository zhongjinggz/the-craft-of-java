package refactoring.performanceinvoice.domain;

import java.util.ArrayList;
import java.util.List;

public class PerformanceInvoice {
    private Long id;

    private String customer;

    private int totalAmount;

    private int volumeCredits;

    private List<PerformanceInvoiceItem> items = new ArrayList<>();

    public PerformanceInvoice(String customer) {
        this.customer = customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void addItem(PerformanceInvoiceItem item) {
        this.items.add(item);
    }

    public void addItem(String name, int amount, int audience) {
        this.items.add(new PerformanceInvoiceItem(name, amount, audience));
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceInvoiceItem> getItems() {
        return items;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setVolumePoints(int volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }

    public int addItem2(int totalAmount, int thisAmount, Play play, int audienceCount) {
        totalAmount += thisAmount;
        addItem(play.getName(), thisAmount, audienceCount);
        return totalAmount;
    }
}
