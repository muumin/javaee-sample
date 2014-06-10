package sample.dao;

import sample.domain.user.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();

    User addUser(User user);
}
