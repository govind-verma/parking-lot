package com.gs.parkinglot.service;

import com.gs.parkinglot.pojo.Membership;

/**
 * Created by govinda.v on 10/07/17.
 */
public interface IMembershipService {

    void addMembership(Membership membership);

    Membership get(String name);

}
