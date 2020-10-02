package org.kevvlvl.kafkaproxima.resource;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.jboss.resteasy.annotations.SseElementType;
import org.kevvlvl.kafkaproxima.model.Price;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/prices")
public class PriceResource {

    /**
     * In-Memory reactive stream
     */
    @Inject
    @Channel("price-cad-data")
    Publisher<Price> prices;

    /**
     * @return the value of the stream my-data-stream to subscribers of this API's publisher
     */
    @GET
    @Path("/stream")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType("text/plain")
    public Publisher<Price> stream() {
        return prices;
    }
}
