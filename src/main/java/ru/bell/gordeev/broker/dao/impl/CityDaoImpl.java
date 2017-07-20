package ru.bell.gordeev.broker.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.bell.gordeev.broker.dao.CityDao;
import ru.bell.gordeev.broker.domain.Weather;

/**
 * Created by EGordeev on 07.07.2017.
 */

@Repository(value = "dao")
@Transactional
class CityDaoImpl implements CityDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Weather showWeatherForCity(String city) {
        return sessionFactory.getCurrentSession().get(Weather.class, city);
    }

    public void updateWeatherForCity(Weather localWeather) {
        sessionFactory.getCurrentSession().saveOrUpdate(localWeather);
    }
}
