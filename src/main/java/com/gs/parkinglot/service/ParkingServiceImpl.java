package com.gs.parkinglot.service;

import com.gs.parkinglot.enums.ParkingLotStatus;
import com.gs.parkinglot.enums.VehicleType;
import com.gs.parkinglot.pojo.*;
import com.gs.parkinglot.request.ParkingLotRequest;
import com.gs.parkinglot.response.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by govinda.v on 11/07/17.
 */
@Service
public class ParkingServiceImpl implements IParkingService {

    private List<ParkingLotRequest> parkingLotRequests = new ArrayList<>();

    private Parking parking = buildParking();

    @Autowired
    private ParkingLotPriceCalculator parkingLotPriceCalculator;

    @Override
    public ParkingLotResponse allocate(ParkingLotRequest parkingLotRequest) {

        updateParkingLotStatus(parkingLotRequest.getFloor(), parkingLotRequest.getParkingZoneName(),
                parkingLotRequest.getParkingLotName());

        parkingLotRequest.setParkedTime(LocalDateTime.now());

        parkingLotRequests.add(parkingLotRequest);

        return ParkingLotResponse.builder().invoice(String.format("Allocated floor %s , zone %s , slot %s and parked time",
                parkingLotRequest.getFloor(), parkingLotRequest.getParkingZoneName(),
                parkingLotRequest.getParkingLotName(), parkingLotRequest.getParkedTime())).build();
    }

    @Override
    public ParkingLotResponse releaseParkingLot(ParkingLotRequest parkingLotRequest) {
        parkingLotRequests.stream().forEach(parkingLotRequest1 -> {

            if (parkingLotRequest.getParkingLotName().equalsIgnoreCase(parkingLotRequest1.getParkingLotName())) {
                parkingLotRequest.setParkedTime(parkingLotRequest1.getParkedTime());
            }
        });


        final List<ParkingFloor> parkingFloors = this.parking.getParkingFloors();

        ParkingFloor parkingFloor = null;

        for (ParkingFloor pf : parkingFloors) {
            if (pf.getName().equalsIgnoreCase(parkingLotRequest.getFloor())) {
                parkingFloor = pf;
                break;
            }
        }

        if (parkingFloor != null) {
            List<ParkingZone> parkingZones = parkingFloor.getParkingZones();

            if (parkingZones != null) {
                for (ParkingZone parkingZone : parkingZones) {
                    if (parkingZone.getName().equalsIgnoreCase(parkingLotRequest.getParkingZoneName())) {

                        parkingZone.setAllocatedLots(parkingZone.getAllocatedLots() - 1);

                        final List<ParkingLot> parkingLots = parkingZone.getParkingLots();


                        for (ParkingLot parkingLot : parkingLots) {
                            if (parkingLot.getName().equalsIgnoreCase(parkingLotRequest.getParkingLotName())) {
                                parkingLot.setParkingLotStatus(ParkingLotStatus.FREE);
                            }
                        }

                    }
                }
            }
        }

        return ParkingLotResponse.builder()
                .invoice(String.format("Release parking lot %s on floor %s in zone %s and charge %s",
                        parkingLotRequest.getParkingLotName(),
                        parkingLotRequest.getFloor(), parkingLotRequest.getParkingZoneName()
                        , parkingLotPriceCalculator.calculate(getParkingZone(parkingLotRequest).getVehicleType(), parkingLotRequest.getParkedTime())))

                .build();
    }

