package com.gs.parkinglot.service;

import com.gs.parkinglot.enums.VehicleType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by govinda.v on 10/07/17.
 * This  we can improve
 */
@Component
public class ParkingLotPriceCalculator {

    public Double calculate(VehicleType vehicleType, LocalDateTime parkedTime) {
        long diff = LocalDateTime.now().getHour() - parkedTime.getHour();

        if (VehicleType.TWO_WHEELER.equals(vehicleType)) {
            if (diff > 12) {
                return 30.0D + (diff - 12) * 40;
            } else {
                return 30.0D;
            }
        }

        if (VehicleType.FOUR_WHEELER.equals(vehicleType)) {
            if (diff > 12) {
                return 80.0D + (diff - 12) * 70;
            } else {
                return 80.0D;
            }
        }

        return 0.0D;
    }
}
