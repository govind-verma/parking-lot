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
public class Plan implements Serializable {

    private String name;

    private double price;

    private LocalDateTime fromDate;

    private LocalDateTime toDate;

    private VehicleType vehicleType;

    public boolean validate(LocalDateTime membershipDate, VehicleType vehicleType) {
        if (membershipDate != null) {
            if (this.fromDate.isAfter(membershipDate) && this.toDate.isBefore(membershipDate)) {
                return true;
            }
        }

        if (vehicleType != null && vehicleType.equals(this.vehicleType)) {
            return true;
        }
        return false;
    }
}