    @Override
    public List<ParkingZone> getParkingZone(VehicleType vehicleType, String floor) {

        final List<ParkingFloor> parkingFloors = this.parking.getParkingFloors();
        ParkingFloor parkingFloor = null;

        for (ParkingFloor pf : parkingFloors) {
            if (pf.getName().equalsIgnoreCase(floor)) {
                parkingFloor = pf;
                break;
            }
        }
        List<ParkingZone> pZones = new ArrayList<>();

        if (parkingFloor != null) {
            List<ParkingZone> parkingZones = parkingFloor.getParkingZones();

            if (parkingZones != null) {
                for (ParkingZone parkingZone : parkingZones) {
                    if (!parkingZone.isFull() && parkingZone.getVehicleType().equals(vehicleType)) {

                        final List<ParkingLot> parkingLots = parkingZone.getParkingLots();

                        final List<ParkingLot> pLots = new ArrayList<>();

                        for (ParkingLot parkingLot : parkingLots) {
                            if (ParkingLotStatus.FREE.equals(parkingLot.getParkingLotStatus())) {
                                pLots.add(parkingLot);
                            }
                        }

                        pZones.add(ParkingZone.builder()
                                .allocatedLots(parkingZone.getAllocatedLots())
                                .distanceFromEntrance(parkingZone.getDistanceFromEntrance())
                                .name(parkingZone.getName())
                                .parkingLots(pLots)
                                .vehicleType(parkingZone.getVehicleType())
                                .build());

                    }
                }
            }
        }

        /**
         * Sort pZons by distance
         */
        return pZones;

    }

    private void updateParkingLotStatus(String floor, String parkingZoneName, String parkingLotName) {

        final List<ParkingFloor> parkingFloors = this.parking.getParkingFloors();

        ParkingFloor parkingFloor = null;

        for (ParkingFloor pf : parkingFloors) {
            if (pf.getName().equalsIgnoreCase(floor)) {
                parkingFloor = pf;
                break;
            }
        }

        if (parkingFloor != null) {
            List<ParkingZone> parkingZones = parkingFloor.getParkingZones();

            if (parkingZones != null) {
                for (ParkingZone parkingZone : parkingZones) {
                    if (parkingZone.getName().equalsIgnoreCase(parkingZoneName)) {

                        parkingZone.setAllocatedLots(parkingZone.getAllocatedLots() + 1);

                        final List<ParkingLot> parkingLots = parkingZone.getParkingLots();


                        for (ParkingLot parkingLot : parkingLots) {
                            if (parkingLot.getName().equalsIgnoreCase(parkingLotName)) {
                                parkingLot.setParkingLotStatus(ParkingLotStatus.OCCUPIED);
                            }
                        }

                    }
                }
            }
        }

    }

    private Parking buildParking() {
        Entrance entrance = new Entrance("ID:1");

        ParkingFloor parkingFloor = new ParkingFloor("Ground_Floor", entrance, getParkingZone(4, VehicleType.TWO_WHEELER));
        parkingFloor.getParkingZones().addAll(getParkingZone(4, VehicleType.FOUR_WHEELER));

        List<ParkingFloor> parkingFloors = new ArrayList<>();

        parkingFloors.add(parkingFloor);

        Parking parking = new Parking(parkingFloors);

        return parking;
    }

    @Override
    public Parking getParking() {
        return this.parking;
    }

    private List<ParkingLot> getParkingLot(int size) {
        List<ParkingLot> parkingLots = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            parkingLots.add(new ParkingLot("ID-" + i, ParkingLotStatus.FREE, Double.valueOf(String.valueOf(i))));
        }

        return parkingLots;
    }

    private List<ParkingZone> getParkingZone(int size, VehicleType vehicleType) {
        List<ParkingZone> parkingZones = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            parkingZones.add(new ParkingZone(vehicleType, "ID-" + i, getParkingLot(5), Double.valueOf(String.valueOf(i)), 0));
        }

        return parkingZones;
    }

    private ParkingZone getParkingZone(ParkingLotRequest parkingLotRequest) {
        final List<ParkingFloor> parkingFloors = this.parking.getParkingFloors();
        ParkingFloor parkingFloor = null;

        for (ParkingFloor pf : parkingFloors) {
            if (pf.getName().equalsIgnoreCase(parkingLotRequest.getFloor())) {
                parkingFloor = pf;
                break;
            }
        }
        List<ParkingZone> pZones = new ArrayList<>();

        if (parkingFloor != null) {
            List<ParkingZone> parkingZones = parkingFloor.getParkingZones();

            if (parkingZones != null) {
                for (ParkingZone parkingZone : parkingZones) {
                    if (parkingZone.getName().equalsIgnoreCase(parkingLotRequest.getParkingZoneName())) {

                        return parkingZone;
                    }
                }
            }
        }
        return null;
    }
}
