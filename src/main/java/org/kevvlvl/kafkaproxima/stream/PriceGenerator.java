package org.kevvlvl.kafkaproxima.stream;

import io.reactivex.Flowable;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@ApplicationScoped
public class PriceGenerator {

    private Random random = new Random();

    /**
     * Generates a random number every 5 seconds, to be written in the topic name configured as "generated-price"
     * See mp.messaging.outgoing.generated-price.topic in application.properties
     * @return
     */
    @Outgoing("generated-price")
    public Flowable<Integer> generate() {

        log.info("generate() - Initiate generation of a random number");

        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(100));
    }
}
