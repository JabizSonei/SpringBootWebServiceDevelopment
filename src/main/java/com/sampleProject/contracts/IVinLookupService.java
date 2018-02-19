package com.sampleProject.contracts;

import com.sampleProject.dto.IsVehicleInsurable;
import com.sampleProject.dto.VehicleDetails;

public interface IVinLookupService {
    VehicleDetails GetDetails(String vin);
    IsVehicleInsurable IsInsurable(String vin);
}
