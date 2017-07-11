package com.gs.parkinglot.request;

import com.gs.parkinglot.pojo.Vehicle;
import com.gs.parkinglot.pojo.Membership;
import com.gs.parkinglot.pojo.ParkingLot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by govinda.v on 10/07/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ParkingLotRequest {

    private String membershipId;

    private Vehicle vehicle;

    private String parkingLotName;

    private String parkingZoneName;

    private LocalDateTime parkedTime;

    private String floor;
}
