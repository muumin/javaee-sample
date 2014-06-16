package sample.domain.user;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class UserPersistenceTest {

    @Deployment
    public static Archive<?> createDeployment() {
//        WebArchive arch = ShrinkWrap.create(WebArchive.class, "test.war")
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(User.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("jbossas-ds.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//        System.out.println(arch.toString(true));
//        return arch;
    }

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction utx;

    @Before
    public void setUp() throws Exception {
        startTransaction();
    }

    private void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    @After
    public void tearDown() throws Exception {
//        utx.commit();
    }

    @Test
    // 例外が発生したかのテストだけなら以下で問題無いがこの場合
    // 例外対象のフィールドとメッセージもチェックするのでtry-catchしている
    // @Test(expected = ConstraintViolationException.class)
    public void userErrorTest() {
        User user = new User();
        user.setPasswd("passwd");
        user.setEmail("test@example.com");
        user.setCrateDate(new Date());
        try {
            assertThat(em, notNullValue());
            assertThat(utx, notNullValue());
            em.persist(user);
        } catch (ConstraintViolationException ex) {
            assertEquals(ex.getConstraintViolations().size(), 1);
            for (ConstraintViolation v : ex.getConstraintViolations()) {
                assertThat(v.getPropertyPath().toString(), is("name"));
                assertThat(v.getMessage(), is("may not be null"));
            }
        }
    }
}