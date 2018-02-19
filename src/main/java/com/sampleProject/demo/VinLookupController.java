package com.sampleProject.demo;

import com.sampleProject.contracts.IVinLookupService;
import com.sampleProject.dto.IsVehicleInsurable;
import com.sampleProject.dto.VehicleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EntityScan("com.sampleProject.contracts")
@ComponentScan("com.sampleProject.services")
public class VinLookupController {

    private IVinLookupService vinLookupService;

    @Autowired
    public VinLookupController(IVinLookupService vinLookupService) {
        this.vinLookupService = vinLookupService;
    }

    @RequestMapping(value = "/vin", method = RequestMethod.POST)
    public VehicleDetails GetVehicleDetails(String vin) {
        VehicleDetails ret = vinLookupService.GetDetails(vin);
        return ret;
    }

    @RequestMapping(value = "/insurable", method = RequestMethod.POST)
    public IsVehicleInsurable IsVehicleInsurable(String vin) {
        IsVehicleInsurable ret = vinLookupService.IsInsurable(vin);
        return ret;
    }
}
