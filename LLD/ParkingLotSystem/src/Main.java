import enums.PaymentMode;
import enums.VehicleSize;
import factories.VehicleFactory;
import model.ParkingTicket;
import model.Vehicle;
import service.ParkingLotSystem;

public class Main {
    public static void main(String[] args) {
        ParkingLotSystem parkingLotSystem = ParkingLotSystem.getInstance();
        parkingLotSystem.createBuilder()
                .addFloor(0)
                .addSpot("0-A", VehicleSize.SMALL)
                .addSpot("0-B", VehicleSize.MEDIUM)
                .addSpot("0-C", VehicleSize.LARGE)
                .addFloor(1)
                .addSpot("1-A", VehicleSize.MEDIUM)
                .addSpot("1-B", VehicleSize.SMALL)
                .addSpot("1-C", VehicleSize.MEDIUM)
                .buildAndAddTo(parkingLotSystem);
        parkingLotSystem.printFloors();

        try{
//            Vehicle vehicle1= VehicleFactory.createVehicle("QWE_QWWW",VehicleSize.SMALL);
//            ParkingTicket ticket1=parkingLotSystem.parkVehicle(vehicle1);
//
//            Vehicle vehicle2= VehicleFactory.createVehicle("EWE_EWWW",VehicleSize.LARGE);
//            ParkingTicket ticket2=parkingLotSystem.parkVehicle(vehicle2);
//
//            Vehicle vehicle3= VehicleFactory.createVehicle("RWE_RWWW",VehicleSize.LARGE);
//            ParkingTicket ticket3=parkingLotSystem.parkVehicle(vehicle3);
//
//            parkingLotSystem.printFloors();
//
//            Thread.sleep(5000);
//
//            parkingLotSystem.unParkVehicle(ticket1.getTicketID(), PaymentMode.CASH);
//            parkingLotSystem.unParkVehicle(ticket2.getTicketID(), PaymentMode.CARD);

            Vehicle vehicle1= VehicleFactory.createVehicle("TRUCK_EWWW",VehicleSize.LARGE);
            Vehicle vehicle2= VehicleFactory.createVehicle("TRUCK_RWWW",VehicleSize.LARGE);

            Thread t1=new Thread(()->parkingLotSystem.parkVehicle(vehicle1));
            Thread t2=new Thread(()->parkingLotSystem.parkVehicle(vehicle2));

            t1.start();
            t2.start();
            t1.join();
            t2.join();
            parkingLotSystem.printFloors();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
