package example.demo.mapper;

import example.demo.entity.DeviceEntity;
import example.demo.model.Device;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DeviceMapper {

    public Device entityToModel(DeviceEntity entity) {
        if (entity == null) {
            return null;
        }

        Device device = new Device();
        device.setId(entity.getId());
        device.setName(entity.getName());
        device.setSerialNumber(entity.getSerialNumber());
        device.setStatus(entity.getStatus());
        return device;
    }

    public DeviceEntity modelToEntity(Device device) {
        if (device == null) {
            return null;
        }

        DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setId(device.getId());
        deviceEntity.setName(device.getName());
        deviceEntity.setSerialNumber(device.getSerialNumber());
        deviceEntity.setStatus(device.getStatus());
        return deviceEntity;
    }
}
