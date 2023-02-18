package ee.qrental.transaction.domain;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;

import static java.lang.String.format;


@Getter
@Setter
public class Transaction {
    private static final Integer COMMENT_MAX_SIZE = 150;

    private Long id;

    private TransactionType type;

    private Long driverId;

    private Long amount;

    private LocalDate date;

    private String comment;

    public Transaction(Long id,
                       TransactionType type,
                       Long driverId,
                       Long amount,
                       LocalDate date,
                       String comment) {
        this.id = id;
        this.type = type;
        this.driverId = driverId;
        this.amount = amount;
        this.date = date;
        this.comment = comment;
    }
    public Long getRealAmount() {
        return type.getNegative() ? -amount : amount;
    }
    public Integer getWeekNumber() {
        return date.get(ChronoField.ALIGNED_WEEK_OF_YEAR);
    }
}
