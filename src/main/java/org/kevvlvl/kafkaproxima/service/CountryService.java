package org.kevvlvl.kafkaproxima.service;

import lombok.Getter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class CountryService {

    private List<String> countriesList;

    @PostConstruct
    private void init() {
        this.countriesList = new ArrayList<>();
    }

    @PreDestroy
    private void destroy() {
        this.countriesList.clear();
    }

    public void add(String country) {
        this.countriesList.add(country);
    }

    public void remove(String country) {
        this.countriesList.remove(country);
    }

    public String getCountriesList() {
        return String.join(",", this.countriesList);
    }
}
