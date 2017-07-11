package com.gs.parkinglot.response;

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
public class ParkingLotResponse {

    private String invoice;

}
