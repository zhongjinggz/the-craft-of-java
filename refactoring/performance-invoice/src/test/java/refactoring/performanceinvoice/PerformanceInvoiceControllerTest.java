package refactoring.performanceinvoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PerformanceInvoiceControllerTest {

    @InjectMocks
    private PerformanceInvoiceController controller; // 被测试的控制器实例

    @Mock
    private PerformanceInvoiceRepository repository; // 模拟持久化层接口

    @BeforeEach
    void setUp() {

        // 初始化 Mockito 注解支持
        MockitoAnnotations.openMocks(this);

        // 模拟 repository 行为
        doNothing().when(repository).save(any(PerformanceInvoice.class));
    }

    /**
     * 测试正常悲剧演出账单生成
     */
    @Test
    void should_create_bill_with_tragedy_performance() {
        // given
        PerformanceSummary summary = new PerformanceSummary("张三");
        summary.addPerformance("dasheng", 35); // 超过30人

        // when
        PerformanceInvoice invoice = controller.createInvoice(summary);

        // then
        assertNotNull(invoice);
        assertEquals("张三", invoice.getCustomer());
        assertEquals(1, invoice.getItems().size());

        // 验证总金额：40000 + 1000*(35-30) = 45000
        assertEquals(45000, invoice.getTotalAmount());

        // 验证积分：max(35-30, 0) = 5
        assertEquals(5, invoice.getVolumeCredits());

        // 验证数据库操作
        verify(repository, times(1)).save(any(PerformanceInvoice.class));
    }

    /**
     * 测试正常喜剧演出账单生成
     */
    @Test
//    void testCreateBillWithComedyPerformance() {
    void should_create_bill_with_comedy_performance() {
        PerformanceSummary summary = new PerformanceSummary("李四");
        summary.addPerformance("007", 25); // 超过20人

//        doNothing().when(repository).save(any(PerformanceInvoice.class));

        PerformanceInvoice invoice = controller.createInvoice(summary);

        assertNotNull(invoice);
        assertEquals("李四", invoice.getCustomer());
        assertEquals(1, invoice.getItems().size());

        // 计算预期金额：30000 + 10000 + 500*(25-20) + 300*25 = 30000+10000+2500+7500=50000
        assertEquals(50000, invoice.getTotalAmount());

        // 积分：max(25-30,0)=0 + floorDiv(25,5)=5 => 共5分
        assertEquals(5, invoice.getVolumeCredits());

        verify(repository, times(1)).save(any(PerformanceInvoice.class));
    }

    /**
     * 测试多个不同类型演出合并账单
     */
    @Test
    void should_create_bill_with_multiple_types_of_performances() {
        PerformanceSummary summary = new PerformanceSummary("王五");
        summary.addPerformance("dasheng", 30); // 悲剧刚好30人
        summary.addPerformance("qiuxiang", 20); // 喜剧刚好20人

        PerformanceInvoice invoice = controller.createInvoice(summary);

        assertNotNull(invoice);
        assertEquals("王五", invoice.getCustomer());
        assertEquals(2, invoice.getItems().size());

        // 悲剧：40000；喜剧：30000 + 300*20 = 36000 → 总计76000
        assertEquals(76000, invoice.getTotalAmount());

        // 积分：悲剧 max(0), 喜剧 floorDiv(20/5)=4 → 总共4分
        assertEquals(4, invoice.getVolumeCredits());

        verify(repository, times(1)).save(any(PerformanceInvoice.class));
    }

    /**
     * 测试若剧目不存在，则抛出空异常
     */
    //@Test
    void should_throw_null_pointer_exception_for_invalid_play() {

        PerformanceSummary summary = new PerformanceSummary("赵六");
        summary.addPerformance("invalid", 20);

        assertThrows(NullPointerException.class, () -> {
            controller.createInvoice(summary);
        });

        verify(repository, never()).save(any(PerformanceInvoice.class)); // 不应执行保存
    }


    // TODO 测试非法剧目类型

    /**
     * 测试边界情况：没有演出时账单为空但有效
     */
    @Test
    void should_create_empty_bill_for_no_performances() {
        PerformanceSummary summary = new PerformanceSummary("孙七");

        doNothing().when(repository).save(any(PerformanceInvoice.class));

        PerformanceInvoice invoice = controller.createInvoice(summary);

        assertNotNull(invoice);
        assertEquals("孙七", invoice.getCustomer());
        assertTrue(invoice.getItems().isEmpty());
        assertEquals(0, invoice.getTotalAmount());
        assertEquals(0, invoice.getVolumeCredits());

        verify(repository, times(1)).save(any(PerformanceInvoice.class));
    }
}
