package ee.qrental.transaction.application.service.strategy;

import ee.qrental.transaction.application.port.in.request.transaction.TransactionFilterRequest;
import ee.qrental.transaction.application.port.in.utils.Week;
import ee.qrental.transaction.application.port.out.TransactionLoadPort;
import ee.qrental.transaction.domain.Transaction;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static ee.qrental.transaction.application.service.strategy.TransactionLoadUtils.getFirstDayOfWeekInYear;
import static ee.qrental.transaction.application.service.strategy.TransactionLoadUtils.getLastDayOfWeekInYear;

@Component
@AllArgsConstructor
public class TransactionLoadStrategyByYearAndWeek implements TransactionLoadStrategy {

    private final TransactionLoadPort transactionLoadPort;

    @Override
    public boolean canApply(TransactionFilterRequest request) {
        return request.getDriverId() == null && request.getWeek() != Week.ALL;
    }

    @Override
    public List<Transaction> load(TransactionFilterRequest request) {
        final var year = request.getYear();
        final var weekNumber = request.getWeek().getNumber();

        return transactionLoadPort.loadAllBetweenDays(
                getFirstDayOfWeekInYear(year, weekNumber),
                getLastDayOfWeekInYear(year, weekNumber));
    }
}
