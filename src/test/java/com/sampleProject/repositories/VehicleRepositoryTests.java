package com.sampleProject.repositories;

import com.sampleProject.contracts.GlobalBusinessLogicVariables;
import com.sampleProject.contracts.ISqlHelper;
import com.sampleProject.contracts.IVehicleRepository;
import com.sampleProject.dto.VehicleDetails;
import org.junit.Test;
import org.omg.CORBA.Any;
import org.springframework.util.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VehicleRepositoryTests {

    @Test
    public void ExceptionThrown() throws SQLException, ClassNotFoundException {
        final ISqlHelper sqlHelperMock = mock(ISqlHelper.class);
        when(sqlHelperMock.ExecuteSqlQuery(any())).thenThrow(Exception.class);

        final IVehicleRepository vehicleRepository = new VehicleRepository(sqlHelperMock);
        final VehicleDetails rs = vehicleRepository.GetDetails(null);

        final int expectedYear = GlobalBusinessLogicVariables.yearForNotFoundVehicle;
        final String expectedMake = GlobalBusinessLogicVariables.unavailableMessage;
        final String expectedModel = GlobalBusinessLogicVariables.unavailableMessage;
        final int actualYearReturn = rs.year;
        final String actualMakeReturn = rs.make;
        final String actualModelReturn = rs.model;

        Assert.isTrue(expectedYear == actualYearReturn, "Year mismatch!");
        Assert.isTrue(expectedMake.equals(actualMakeReturn), "Make mismatch!");
        Assert.isTrue(expectedModel.equals(actualModelReturn), "Model mismatch!");
    }

    @Test
    public void ExpectedValuesReturned() throws SQLException, ClassNotFoundException {
        final ResultSet resultSetMock = mock(ResultSet.class);

        final int yearResultSetIndex = 1;
        final int makeResultSetIndex = 2;
        final int modelResultSetIndex = 3;

        final int expectedYear = Calendar.getInstance().get(Calendar.YEAR);
        final String expectedMake = "FakeMake";
        final String expectedModel = "FakeModel";

        when(resultSetMock.getInt(yearResultSetIndex)).thenReturn(expectedYear);
        when(resultSetMock.getString(makeResultSetIndex)).thenReturn(expectedMake);
        when(resultSetMock.getString(modelResultSetIndex)).thenReturn(expectedModel);
        when(resultSetMock.next()).thenReturn(true);

        final ISqlHelper sqlHelperMock = mock(ISqlHelper.class);
        when(sqlHelperMock.ExecuteSqlQuery(any())).thenReturn(resultSetMock);

        final VehicleRepository vehicleRepository = new VehicleRepository(sqlHelperMock);
        final VehicleDetails vehicleDetails = vehicleRepository.GetDetails(null);

        final int actualYearReturned = vehicleDetails.year;
        final String actualMakeReturned = vehicleDetails.make;
        final String actualModelReturned = vehicleDetails.model;
        Assert.isTrue(expectedYear == actualYearReturned, "Year mismatch!");
        Assert.isTrue(expectedMake == actualMakeReturned, "Make mismatch!");
        Assert.isTrue(expectedModel == actualModelReturned, "Model mismatch!");
    }
}
