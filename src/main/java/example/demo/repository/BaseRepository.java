package example.demo.repository;

import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public abstract class BaseRepository<T, ID> {

    @NonNull
    private final Class<T> entityType;

    @NonNull
    private final EntityManager em;

    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    public T findById(ID id) {
        return em.find(entityType, id);
    }

    public List<T> findAll() {
        String jpql = "FROM " + entityType.getSimpleName();
        return em.createQuery(jpql, entityType)
                .getResultList();
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void delete(ID id) {
        T entity = em.find(entityType, id);
        if (Objects.nonNull(entity)) {
            em.remove(entity);
        }
    }
}
