package example.demo.repository;

import example.demo.entity.DeviceAssignmentEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import javax.inject.Inject;

@ApplicationScoped
public class DeviceAssignmentRepository extends BaseRepository<DeviceAssignmentEntity, Long> {

    protected DeviceAssignmentRepository() {
        super();
    }

    @Inject
    public DeviceAssignmentRepository(EntityManager em) {
        super(DeviceAssignmentEntity.class, em);
    }
}
