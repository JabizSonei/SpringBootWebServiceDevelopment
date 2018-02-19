package com.sampleProject.services;

import com.sampleProject.contracts.GlobalBusinessLogicVariables;
import com.sampleProject.contracts.IVehicleRepository;
import com.sampleProject.contracts.IVinLookupService;
import com.sampleProject.dto.IsVehicleInsurable;
import com.sampleProject.dto.VehicleDetails;
import org.springframework.util.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VinLookupServiceTests {

    @Test
    public void IsNotInsurable() {
        final int yearToBeginTestAt = 1900;
        for (int year = yearToBeginTestAt; year < GlobalBusinessLogicVariables.minimumInsurabYear; year++) {
            IsVehicleInsurable isVehicleInsurable = GetIsVehicleInsurable(year);
            Assert.isTrue(!isVehicleInsurable.isVehicleInsurable,
                          "Vehicle is expected not to be insurable.");
        }
    }

    @Test
    public void IsInsurable() {
        final int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        final int offsetYearForLatestVehicle = 2;
        final int yearToEndTestAt = currentYear + offsetYearForLatestVehicle;
        for (int year = GlobalBusinessLogicVariables.minimumInsurabYear; year < yearToEndTestAt; year++) {
            IsVehicleInsurable isVehicleInsurable = GetIsVehicleInsurable(year);
            Assert.isTrue(isVehicleInsurable.isVehicleInsurable,
                          "Vehicle is expected to be insurable.");
        }
    }

    private IsVehicleInsurable GetIsVehicleInsurable(int year) {
        final String fakeVin = "fakeVin";
        VehicleDetails fakeVehicle = new VehicleDetails(year, "FakeMake", "FakeModel");
        IVehicleRepository vehicleRepositoryMock = mock(IVehicleRepository.class);
        when(vehicleRepositoryMock.GetDetails(fakeVin)).thenReturn(fakeVehicle);

        IVinLookupService vinLookupService = new VinLookupService(vehicleRepositoryMock);
        return vinLookupService.IsInsurable(fakeVin);
    }

}
