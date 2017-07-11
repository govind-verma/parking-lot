package com.gs.parkinglot.controller;

import com.gs.parkinglot.enums.VehicleType;
import com.gs.parkinglot.request.ParkingLotRequest;
import com.gs.parkinglot.service.IParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by govinda.v on 11/07/17.
 */
@RestController
@RequestMapping("/parking")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ParkingController {

    private final IParkingService parkingService;

    @GetMapping(path = "/")
    ResponseEntity<?> parking() {
        return new ResponseEntity<>(parkingService.getParking(), HttpStatus.OK);
    }

    @GetMapping(path = "/zones/{vehicleType}/{floor}")
    ResponseEntity<?> parking(@PathVariable("vehicleType") VehicleType vehicleType,
                              @PathVariable("floor") String floor) {

        return new ResponseEntity<>(parkingService.getParkingZone(vehicleType, floor), HttpStatus.OK);
    }

    @PostMapping(path = "/allocate")
    ResponseEntity<?> allocate(@RequestBody ParkingLotRequest parkingLotRequest) {

        return new ResponseEntity<>(parkingService.allocate(parkingLotRequest), HttpStatus.OK);
    }

    @PostMapping(path = "/release")
    ResponseEntity<?> release(@RequestBody ParkingLotRequest parkingLotRequest) {

        return new ResponseEntity<>(parkingService.releaseParkingLot(parkingLotRequest), HttpStatus.OK);
    }
}
