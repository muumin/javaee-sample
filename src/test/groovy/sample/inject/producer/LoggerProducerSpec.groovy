package sample.inject.producer

import spock.lang.Specification

import javax.enterprise.inject.spi.InjectionPoint
import java.lang.reflect.Member

class LoggerProducerSpec extends Specification {
    def "GetLogger"() {
        when:
        InjectionPoint injectionPoint = Mock(InjectionPoint) {
            1 * getMember() >> Mock(Member) {
                1 * getDeclaringClass() >> LoggerProducerSpec.class
            }
        }

        then:
        new LoggerProducer().getLogger(injectionPoint).getName() == LoggerProducerSpec.class.getName()
    }
}
