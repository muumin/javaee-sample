package sample.controller

import sample.dao.UserDao
import sample.domain.user.User
import spock.lang.Specification

import javax.faces.application.FacesMessage
import javax.faces.component.UIComponent
import javax.faces.context.FacesContext

class UserControllerSpec extends Specification {
    def userController = new UserController()
    def list = [
            new User(name: "yamada",
                    email: "test@exsample.com",
                    passwd: "testtest"
            ),
            new User(name: "sato",
                    email: "test1@exsample.com",
                    passwd: "testtest1"
            )
    ]

    def setup() {
        userController.userDao = Mock(UserDao)

        UIComponent component = Mock(UIComponent)
        component.getClientId() >> "clientId"
        userController.setEmailComponent(component)

        userController.user = new User(name: "takahashi",
                email: "test2@exsample.com",
                passwd: "testtest2"
        )

        userController.facesContext = Mock(FacesContext)
    }

    def "GetUserList"() {
        when:
        userController.init()
        def userList = userController.getUserList()

        then:
        1 * userController.userDao.findAll() >> list
        userList.size() == 2
        userList[0] == list[0]
        userList[1] == list[1]
    }

    def "GetUser"() {
        when:
        User user = userController.getUser()

        then:
        user.name == "takahashi"
        user.email == "test2@exsample.com"
        user.passwd == "testtest2"
    }

    def "GetEmailComponent"() {
        expect:
        userController.getEmailComponent().getClientId() == "clientId"
    }

    def "Create"() {
        when:
        def ret = userController.create()

        then:
        ret == "index.xhtml?faces-redirect=true"
        1 * userController.userDao.addUser(_) >> new User()
        0 * userController.facesContext.addMessage(*_)
    }

    def "e-mail is already in use"() {
        when:
        1 * userController.userDao.addUser(_) >> null
        def ret = userController.create()

        then:
        ret == "index.xhtml"
        1 * userController.facesContext.addMessage(*_) >> {
            String clientId, FacesMessage msg ->
                assert msg.getDetail() == "e-mail is already in use."
        }
    }

}
