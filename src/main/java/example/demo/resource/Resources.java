package example.demo.resource;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@ApplicationScoped
public class Resources {

    @PersistenceContext(unitName = "AssetManagerPU")
    private EntityManager em;

    @Produces
    public EntityManager getEntityManager() {
        return em;
    }
}