package com.gs.parkinglot.pojo;

import com.gs.parkinglot.enums.ParkingLotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by govinda.v on 10/07/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLot {

    private String name;

    private ParkingLotStatus parkingLotStatus;

    private Double distanceFromEntrance;

}
