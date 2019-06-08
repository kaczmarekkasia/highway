import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor


public class VehicleInfo {
    private String registrationNr;
    private CarType carType;
    private LocalDateTime entryTime;

    public VehicleInfo(String registrationNr, CarType carType) {
        this.registrationNr = registrationNr;
        this.carType = carType;
        this.entryTime = null;
    }
}
