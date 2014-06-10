package sample.controller;

import sample.dao.UserDao;
import sample.domain.user.User;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

@Named(value = "userController")
@RequestScoped
public class UserController {
    @Inject
    transient Logger logger;

    @Inject
    private UserDao userDao;

    @Inject
    private FacesContext facesContext;

    private List<User> userList;

    private User user = new User();

    private UIComponent emailComponent;

    public UserController() {
    }

    @PostConstruct
    public void init() {
        userList = userDao.findAll();
    }

    public List<User> getUserList() {
        return userList;
    }

    public User getUser() {
        return user;
    }

    public UIComponent getEmailComponent() {
        return emailComponent;
    }

    public void setEmailComponent(UIComponent emailComponent) {
        this.emailComponent = emailComponent;
    }

    public String create() {
        if (userDao.addUser(user) == null) {
            // <h:message for="email"/>へのメッセージのつもりでaddMessage("email", new FacesMessage(...))はアカンらしい
            facesContext.addMessage(emailComponent.getClientId(),
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "e-mail is already in use.", null));
            return "index.xhtml";
        }
        return "index.xhtml?faces-redirect=true";
    }
}
