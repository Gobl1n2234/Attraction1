package com.example.app.attraction.repository.custom.impl;

import com.example.app.attraction.entity.Attraction;
import com.example.app.attraction.entity.Category;
import com.example.app.attraction.entity.City;
import com.example.app.attraction.repository.custom.AttractionRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class AttractionRepositoryCustomImpl implements AttractionRepositoryCustom {

    private final EntityManager em;


    public AttractionRepositoryCustomImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Attraction> findNearest(Integer count, Double latitude, Double longitude, Double distance) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attraction> cq = cb.createQuery(Attraction.class);
        Root<Attraction> attraction = cq.from(Attraction.class);
        attraction.fetch("ratings", JoinType.LEFT);
        cq.select(attraction);
        cq.where(cb.between(attraction.get("latitude"), latitude - distance, latitude + distance),
                cb.between(attraction.get("longitude"), longitude - distance, longitude + distance));

        return em.createQuery(cq).setFirstResult(10).setMaxResults(count).getResultList();
    }

    @Override
    public List<Attraction> findNearFilterByCategory(Integer count, Double latitude, Double longitude, Double distance, String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attraction> cq = cb.createQuery(Attraction.class);
        Root<Attraction> attraction = cq.from(Attraction.class);
        Join<Attraction, Category> joinAttractionToCategory = attraction.join("category");
        cq.select(attraction);
        cq.where(cb.between(attraction.get("latitude"), latitude - distance, latitude + distance),
                cb.between(attraction.get("longitude"), longitude - distance, longitude + distance),
                cb.like(joinAttractionToCategory.get("name"), "%" + category + "%"));

        return em.createQuery(cq).setFirstResult(10).setMaxResults(count).getResultList();
    }

    @Override
    public List<Attraction> findNearByCity(Integer count, Double latitude, Double longitude, Double distance, String city) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attraction> cq = cb.createQuery(Attraction.class);
        Root<Attraction> attraction = cq.from(Attraction.class);
        Join<Attraction, City> joinAttractionToCity = attraction.join("city");
        cq.select(attraction);
        cq.where(cb.between(attraction.get("latitude"), latitude - distance, latitude + distance),
                cb.between(attraction.get("longitude"), longitude - distance, longitude + distance),
                cb.like(joinAttractionToCity.get("name"), "%" + city + "%"));

        return em.createQuery(cq).setFirstResult(10).setMaxResults(count).getResultList();
    }

    @Override
    public List<Attraction> findNearByCityFilterByCategory(Integer count, Double latitude, Double longitude, Double distance, String city, String category) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Attraction> cq = cb.createQuery(Attraction.class);
        Root<Attraction> attraction = cq.from(Attraction.class);
        Join<Attraction, City> joinAttractionToCity = attraction.join("city");
        Join<Attraction, Category> joinAttractionToCategory = attraction.join("category");
        cq.select(attraction);
        cq.where(cb.between(attraction.get("latitude"), latitude - distance, latitude + distance),
                cb.between(attraction.get("longitude"), longitude - distance, longitude + distance),
                cb.like(joinAttractionToCity.get("name"), "%" + city + "%"),
                cb.like(joinAttractionToCategory.get("name"), "%" + category + "%"));

        return em.createQuery(cq).setFirstResult(10).setMaxResults(count).getResultList();
    }

}
