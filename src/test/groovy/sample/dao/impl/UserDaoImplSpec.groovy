package sample.dao.impl

import sample.domain.user.User
import spock.lang.Specification
import spock.lang.Unroll

import javax.persistence.EntityManager
import javax.persistence.TypedQuery
import java.util.logging.Logger

class UserDaoImplSpec extends Specification {
    UserDaoImpl userDao

    def setup() {
        userDao = new UserDaoImpl()
        userDao.logger = Mock(Logger)
        userDao.em = Mock(EntityManager)
    }

    def "FindAll"() {
        when:
        1 * userDao.em.createNamedQuery("User.findAll", User.class) >> Mock(TypedQuery) {
            1 * getResultList() >> [new User(name: "test")]
        }

        then:
        userDao.findAll()[0].name == "test"
    }

    @Unroll
    def "AddUser(#beforeMail)"() {
        when:
        1 * userDao.em.createNamedQuery("User.countEmail", Long.class) >> Mock(TypedQuery) {
            1 * setParameter("email", "test@example.com") >> it
            1 * getSingleResult() >> 0L
        }

        then:
        userDao.addUser(new User(email: beforeMail)).email == afterMail

        where:
        beforeMail         | afterMail
        "test@example.com" | "test@example.com"
        "TEST@EXAMPLE.COM" | "test@example.com"
    }

    def "AddUser"() {
        when:
        1 * userDao.em.createNamedQuery("User.countEmail", Long.class) >> Mock(TypedQuery) {
            1 * setParameter("email", "test@example.com") >> it
            1 * getSingleResult() >> 1L
        }

        then:
        !userDao.addUser(new User(email: "test@example.com"))
    }
}
