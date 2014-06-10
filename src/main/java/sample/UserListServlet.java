package sample;

import com.google.common.base.Joiner;
import sample.dao.UserDao;
import sample.domain.user.User;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    transient Logger logger;

    @Inject
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        List<User> userList = userDao.findAll();
        logger.log(Level.INFO, "User size = " + userList.size());
        List<String> userNameList = new ArrayList<>();
        for (User u : userList) {
            userNameList.add(u.getName() + "&lt;" + u.getEmail() + "&gt;");
        }
        res.setContentType("text/html");
        res.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = res.getWriter();
        writer.println("<h1>User List</h1>");
        if (userList.size() != 0) {
            Joiner joiner = Joiner.on("</p><p>").skipNulls();
            writer.println("<p>" + joiner.join(userNameList) + "</p>");
        } else {
            writer.println("<p>user not found .</p>");
        }
        writer.flush();
    }
}
