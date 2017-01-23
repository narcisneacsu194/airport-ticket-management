package com.teamtreehouse.airport.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String tripReason;

    @NotNull
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Trip> tripList;

    @NotNull
    private Map<Integer, String> passengerMap;

    @NotNull
    private String paymentMethod;

    public Booking(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTripReason() {
        return tripReason;
    }

    public void setTripReason(String tripReason) {
        this.tripReason = tripReason;
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public Map<Integer, String> getPassengerMap() {
        return passengerMap;
    }

    public void setPassengerMap(Map<Integer, String> passengerMap) {
        this.passengerMap = passengerMap;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
