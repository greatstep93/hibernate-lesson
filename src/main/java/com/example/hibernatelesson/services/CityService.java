package com.example.hibernatelesson.services;

import com.example.hibernatelesson.models.hibernate.City;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CityService {

    private final EntityManager em;

    @Transactional
    public void saveNewCity(String name) {

        //Transient
        City city = new City();
        city.setName(name);

        //Managed
        em.persist(city);

        //Detached
        em.detach(city);

        city.setName("New-" + name);

        //Managed
        em.merge(city);

        em.flush();

        //Removed
        em.remove(city);

    }
}
