package com.gs.parkinglot.service;

import com.gs.parkinglot.pojo.Membership;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by govinda.v on 10/07/17.
 * We can use database here
 */
public class MembershipServiceImpl implements IMembershipService {
    private List<Membership> memberships;

    public MembershipServiceImpl() {
        this.memberships = new ArrayList<>();
    }

    @Override
    public void addMembership(Membership membership) {
        if (membership != null) {
            memberships.add(membership);
        }
    }

    @Override
    public Membership get(String name) {
        for (Membership membership : this.memberships) {
            if (membership.getName().equalsIgnoreCase(name)) {
                return membership;
            }
        }
        return null;
    }

}
