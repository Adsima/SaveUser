import dao.UserDao;
import model.Gender;
import model.User;

import java.time.LocalDate;

public class UserDemo {
    private UserDao userDao;

    private UserDemo() {
        userDao = UserDao.getInstance();

        userDao.saveUser(new User("Artem", "Nevada",
                LocalDate.parse("2000-01-01"), "England", Gender.MALE));
    }

    public static void main(String[] args) {
        new UserDemo();
    }
}
