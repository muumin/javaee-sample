package sample.inject.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
@Dependent
public class EntityManagerProducer {
    @PersistenceContext(unitName = "javaee_example")
    @Produces
    private EntityManager em;
}
