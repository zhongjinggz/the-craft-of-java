package refactoring.performanceinvoice.drivingadapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import refactoring.performanceinvoice.drivenadapter.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.Play;

import java.util.HashMap;
import java.util.Map;

//DONE 坏味道：缺乏包内聚；重构手法：重构到分层架构/搬移类
//DOING 坏味道：过长的函数；重构手法：提炼函数
//TODO 坏味道：过长的类；重构手法：提炼类/搬移函数
//TODO 坏味道：临时变量；重构手法：内联变量
//TODO 坏味道：过大的类；重构手法：提炼类（service 类）
//TODO 坏味道：复杂代码；重构手法：提炼函数
//TODO 坏味道：特性依恋；重构手法：搬移函数
//TODO 坏味道：基本类型偏执；重构手法：提炼类
//TODO 坏味道：消息链；重构手法：提炼函数
//TODO 坏味道：重复的 Switch；重构手法：提炼超类/搬移函数/重写算法（实现策略模式，开闭原则）
//TODO 坏味道：魔法数字; 重构手法：提炼常量
//TODO 坏味道：数据类；重构手法：搬移函数/内联函数
//TODO 优化：Play Repository
//TODO 潜在BUG：String.equals()顺序 -- 是重构吗？
//DOING 坏味道：神秘命名；重构手法：重命名
//DOING 坏味道：不当注释
//DOING 坏味道：废弃代码

@RestController
public class PerformanceInvoiceController {

    Map<String, Play> plays = new HashMap<>();

    @Autowired
    PerformanceInvoiceRepository repository;

    @PostMapping("/api/performance-invoice")
    public PerformanceInvoice createInvoice(@RequestBody PerformanceSummary performanceSummary) {
        PerformanceInvoice invoice = createInvoice2(performanceSummary);

        return invoice;
    }

    private PerformanceInvoice createInvoice2(PerformanceSummary performanceSummary) {
        //初始化戏剧列表
        plays.put("dasheng", new Play("dasheng", "大圣娶亲", "tragedy"));
        plays.put("007", new Play("007", "国产凌凌漆", "comedy"));
        plays.put("qiuxiang", new Play("qiuxiang", "唐伯虎点秋香", "comedy"));

        int totalAmount = 0;
        int volumeCredits = 0;

        PerformanceInvoice bill = new PerformanceInvoice(
                performanceSummary.getCustomer());


        for (Performance perf : performanceSummary.getPerformances()) {
            Play play = plays.get(perf.getPlayId());
            int thisAmt;

            if (play.getType().equals("tragedy")) {
                thisAmt = 40000;
                if (perf.getAudience() > 30) {
                    thisAmt += 1000 * (perf.getAudience() - 30);
                }
            } else if (play.getType().equals("comedy")) {
                thisAmt = 30000;
                if (perf.getAudience() > 20) {
                    thisAmt += 10000 + 500 * (perf.getAudience() - 20);
                }
                thisAmt += 300 * perf.getAudience();
            } else {
                throw new IllegalArgumentException("戏剧类型不正确!");
            }

            //计算观众量积分
            volumeCredits += Math.max(perf.getAudience() - 30, 0);
            if ("comedy".equals(play.getType())) {
                volumeCredits += Math.floorDiv(perf.getAudience(), 5);
            }

            totalAmount += thisAmt;

            // 添加账单项
            bill.addItem(play.getName(),thisAmt, perf.getAudience());


        }

        //设置账单金额和积分
        bill.setTotalAmount(totalAmount);
        bill.setVolumePoints(volumeCredits);

        repository.save(bill);
        return bill;
    }

}
