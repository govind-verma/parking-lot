package com.gs.parkinglot.pojo;

import com.gs.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * Created by govinda.v on 10/07/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingZone implements Serializable {

    private VehicleType vehicleType;

    private String name;

    private List<ParkingLot> parkingLots;

    private Double distanceFromEntrance;

    private int allocatedLots;

    public boolean isFull() {
        return parkingLots.size() == 0 ;
    }

}
