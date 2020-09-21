package org.kevvlvl.kafkaproxima.stream;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.kevvlvl.kafkaproxima.service.CountryService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class CountryStream {

    private CountryService countryService;

    @Inject
    public CountryStream(CountryService countryService) {
        this.countryService = countryService;
    }

    @Incoming("countries-create")
    public void processCountryAdd(String country) {

        log.info("processCountryAdd() - country to add {}", country);
        this.countryService.add(country);

        updateCountriesTopic();
    }

    @Incoming("countries-delete")
    public void processCountryDelete(String country) {

        log.info("processCountryDelete() - country to delete {}", country);
        this.countryService.remove(country);

        updateCountriesTopic();
    }

    @Outgoing("countries-generator")
    public String updateCountriesTopic() {
        return this.countryService.getCountriesList();
    }

    @Incoming("countries-generator")
    @Outgoing("countries-data")
    @Broadcast
    public String process(String countries) {

        return countries;
    }
}
