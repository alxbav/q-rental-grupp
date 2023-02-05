package ee.qrental.transaction.application.port.out;

import ee.qrental.transaction.domain.Transaction;

import java.util.List;

public interface TransactionLoadPort {
    List<Transaction> loadAllTransactionTypes();

    Transaction loadTransactionTypeById(Long id);
}
