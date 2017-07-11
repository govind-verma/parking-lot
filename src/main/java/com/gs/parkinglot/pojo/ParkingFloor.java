package com.gs.parkinglot.pojo;

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
public class ParkingFloor implements Serializable {
    private String name;

    private Entrance entrance;

    private List<ParkingZone> parkingZones;
}
