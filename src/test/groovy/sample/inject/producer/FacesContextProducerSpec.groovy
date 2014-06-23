package sample.inject.producer

import spock.lang.Specification

class FacesContextProducerSpec extends Specification {
    def "GetFacesContext"() {
        expect:
        new FacesContextProducer().getFacesContext() == null
    }
}
