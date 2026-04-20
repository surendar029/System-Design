package model;

import enums.SeatType;

import java.util.concurrent.atomic.AtomicBoolean;

public class Seat {
    private String seatID;
    private SeatType seatType;
    private AtomicBoolean isOccupied;
    private Passenger bookedPassenger;

    public Seat(String seatID,SeatType seatType){
        this.seatID=seatID;
        this.seatType=seatType;
        this.isOccupied=new AtomicBoolean(false);
    }

    public Ticket book(Passenger passenger){
        if(isOccupied.get()) return null;
        isOccupied.set(true);
        bookedPassenger=passenger;
        return new Ticket(passenger,this);
    }

}
