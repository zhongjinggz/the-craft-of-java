package refactoring.performanceinvoice.drivingadapter;

import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.domain.PerformanceInvoice;

//DONE 坏味道：缺乏包内聚；重构手法：重构到分层架构/搬移类
//DONE 坏味道：过长的函数(PerformanceInvoiceController#createInvoice)；重构手法：提炼函数
//CONE 坏味道：临时变量；重构手法：内联变量
//DONE 坏味道：过长的类(PerformanceInvoiceController)；重构手法：提炼类/搬移函数
//DONE 坏味道：过长的函数(PerformanceInvoiceService#createInvoice)；重构手法：提炼函数
//DONE 坏味道：特性依恋；重构手法：搬移函数
//DONE 坏味道：基本类型偏执；重构手法：提炼类
//DONE 坏味道：消息链；重构手法：提炼函数/搬移函数
//DONE 坏味道：重复的Switch / 复杂代码；重构手法：提炼超类/搬移函数/重写算法（实现策略模式，开闭原则）
//DONE 坏味道：魔法数字; 重构手法：提炼常量
//DONE 坏味道：数据类；重构手法：搬移函数/内联函数
//DONE 将取Play的逻辑放入 Repository
//DONE 处理分层架构中的依赖倒置
//DONE 潜在BUG：String.equals()顺序 （重构后已经不存在该问题）
//DONE 坏味道：神秘命名；重构手法：重命名
//DONE 坏味道：不当注释
//DONE 坏味道：废弃代码
//DOING 坏味道：可变性

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
