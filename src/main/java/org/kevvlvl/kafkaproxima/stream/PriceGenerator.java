package org.kevvlvl.kafkaproxima.stream;

import io.reactivex.Flowable;
import org.eclipse.microprofile.reactive.messaging.Outgoing;

import javax.enterprise.context.ApplicationScoped;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        return Flowable.interval(5, TimeUnit.SECONDS)
                .map(tick -> random.nextInt(100));
    }
}
