package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

//  private static UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();
  private static UserDaoHibernateImpl userDaoHiber = new UserDaoHibernateImpl();

    public void createUsersTable() {

//        userDao.createUsersTable();
        userDaoHiber.createUsersTable();
    }

    public void dropUsersTable() {

//      userDao.dropUsersTable();
      userDaoHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {

//     userDao.saveUser(name, lastName, age);
      userDaoHiber.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
//      userDao.removeUserById(id);
      userDaoHiber.removeUserById(id);

    }

    public List<User> getAllUsers() {
//      List<User> users = userDao.getAllUsers();
//      for (User u : users) {
//        System.out.println(u);}
//      return users;}

      List<User> users = userDaoHiber.getAllUsers();
      for (User u : users) {
        System.out.println(u);}
      return users;}



    public void cleanUsersTable() {
//      userDao.cleanUsersTable();
      userDaoHiber.cleanUsersTable();

    }
}
