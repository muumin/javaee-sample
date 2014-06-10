package sample.inject.producer;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@Dependent
public class FacesContextProducer {
    @Produces
    @RequestScoped
    FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
