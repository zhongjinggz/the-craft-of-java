package refactoring.performanceinvoice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.PerformanceInvoiceRepository;
import refactoring.performanceinvoice.drivenadapter.PlayRepositoryMem;
import refactoring.performanceinvoice.drivingadapter.PerformanceInvoiceController;
import refactoring.performanceinvoice.drivingadapter.PerformanceSummary;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PerformanceInvoiceControllerTest {

    private PerformanceInvoiceController controller; // 被测试的控制器实例

    @Mock
    private PerformanceInvoiceRepository invoiceRepository; // 模拟持久化层接口

    @BeforeEach
    void setUp() {
        PerformanceInvoiceService service = new PerformanceInvoiceService(
            invoiceRepository,
            new PlayRepositoryMem());
        controller = new PerformanceInvoiceController(service);
    }

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
        assertEquals(45000, invoice.getAmount());

        // 验证积分：max(35-30, 0) = 5
        assertEquals(5, invoice.getAudiencePoints());

        // 验证数据库操作
        verify(invoiceRepository, times(1)).save(any(PerformanceInvoice.class));
    }

    @Test
    void should_create_bill_with_comedy_performance() {
        PerformanceSummary summary = new PerformanceSummary("李四");
        summary.addPerformance("007", 25); // 超过20人

        PerformanceInvoice invoice = controller.createInvoice(summary);

        assertNotNull(invoice);
        assertEquals("李四", invoice.getCustomer());
        assertEquals(1, invoice.getItems().size());

        // 计算预期金额：30000 + 10000 + 500*(25-20) + 300*25 = 30000+10000+2500+7500=50000
        assertEquals(50000, invoice.getAmount());

        // 积分：max(25-30,0)=0 + floorDiv(25,5)=5 => 共5分
        assertEquals(5, invoice.getAudiencePoints());

        verify(invoiceRepository, times(1)).save(any(PerformanceInvoice.class));
    }

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
        assertEquals(76000, invoice.getAmount());

        // 积分：悲剧 max(0), 喜剧 floorDiv(20/5)=4 → 总共4分
        assertEquals(4, invoice.getAudiencePoints());

        verify(invoiceRepository, times(1)).save(any(PerformanceInvoice.class));
    }


    // TODO 测试非法剧目类型

    @Test
    void should_create_empty_bill_for_no_performances() {
        PerformanceSummary summary = new PerformanceSummary("孙七");

        doNothing().when(invoiceRepository).save(any(PerformanceInvoice.class));

        PerformanceInvoice invoice = controller.createInvoice(summary);

        assertNotNull(invoice);
        assertEquals("孙七", invoice.getCustomer());
        assertTrue(invoice.getItems().isEmpty());
        assertEquals(0, invoice.getAmount());
        assertEquals(0, invoice.getAudiencePoints());

        verify(invoiceRepository, times(1)).save(any(PerformanceInvoice.class));
    }
}
