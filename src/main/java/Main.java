import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Highway highway = new Highway();

        System.out.println("Welcome to the highway apllication!!");

        String komenda;
        do {
            System.out.println("What do you want to do? entry/departure/status");
            komenda = scanner.next().toUpperCase();
            try {
            Commands command = Commands.valueOf(komenda);

                System.out.println("Put registration number");
                String registrationNr = scanner.next().toUpperCase();

                switch (command) {
                    case ENTRY:
                        System.out.println("Put vehicle type" + Arrays.toString(CarType.values()));
                        String carT = scanner.next().toUpperCase();
                        VehicleInfo vehicleInfo = new VehicleInfo();

                        if (carT.equals("MOTORCYCLE")) {
                            vehicleInfo = new VehicleInfo(registrationNr, CarType.MOTORCYCLE);
                        } else if (carT.equals("CAR")) {
                            vehicleInfo = new VehicleInfo(registrationNr, CarType.CAR);
                        } else if (carT.equals("TRUCK")) {
                            vehicleInfo = new VehicleInfo(registrationNr, CarType.TRUCK);
                        } else {
                            System.out.println("Wrong vehicle type!");
                            break;
                        }
                        try {
                            highway.vehicleEntry(registrationNr, vehicleInfo);

                        } catch (VehicleAlreadyOnHighway vaoh) {
                            System.err.println(vaoh.getMessage());
                        }
                        break;

                    case DEPARTURE:
                        System.out.println("The trip cost is "+highway.vehicleDeparture(registrationNr));
                        break;
                    case STATUS:
                        System.out.println(highway.serachVehicle(registrationNr));
                        break;
                }

            } catch (IllegalArgumentException iae) {
                System.err.println("Wrong command..try again!\n");
            }

        } while (!komenda.equals("q"));


    }
}
