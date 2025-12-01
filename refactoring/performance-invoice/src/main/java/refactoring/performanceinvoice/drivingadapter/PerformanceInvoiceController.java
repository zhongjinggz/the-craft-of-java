package refactoring.performanceinvoice.drivingadapter;

import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.application.PerformanceSummary;
import refactoring.performanceinvoice.domain.PerformanceInvoice;

//DONE 重构名称
//DONE 抽取应用服务
//TODO 解决仓库依赖倒置问题
//TODO 分层架构
//TODO 用构造函数进行依赖注入
//TODO 降低复杂度
//TODO 策略模式
//TODO plays 应放在数据库
//清理其他类

@RestController
public class PerformanceInvoiceController {

    private PerformanceInvoiceService performanceInvoiceService;

    public PerformanceInvoiceController(PerformanceInvoiceService performanceInvoiceService) {
        this.performanceInvoiceService = performanceInvoiceService;
    }

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createInvoice(@RequestBody PerformanceSummary performanceSummary) {

        PerformanceInvoice invoice = performanceInvoiceService.createInvoice(performanceSummary);

        return invoice;
    }

}
