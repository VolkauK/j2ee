package example.demo.model;

import example.demo.entity.DeviceStatus;
import lombok.Data;

@Data
public class Device {
    private long id;
    private String name;
    private String serialNumber;
    private DeviceStatus status;
}
