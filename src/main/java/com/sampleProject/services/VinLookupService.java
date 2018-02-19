package com.sampleProject.services;

import com.sampleProject.contracts.GlobalBusinessLogicVariables;
import com.sampleProject.contracts.IVehicleRepository;
import com.sampleProject.contracts.IVinLookupService;
import com.sampleProject.dto.IsVehicleInsurable;
import com.sampleProject.dto.VehicleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@EntityScan("com.sampleProject.contracts")
@ComponentScan("com.sampleProject.repositories")
public class VinLookupService implements IVinLookupService {

    private IVehicleRepository vehicleRepository;

    @Autowired
    public VinLookupService(IVehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDetails GetDetails(String vin) {
        VehicleDetails ret = vehicleRepository.GetDetails(vin);
        return ret;
    }

    @Override
    public IsVehicleInsurable IsInsurable(String vin) {
        IsVehicleInsurable ret;
        if(vehicleRepository.GetDetails(vin).year < GlobalBusinessLogicVariables.minimumInsurabYear) {
            ret = new IsVehicleInsurable(false);
        }
        else {
            ret = new IsVehicleInsurable(true);
        }
        return ret;
    }

}
