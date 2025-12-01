package refactoring.performanceinvoice.drivingadapter;

import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.application.PerformanceSummary;
import refactoring.performanceinvoice.domain.PerformanceInvoice;


@RestController
public class PerformanceInvoiceController {

    private final PerformanceInvoiceService performanceInvoiceService;

    public PerformanceInvoiceController(PerformanceInvoiceService performanceInvoiceService) {
        this.performanceInvoiceService = performanceInvoiceService;
    }

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createInvoice(@RequestBody PerformanceSummary performanceSummary) {
        return performanceInvoiceService.createInvoice(performanceSummary);
    }

}
