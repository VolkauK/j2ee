package example.demo.service;

import example.demo.entity.DeviceEntity;
import example.demo.mapper.DeviceMapper;
import example.demo.model.Device;
import example.demo.repository.DeviceRepository;
import jakarta.ejb.Stateless;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import jakarta.inject.Inject;

import java.util.Optional;

@Stateless
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor(onConstructor = @__(@Inject))
public class DeviceService {

    @NonNull
    private final DeviceRepository deviceRepository;

    @NonNull
    private final DeviceMapper deviceMapper;


    public Optional<Device> getDeviceById(long id) {
        DeviceEntity deviceEntity = deviceRepository.findById(id);
        return Optional.ofNullable(deviceMapper.entityToModel(deviceEntity));
    }
}
