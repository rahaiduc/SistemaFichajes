package com.sistemafichajes.repository;

import com.sistemafichajes.controller.dto.outputs.PersonOutputDto;
import com.sistemafichajes.domain.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Pageable;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PersonRepositoryImpl implements PersonRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;

    public List<PersonOutputDto> getGreaterQuery(
            HashMap<String, Object> conditions, Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    predicates.add(cb.greaterThan(root.get(field),(String) conditions.get("usuario")));
                    break;
                case "name":
                    predicates.add(cb.greaterThan(root.get(field),(String) conditions.get("name")));
                    break;
                case "surname":
                    predicates.add(cb.greaterThan(root.get(field),(String) conditions.get("surname")));

                    break;
                case "createdDate":
                    predicates.add(cb.greaterThan(root.get(field),(Date) conditions.get("createdDate")));
                    break;
            }
        });
        if(conditions.get("orderBy").equals("usuario")) {
            query.select(root)
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("usuario")) : cb.asc(root.get("usuario")));
        }else {
            query.select(root)
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("name")) : cb.asc(root.get("name")));
        }
        return entityManager
                .createQuery(query).setFirstResult((pageable.getPageNumber() * pageable.getPageSize())+1).setMaxResults(pageable.getPageSize())
                .getResultList()
                .stream()
                .map(Persona::personToPersonOutputDto)
                .toList();

    }


    public List<PersonOutputDto> getLessQuery(
            HashMap<String, Object> conditions,Pageable pageable) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Persona> query = cb.createQuery(Persona.class);
        Root<Persona> root = query.from(Persona.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "usuario":
                    predicates.add(cb.lessThan(root.get(field),(String) conditions.get("usuario")));
                    break;
                case "name":
                    predicates.add(cb.lessThan(root.get(field),(String) conditions.get("name")));
                    break;
                case "surname":
                    predicates.add(cb.lessThan(root.get(field),(String) conditions.get("surname")));

                    break;
                case "createdDate":
                    predicates.add(cb.lessThan(root.get(field),(Date) conditions.get("createdDate")));
                    break;
            }
        });
        if(conditions.get("orderBy").equals("usuario")) {
            query.select(root)
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("usuario")) : cb.asc(root.get("usuario")));
        }else{
            query.select(root)
                    .where(predicates.toArray(new Predicate[predicates.size()]))
                    .orderBy(conditions.get("orderByDirection").equals("desc") ? cb.desc(root.get("name")) : cb.asc(root.get("name")));
        }
        return entityManager
                .createQuery(query).setFirstResult((pageable.getPageNumber() * pageable.getPageSize())+1).setMaxResults(pageable.getPageSize())
                .getResultList()
                .stream()
                .map(Persona::personToPersonOutputDto)
                .toList();
    }

}
