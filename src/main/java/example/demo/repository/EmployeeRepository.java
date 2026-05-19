package example.demo.repository;

import example.demo.entity.EmployeeEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;

import javax.inject.Inject;

@ApplicationScoped
public class EmployeeRepository extends BaseRepository<EmployeeEntity, Long> {

    protected EmployeeRepository() {
        super();
    }

    @Inject
    public EmployeeRepository(EntityManager em) {
        super(EmployeeEntity.class, em);
    }
}
