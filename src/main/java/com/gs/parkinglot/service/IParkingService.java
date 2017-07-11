package com.gs.parkinglot.service;

import com.gs.parkinglot.enums.VehicleType;
import com.gs.parkinglot.pojo.Parking;
import com.gs.parkinglot.pojo.ParkingZone;
import com.gs.parkinglot.request.ParkingLotRequest;
import com.gs.parkinglot.response.ParkingLotResponse;

import java.util.List;

/**
 * Created by govinda.v on 11/07/17.
 */
public interface IParkingService {

    List<ParkingZone> getParkingZone(VehicleType vehicleType, String floor);

    Parking getParking();

    ParkingLotResponse allocate(ParkingLotRequest parkingLotRequest);

    ParkingLotResponse releaseParkingLot(ParkingLotRequest parkingLotRequest);

}
