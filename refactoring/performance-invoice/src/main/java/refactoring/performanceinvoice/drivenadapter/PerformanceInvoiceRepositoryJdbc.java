package refactoring.performanceinvoice.drivenadapter;

import org.springframework.stereotype.Repository;
import refactoring.performanceinvoice.domain.PerformanceInvoice;
import refactoring.performanceinvoice.domain.PerformanceInvoiceRepository;

@Repository
public class PerformanceInvoiceRepositoryJdbc implements PerformanceInvoiceRepository {
    @Override
    public void save(PerformanceInvoice invoice){
        throw new UnsupportedOperationException("该方法尚未实现");
    }

}
