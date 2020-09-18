package org.kevvlvl.kafkaproxima.stream;

import io.smallrye.reactive.messaging.annotations.Broadcast;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;

@Slf4j
@ApplicationScoped
public class CountryStream {

    @Incoming("countries-create")
    @Outgoing("countries-generator")
    @Broadcast
    public String processNewCountry(String country) {

        log.info("processNewCountry() - country to add {}", country);

        return country;
    }

    @Incoming("countries-generator")
    @Outgoing("countries-data")
    @Broadcast
    public String process(String country) {

        log.info("process() - country {}", country);

        return country;
    }
}
