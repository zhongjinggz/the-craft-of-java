package refactoring.performanceinvoice.application;

import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.Play;
import refactoring.performanceinvoice.domain.PlayRepository;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepositoryImpl;
import refactoring.performanceinvoice.drivingadapter.Performance;
import refactoring.performanceinvoice.drivingadapter.PerformanceSummary;

import java.util.HashMap;
import java.util.Map;

public class PerformanceInvoiceService {
    private final PerformanceInvoiceRepository invoiceRepository;
    private final PlayRepository playRepository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository invoiceRepository, PlayRepository playRepository) {
        this.invoiceRepository = invoiceRepository;
        this.playRepository = playRepository;
    }

    Map<String, Play> plays = new HashMap<>();
    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {
        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomerName());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = playRepository.findPlayById(perf.getPlayId());

            invoice.addItem(play
                , play.calAmount(perf)
                , play.calPoints(perf)
                , perf.getAudience());

        }


        invoiceRepository.save(invoice);
        return invoice;
    }

}
