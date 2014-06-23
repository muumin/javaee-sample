package sample.inject.producer

import spock.lang.Specification

class EntityManagerProducerSpec extends Specification {
    def "new"() {
        expect:
        new EntityManagerProducer().em == null
    }
}
