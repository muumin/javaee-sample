package sample.domain.user

import spock.lang.Specification

class UserSpec extends Specification {
    User user
    void setup() {
        user = new User();
    }

    def "Set/GetId"() {
        when:
        user.setId(1L)

        then:
        user.getId() == 1L
    }

    def "Set/GetName"() {
        when:
        user.setName("test")

        then:
        user.getName() == "test"
    }

    def "SetGetPasswd"() {
        when:
        user.setPasswd("test")

        then:
        user.getPasswd() == "test"
    }

    def "Set/GetEmail"() {
        when:
        user.setEmail("test")

        then:
        user.getEmail() == "test"
    }

    def "GetCrateDate"() {
        when:
        Date date = new Date();
        user.setCrateDate(date)

        then:
        user.getCrateDate() == date
    }

    def "SetGetVersion"() {
        when:
        user.setVersion(1)

        then:
        user.getVersion() == 1
    }
}
