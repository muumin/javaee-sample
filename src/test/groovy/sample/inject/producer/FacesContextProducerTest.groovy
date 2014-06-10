package sample.inject.producer

import spock.lang.Specification

class FacesContextProducerTest extends Specification {
    def "GetFacesContext"() {
        expect:
        new FacesContextProducer().getFacesContext() == null
    }
}
