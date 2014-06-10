package sample.domain.user

import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.EntityManager
import javax.persistence.Persistence
import javax.validation.ConstraintViolationException

class UserSpec extends Specification {
    private EntityManager em

    User user

    void setup() {
        user = new User();
        em = Persistence.createEntityManagerFactory("javaee_example").createEntityManager();
    }

    void cleanup() {
        em.close()
    }

    @Unroll
    def "#testCaseの場合「#message」エラーになることを確認する"() {
        setup:
        em.getTransaction().begin();

        when:
        user = newUser
        user.crateDate = new Date()

        em.persist(user);

        then:
        def e = thrown(ConstraintViolationException)
        1 == e.constraintViolations.size()
        e.constraintViolations[0].propertyPath.toString() == propertyName
        e.constraintViolations[0].message == message

        cleanup:
        em.getTransaction().rollback()

        where:
        newUser                                                                   | propertyName | message                          | testCase
        new User(name: null, passwd: "passwd", email: "test@example.com")         | "name"       | "may not be null"                | "名前がnull"
        new User(name: "", passwd: "passwd", email: "test@example.com")           | "name"       | "size must be between 1 and 50"  | "名前が空文字"
        new User(name: "a" * 51, passwd: "passwd", email: "test@example.com")     | "name"       | "size must be between 1 and 50"  | "名前の長さが51文字"
        new User(name: "test name", passwd: null, email: "test@example.com")      | "passwd"     | "may not be null"                | "パスワードがnull"
        new User(name: "test name", passwd: "", email: "test@example.com")        | "passwd"     | "size must be between 6 and 100" | "パスワードが空文字"
        new User(name: "test name", passwd: "a" * 5, email: "test@example.com")   | "passwd"     | "size must be between 6 and 100" | "パスワードの長さが5文字"
        new User(name: "test name", passwd: "a" * 101, email: "test@example.com") | "passwd"     | "size must be between 6 and 100" | "パスワードの長さが101文字"
        new User(name: "test name", passwd: "passwd", email: null)                | "email"      | "may not be null"                | "メールがnull"
        new User(name: "test name", passwd: "passwd", email: "")                  | "email"      | "size must be between 1 and 256" | "メールが空文字"
        new User(name: "test name", passwd: "passwd", email: "a" * 257)           | "email"      | "size must be between 1 and 256" | "メールの長さが257文字"
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
