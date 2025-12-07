package refactoring.performanceinvoice.application;

import org.springframework.stereotype.Service;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.play.Play;
import refactoring.performanceinvoice.domain.play.PlayRepository;

@Service
public class PerformanceInvoiceService {
    private final PerformanceInvoiceRepository invoiceRepository;
    private final PlayRepository playRepository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository invoiceRepository, PlayRepository playRepository) {
        this.invoiceRepository = invoiceRepository;
        this.playRepository = playRepository;
    }

    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {
        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomerName());

        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = playRepository.findPlayById(perf.playId());

            invoice.addItem(play
                , play.calAmount(perf.audienceCount())
                , play.calPoints(perf.audienceCount())
                , perf.audienceCount());
        }

        invoiceRepository.save(invoice);
        return invoice;
    }

}
