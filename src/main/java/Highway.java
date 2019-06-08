import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Highway {
    Map<String, VehicleInfo> carsOnHighway = new HashMap<>();

    //     - metodę vehicleEntry(String numer_rejestracyjny, oraz CarType type) - która dodaje do kolekcji nową instancję VehicleInfo
//    oraz wypisuje do konsoli komunikat. Metoda nic nie zwraca.
    void vehicleEntry(String registrationNr, VehicleInfo vehicleInfo) throws VehicleAlreadyOnHighway {

        if (carsOnHighway.containsKey(registrationNr)) {
            throw new VehicleAlreadyOnHighway();
        }
        carsOnHighway.put(registrationNr, vehicleInfo);
        LocalDateTime entryTime = LocalDateTime.now();
        vehicleInfo.setEntryTime(entryTime);
        System.out.println("The vehicle " + registrationNr + " has entry the highway.");



    }

    //    - metodę searchVehicle(String numer_rejestracyjny) - która szuka pojazdu i wypisuje jego informacje jeśli wciąż
//    znajduje się na autostradzie
    VehicleInfo serachVehicle(String registrationNr) {
        if (carsOnHighway.containsKey(registrationNr)) {
            return carsOnHighway.get(registrationNr);
        } else {
            System.out.println("Searched vehicle is not on the highway.");
            return null;
        }
    }

    //     - metodę wyjazdu - vehicleLeave(String numer_rejestracyjny) - która usuwa samochód z kolekcji, wypisuje komunikat,
//    oraz na podstawie czasu jaki samochód znajdował się na autostradzie oblicza jej kwotę do zapłaty i wypisuje ją do konsoli.
    double vehicleDeparture(String registrationNr) {
        if (!carsOnHighway.containsKey(registrationNr)) {
            System.out.println("There is no such car on the highway!");
            return 0;

        } else {

            VehicleInfo leavingVehicle = carsOnHighway.get(registrationNr);
            //pobranie czasu wjazdu pojazdu i ustawienie na Timestamp
            Timestamp tEntry = Timestamp.valueOf(leavingVehicle.getEntryTime());
            //ustalenie czasu wyjazdu pojazdu i ustawienie go na Timestamp
            LocalDateTime departureTime = LocalDateTime.now();
            Timestamp tDepature = Timestamp.valueOf(departureTime);
            //czas jazdy
            Long tripPeriod = tDepature.getTime() - tEntry.getTime();
            carsOnHighway.remove(registrationNr);

            //ustalenie ceny za sekundę spędzoną na autorstradzie
            Double sekPrice = 0d;
            switch (leavingVehicle.getCarType()) {
                case CAR:
                    sekPrice = 0.02;
                    break;
                case TRUCK:
                    sekPrice = 0.03;
                    break;
                case MOTORCYCLE:
                    sekPrice = 0.01;
                    break;
                default:
                    break;
            }
            Double tripCost = sekPrice * tripPeriod;

            return tripCost;

        }
    }

}