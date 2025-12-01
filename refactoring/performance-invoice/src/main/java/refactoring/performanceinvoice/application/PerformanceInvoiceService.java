package refactoring.performanceinvoice.application;

import org.springframework.stereotype.Service;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.Play;

import java.util.HashMap;
import java.util.Map;

//DONE 重构名称
//DONE 抽取应用服务
//DONE 解决仓库依赖倒置问题
//DONE 分层架构
//DONE 用构造函数进行依赖注入
//DONE 降低复杂度
//DONE 解决特性依恋
//DONE 解决调用链
//DONE 策略模式
//TODO 重构魔法数字
//TODO plays 应放在数据库
//清理其他类

@Service
public class PerformanceInvoiceService {

    private final PerformanceInvoiceRepository repository;

    public PerformanceInvoiceService(PerformanceInvoiceRepository repository) {
        this.repository = repository;
    }

    Map<String, Play> plays = new HashMap<>();

    public PerformanceInvoice createInvoice(PerformanceSummary performanceSummary) {

        initPlays();

        int totalAmount = 0;
        int totalAudiencePoints = 0;

        PerformanceInvoice invoice = new PerformanceInvoice(
                performanceSummary.getCustomer());

        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());

            int amount = play.calAmount(perf.getAudienceCount());
            totalAmount += amount;

            int audiencePoints = play.calAudiencePoints(perf);
            totalAudiencePoints += audiencePoints;

            // 添加账单项
            invoice.addItem(play.getName(),amount, perf.getAudienceCount());

        }

        //设置账单金额和积分
        invoice.setTotalAmount(totalAmount);
        invoice.setTotalAudiencePoints(totalAudiencePoints);

        repository.save(invoice);
        return invoice;
    }

    private void initPlays() {
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));
    }

}
