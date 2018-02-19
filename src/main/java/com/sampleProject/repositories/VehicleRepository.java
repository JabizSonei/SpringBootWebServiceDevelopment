package com.sampleProject.repositories;

import com.sampleProject.contracts.GlobalBusinessLogicVariables;
import com.sampleProject.contracts.ISqlHelper;
import com.sampleProject.contracts.IVehicleRepository;
import com.sampleProject.dto.VehicleDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;

@Repository
public class VehicleRepository implements IVehicleRepository {

    private final ISqlHelper sqlHelper;

    @Autowired
    public VehicleRepository(ISqlHelper sqlHelper) {
        this.sqlHelper = sqlHelper;
    }

    @Override
    public VehicleDetails GetDetails(String vin) {
        VehicleDetails ret;
        try {
            String sql = "Select * from Vehicle where Vin = '" + vin + "'";
            ResultSet resultSet = sqlHelper.ExecuteSqlQuery(sql);
            resultSet.next();
            int year = resultSet.getInt(1);
            String make = resultSet.getString(2);
            String model = resultSet.getString(3);
            ret = new VehicleDetails(year, make, model);
        } catch (Exception e) {
            ret = new VehicleDetails(GlobalBusinessLogicVariables.yearForNotFoundVehicle,
                                     GlobalBusinessLogicVariables.unavailableMessage,
                                     GlobalBusinessLogicVariables.unavailableMessage);
        }
        return ret;
    }
}
