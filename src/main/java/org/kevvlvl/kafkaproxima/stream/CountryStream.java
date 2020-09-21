package org.kevvlvl.kafkaproxima.stream;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.kevvlvl.kafkaproxima.service.CountryService;
import org.kevvlvl.kafkaproxima.util.Constants;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Slf4j
@ApplicationScoped
public class CountryStream {

    private final CountryService countryService;

    @Inject
    public CountryStream(CountryService countryService) {
        this.countryService = countryService;
    }

    @Incoming("countries-update")
    @Outgoing("countries-generator")
    @Broadcast
    public String processCountryChange(String country) {

        log.info("processCountryChange() - action and country to manage {}", country);

        String[] actionCountry = country.split(Constants.ACTION_SEPARATOR);
        if(actionCountry[0].equalsIgnoreCase(Constants.ACTION_ADD)) {
            this.countryService.add(actionCountry[1]);
        }
        else if(actionCountry[0].equalsIgnoreCase(Constants.ACTION_REMOVE)) {
            this.countryService.remove(actionCountry[1]);
        }
        else {
            log.warn("Unexpected action {} to apply to country {}", actionCountry[0], actionCountry[1]);
        }

        return this.countryService.getCountriesList();
    }

    @Incoming("countries-generator")
    @Outgoing("countries-data")
    @Broadcast
    public String process(String countries) {

        return countries;
    }
}
