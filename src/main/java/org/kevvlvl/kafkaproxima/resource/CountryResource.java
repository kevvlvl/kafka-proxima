package org.kevvlvl.kafkaproxima.resource;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.annotations.SseElementType;
import org.kevvlvl.kafkaproxima.util.Constants;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Slf4j
@Path("/countries")
public class CountryResource {

    /**
     * In-Memory reactive stream
     */
    @Inject
    @Channel("countries-data")
    Publisher<String> countries;

    @Inject
    @Channel("countries-update")
    Emitter<String> countryEmitter;

    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<String> stream() {
        return countries;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public void addCountry(String country) {

        log.info("addCountry() - Received country {}", country);
        countryEmitter.send(getCountryWithAction(Constants.ACTION_ADD, country));
    }

    @POST
    @Path("/remove")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public void removeCountry(String country) {

        log.info("removeCountry() - Received country {}", country);
        countryEmitter.send(getCountryWithAction(Constants.ACTION_REMOVE, country));
    }

    private String getCountryWithAction(String action, String country) {
        return action + Constants.ACTION_SEPARATOR + country;
    }
}
