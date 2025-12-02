package refactoring.performanceinvoice.application;

import org.springframework.stereotype.Service;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.playtype.Play;
import refactoring.performanceinvoice.domain.playtype.PlayTypeRepository;

//DONE 清理其他类
//DOING 过长函数：让账单自己计算总金额和总积分

@Service
public class PerformanceInvoiceService {

    private final PerformanceInvoiceRepository performanceInvoiceRepository;
    private final PlayTypeRepository playTypeRepository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository performanceInvoiceRepository
        , PlayTypeRepository playTypeRepository) {

        this.performanceInvoiceRepository = performanceInvoiceRepository;
        this.playTypeRepository = playTypeRepository;
    }

    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {

        PerformanceInvoice invoice = buildInvoice(performanceSummary);

        performanceInvoiceRepository.save(invoice);
        return invoice;
    }

    private PerformanceInvoice buildInvoice(PerformanceSummary performanceSummary) {
        PerformanceInvoice invoice = new PerformanceInvoice(
            performanceSummary.getCustomer());

        for (Performance perf : performanceSummary.getPerformances()) {

            Play play = playTypeRepository.findById(perf.getPlayId(), this);

            invoice.addItem(play.getName()
                , play.calAmount(perf.getAudienceCount())
                , play.calAudiencePoints(perf.getAudienceCount())
                , perf.getAudienceCount());

        }

        return invoice;
    }

}
