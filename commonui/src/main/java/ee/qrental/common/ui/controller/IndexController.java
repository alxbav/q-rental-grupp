package ee.qrental.common.ui.controller;

import ee.qrental.driver.application.port.out.DriverLoadPort;
import ee.qrental.driverpotential.application.port.out.DriverPotentialLoadPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor

@Controller
@RequestMapping("/")
public class IndexController {

    private final DriverPotentialLoadPort driverPotentialLoadPort;

    private final DriverLoadPort driverLoadPort;

    @GetMapping
    public String getIndexView(final Model model) {
        addDriverPotentialListToModel(model);
        addDriverListToModel(model);
        return "index";
    }

    private void addDriverPotentialListToModel(final Model model) {
        final var drivers = driverPotentialLoadPort.loadAllPotentialDrivers();
        model.addAttribute("driversPotential", drivers);
    }
    private void addDriverListToModel(final Model model) {
        final var drivers = driverLoadPort.loadAllDrivers();
        model.addAttribute("drivers", drivers);
    }
}
