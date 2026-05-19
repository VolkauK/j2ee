package example.demo.repository;

import example.demo.entity.DeviceEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import jakarta.inject.Inject;

@ApplicationScoped
public class DeviceRepository extends BaseRepository<DeviceEntity, Long> {

    protected DeviceRepository() {
        super();
    }

    @Inject
    public DeviceRepository(EntityManager em) {
        super(DeviceEntity.class, em);
    }
}
