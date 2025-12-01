package refactoring.performanceinvoice.drivingadapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.application.InvoiceService;
import refactoring.performanceinvoice.application.PerformanceSummary;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.Play;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepository;

import java.util.HashMap;
import java.util.Map;

//DONE 重构名称
//TODO 抽取应用服务
//TODO 解决仓库依赖倒置问题
//TODO 分层架构
//TODO 用构造函数进行依赖注入
//TODO 降低复杂度
//TODO 策略模式
//TODO plays 应放在数据库
//清理其他类

@RestController
public class PerformanceInvoiceController {

    private InvoiceService invoiceService;

    public PerformanceInvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createInvoice(@RequestBody PerformanceSummary performanceSummary) {

        PerformanceInvoice invoice = invoiceService.createInvoice3(performanceSummary);

        return invoice;
    }

}
