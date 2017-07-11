package com.gs.parkinglot.pojo;

import com.gs.parkinglot.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by govinda.v on 10/07/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Membership implements Serializable {

    private String name;

    private VehicleType vehicleType;

    private LocalDateTime membershipDate;

    private Plan plan;

}