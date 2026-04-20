package model;

import enums.VehicleSize;

import java.util.concurrent.atomic.AtomicBoolean;

public class ParkingSpot {
    private String spotID;
    private VehicleSize spotSize;
    private AtomicBoolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotID, VehicleSize spotSize) {
        this.spotID = spotID;
        this.spotSize = spotSize;
        this.isOccupied = new AtomicBoolean(false);
    }

    public String getSpotID() {
        return spotID;
    }

    public VehicleSize getSpotSize() {
        return spotSize;
    }

    public boolean isOccupied() {
        return isOccupied.get();
    }

    public synchronized ParkingTicket parkVehicle(Vehicle vehicle) {
        if (isOccupied.get() || vehicle.getVehicleSize() != spotSize) return null;
        parkedVehicle = vehicle;
        isOccupied.set(true);
        return new ParkingTicket(vehicle, this);
    }

    public void unParkVehicle() {
        parkedVehicle = null;
        isOccupied.set(false);
    }

    public boolean canFitVehicle(Vehicle vehicle) {
        return !isOccupied.get() && vehicle.getVehicleSize() == spotSize;
    }
}
