package bankymono.reactive.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
public class MonoTest {

    @Test
    public void monoSubscriber() {
        String name = "Bankole Ayodeji";
        Mono<String> mono = Mono.just(name)
                .log();
        mono.subscribe();
        log.info("------------------------------");
        StepVerifier.create(mono)
                        .expectNext(name)
                                .verifyComplete();
//        log.info("Mono -> {}", mono);
        log.info("Working as intended");
    }

    @Test
    public void monoSubscriberConsumer() {
        String name = "Bankole Ayodeji";
        Mono<String> mono = Mono.just(name)
                .log();
        mono.subscribe((val) -> log.info("value -> {}", val));
        log.info("------------------------------");
//        StepVerifier.create(mono)
//                .expectNext(name)
//                .verifyComplete();
//        log.info("Mono -> {}", mono);
        log.info("Working as intended");
    }

    @Test
    public void monoSubscriberConsumerError() {
        String name = "Bankole Ayodeji";

        Mono<String> mono = Mono.just(name)
                .map(s -> {
                    throw new RuntimeException("Testing mono with error");
                });

        mono.subscribe((val) -> log.info("value -> {}", val), s -> log.error("Something Bad happend"));
        log.info("------------------------------");
        StepVerifier.create(mono)
//                .expectNext(name)
                .expectError(RuntimeException.class)
                .verify();
//        log.info("Mono -> {}", mono);
        log.info("Working as intended");
    }
}
