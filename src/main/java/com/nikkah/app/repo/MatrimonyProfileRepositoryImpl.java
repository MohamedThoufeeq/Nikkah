package com.nikkah.app.repo;

import java.util.List;
import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.nikkah.app.model.MatrimonyProfile;

@Repository
public class MatrimonyProfileRepositoryImpl {

    private final EntityManager entityManager;

    public MatrimonyProfileRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<MatrimonyProfile> filterByField(String field, String value) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MatrimonyProfile> criteriaQuery = criteriaBuilder.createQuery(MatrimonyProfile.class);
        Root<MatrimonyProfile> root = criteriaQuery.from(MatrimonyProfile.class);

        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(criteriaBuilder.lower(root.get(field)), value.toLowerCase()));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    
    public List<MatrimonyProfile> filterByFields(Map<String, String> filterParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<MatrimonyProfile> criteriaQuery = criteriaBuilder.createQuery(MatrimonyProfile.class);
        Root<MatrimonyProfile> root = criteriaQuery.from(MatrimonyProfile.class);

        Predicate[] predicates = filterParams.entrySet().stream()
                .map(entry -> criteriaBuilder.equal(
                        criteriaBuilder.lower(root.get(entry.getKey())), entry.getValue().toLowerCase()))
                .toArray(Predicate[]::new);

        criteriaQuery.select(root).where(predicates);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
