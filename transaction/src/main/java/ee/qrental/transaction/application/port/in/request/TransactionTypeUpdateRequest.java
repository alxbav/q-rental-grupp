package ee.qrental.transaction.application.port.in.request;

import ee.qrental.common.core.api.request.AbstractUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionTypeUpdateRequest extends AbstractUpdateRequest {
    private String name;
    private String description;
    private Boolean negative;
    private String comment;
}