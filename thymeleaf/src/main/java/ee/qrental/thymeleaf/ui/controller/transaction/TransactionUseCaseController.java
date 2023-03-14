package ee.qrental.thymeleaf.ui.controller.transaction;


import ee.qrental.driver.application.port.in.query.GetDriverQuery;
import ee.qrental.transaction.application.port.in.query.GetTransactionQuery;
import ee.qrental.transaction.application.port.in.query.GetTransactionTypeQuery;
import ee.qrental.transaction.application.port.in.request.transaction.TransactionAddRequest;
import ee.qrental.transaction.application.port.in.request.transaction.TransactionDeleteRequest;
import ee.qrental.transaction.application.port.in.request.transaction.TransactionUpdateRequest;
import ee.qrental.transaction.application.port.in.usecase.transaction.TransactionAddUseCase;
import ee.qrental.transaction.application.port.in.usecase.transaction.TransactionDeleteUseCase;
import ee.qrental.transaction.application.port.in.usecase.transaction.TransactionUpdateUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/transactions")

@AllArgsConstructor
public class TransactionUseCaseController {

    private final TransactionAddUseCase addUseCase;
    private final TransactionUpdateUseCase updateUseCase;
    private final TransactionDeleteUseCase deleteUseCase;

    private final GetTransactionQuery transactionQuery;
    private final GetTransactionTypeQuery transactionTypeQuery;
    private final GetDriverQuery driverQuery;

    @GetMapping(value = "/add-form")
    public String addForm(final Model model) {
        model.addAttribute("addRequest", new TransactionAddRequest());
        model.addAttribute("transactionTypes", transactionTypeQuery.getAll());
        model.addAttribute("drivers", driverQuery.getAll());

        return "forms/addTransaction";
    }

    @PostMapping(value = "/add")
    public String addTransactionTransaction(@ModelAttribute final TransactionAddRequest addRequest) {
        addUseCase.add(addRequest);

        return "redirect:/transactions";
    }

    @GetMapping(value = "/update-form/{id}")
    public String updateForm(@PathVariable("id") long id, Model model) {
        model.addAttribute("updateRequest", transactionQuery.getUpdateRequestById(id));
        model.addAttribute("transactionTypes", transactionTypeQuery.getAll());
        model.addAttribute("drivers", driverQuery.getAll());

        return "forms/updateTransaction";
    }

    @PostMapping("/update")
    public String updateTransactionTransaction(final TransactionUpdateRequest updateRequest) {
        updateUseCase.update(updateRequest);

        return "redirect:/transactions";
    }

    @GetMapping(value = "/delete-form/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("deleteRequest", new TransactionDeleteRequest(id));
        model.addAttribute("objectInfo", transactionQuery.getObjectInfo(id));

        return "forms/deleteTransaction";
    }

    @PostMapping("/delete")
    public String deleteForm(final TransactionDeleteRequest transactionDeleteCommand) {
        deleteUseCase.delete(transactionDeleteCommand);

        return "redirect:/transactions";
    }
}