package com.sampleProject.contracts;

import com.sampleProject.dto.VehicleDetails;

public interface IVehicleRepository {
    VehicleDetails GetDetails(String vin);
}
