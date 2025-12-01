package refactoring.performanceinvoice.domain.playtype;

import refactoring.performanceinvoice.application.PerformanceInvoiceService;

public interface PlayTypeRepository {
    Play findById(String playId, PerformanceInvoiceService performanceInvoiceService);
}
