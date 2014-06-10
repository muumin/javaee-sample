package sample.inject.producer

import spock.lang.Specification

class EntityManagerProducerTest extends Specification {
    def "new"() {
        expect:
        new EntityManagerProducer().em == null
    }
}
