package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private final static UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        userService.createUsersTable();

        userService.saveUser("Иван","Фёдоров",(byte) 50);
        userService.saveUser("Пётр","Петров",(byte) 41);
        userService.saveUser("Света","Светикова",(byte) 34);
        userService.saveUser("Паша","Смирнов",(byte) 26);

        userService.removeUserById(2);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }

}


