package sample.domain.user

import org.jboss.arquillian.container.test.api.Deployment
import org.jboss.shrinkwrap.api.Archive
import org.jboss.shrinkwrap.api.ShrinkWrap
import org.jboss.shrinkwrap.api.asset.EmptyAsset
import org.jboss.shrinkwrap.api.spec.WebArchive
import spock.lang.Specification
import spock.lang.Unroll

import javax.inject.Inject
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.UserTransaction
import javax.validation.ConstraintViolationException

//@RunWith(ArquillianSputnik)
class UserPersistenceSpec extends Specification {
    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(User.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
    }

    @PersistenceContext
    EntityManager em

    @Inject
    UserTransaction utx

//    @Unroll
//    def "#testCaseの場合「#message」エラーになることを確認する"() {
//        setup:
//        utx.begin()
//        em.joinTransaction()
//
//        when:
//        def user = newUser
//        user.crateDate = new Date()
//        em.persist(user);
//
//        then:
//        def e = thrown(ConstraintViolationException)
//        1 == e.constraintViolations.size()
//        e.constraintViolations[0].propertyPath.toString() == propertyName
//        e.constraintViolations[0].message == message
//
//        where:
//        newUser                                                           | propertyName | message                         | testCase
//        new User(name: null, passwd: "passwd", email: "test@example.com") | "name"       | "may not be null"               | "名前がnull"
////        new User(name: "", passwd: "passwd", email: "test@example.com")   | "name"       | "size must be between 1 and 50" | "名前が空文字"
////        new User(name: "a" * 51, passwd: "passwd", email: "test@example.com")     | "name"       | "size must be between 1 and 50"  | "名前の長さが51文字"
////        new User(name: "test name", passwd: null, email: "test@example.com")      | "passwd"     | "may not be null"                | "パスワードがnull"
////        new User(name: "test name", passwd: "", email: "test@example.com")        | "passwd"     | "size must be between 6 and 100" | "パスワードが空文字"
////        new User(name: "test name", passwd: "a" * 5, email: "test@example.com")   | "passwd"     | "size must be between 6 and 100" | "パスワードの長さが5文字"
////        new User(name: "test name", passwd: "a" * 101, email: "test@example.com") | "passwd"     | "size must be between 6 and 100" | "パスワードの長さが101文字"
////        new User(name: "test name", passwd: "passwd", email: null)                | "email"      | "may not be null"                | "メールがnull"
////        new User(name: "test name", passwd: "passwd", email: "")                  | "email"      | "size must be between 1 and 256" | "メールが空文字"
////        new User(name: "test name", passwd: "passwd", email: "a" * 257)           | "email"      | "size must be between 1 and 256" | "メールの長さが257文字"
//    }

}
