package refactoring.performanceinvoice.drivenadapter;

import org.springframework.stereotype.Repository;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoice;
import refactoring.performanceinvoice.domain.performanceinvoice.PerformanceInvoiceRepository;

@Repository
public class PerformanceInvoiceRepositoryImpl implements PerformanceInvoiceRepository {
    @Override
    public void save(PerformanceInvoice invoice){
        throw new UnsupportedOperationException("该方法尚未实现");
    }

}
