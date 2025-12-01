package tdd.performancebill;

import java.util.ArrayList;
import java.util.List;

public class PerformanceBill {
    private Long id;

    private String customer;

    private int totalAmount;

    private int volumeCredits;

    private List<PerformanceBillItem> items = new ArrayList<>();

    public PerformanceBill(String customer) {
        this.customer = customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void addItem(PerformanceBillItem item) {
        this.items.add(item);
    }

    public void addItem(String name, int amount, int audience) {
        this.items.add(new PerformanceBillItem(name, amount, audience));
    }

    public String getCustomer() {
        return customer;
    }

    public List<PerformanceBillItem> getItems() {
        return items;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setVolumeCredits(int volumeCredits) {
        this.volumeCredits = volumeCredits;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public int getVolumeCredits() {
        return volumeCredits;
    }
}
