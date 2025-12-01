package refactoring.performanceinvoice.drivingadapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import refactoring.performanceinvoice.application.PerformanceInvoiceService;
import refactoring.performanceinvoice.application.PerformanceSummary;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;

@ExtendWith(MockitoExtension.class)
class PerformanceInvoiceControllerTest {

    private PerformanceInvoiceController controller;

    @Mock
    private PerformanceInvoiceService performanceInvoiceService;

    @BeforeEach
    void setUp() {
        controller = new PerformanceInvoiceController(performanceInvoiceService);
    }

    /**
     * 测试正常创建发票的场景
     * 验证controller能正确调用service并返回结果
     */
    @Test
    void should_handle_normal_case() {
        // 准备测试数据
        PerformanceSummary performanceSummary = new PerformanceSummary("张三");
        performanceSummary.addPerformance("hamlet", 55);

        // 创建期望返回的发票对象
        PerformanceInvoice expectedInvoice = new PerformanceInvoice("张三");
        expectedInvoice.setTotalAmount(1000);
        expectedInvoice.setTotalAudiencePoints(10);

        // 设置mock行为
        when(performanceInvoiceService.createInvoice(performanceSummary)).thenReturn(expectedInvoice);

        // 执行被测方法
        PerformanceInvoice actualInvoice = controller.createInvoice(performanceSummary);

        // 验证结果
        assertNotNull(actualInvoice, "返回的发票对象不应为null");
        assertEquals("张三", actualInvoice.getCustomer(), "客户名称应该匹配");
        assertEquals(1000, actualInvoice.getTotalAmount(), "总金额应该匹配");
        assertEquals(10, actualInvoice.getVolumeCredits(), "积分应该匹配");

        // 验证service方法被正确调用
        verify(performanceInvoiceService, times(1)).createInvoice(performanceSummary);
    }

}
