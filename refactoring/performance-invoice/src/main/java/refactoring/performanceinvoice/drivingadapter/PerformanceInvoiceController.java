package refactoring.performanceinvoice.drivingadapter;

import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.application.PerformanceSummary;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;


@RestController
public class PerformanceInvoiceController {

    private final PerformanceInvoiceService invoiceService;

    public PerformanceInvoiceController(PerformanceInvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createInvoice(@RequestBody PerformanceSummary performanceSummary) {
        return invoiceService.createInvoice(performanceSummary);
    }

}
