package servlet;

import dao.UserDao;
import model.Gender;
import model.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@WebServlet(name = "user", urlPatterns = "/user")
public class UserServlet extends HttpServlet {

    private UserDao userDao;
    private DateTimeFormatter formatter;
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        userDao = UserDao.getInstance();
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Method: doGet");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String requestURI = req.getRequestURI();

        saveUserDB(req);

        String params = formatParams(req);
        resp.getWriter().write("Method doPost\nURI: " + requestURI + "\nParams:\n" + params + "\n");
    }

    private void saveUserDB(HttpServletRequest req) {
        String name = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        LocalDate dateOfBirth = getBirthday(req);
        String country = req.getParameter("country");
        Gender gender = getGender(req);

        userDao.saveUser(new User(name, lastName, dateOfBirth, country, gender));
    }

    private LocalDate getBirthday(HttpServletRequest req) {
        String dateOfBirth = req.getParameter("bday");
        return LocalDate.parse(dateOfBirth, formatter);
    }

    private Gender getGender(HttpServletRequest req) {
        String gender = req.getParameter("gender").toUpperCase();
        if (gender.equals(Gender.FEMALE.toString())) {
            return Gender.FEMALE;
        }
        return Gender.MALE;
    }

    private static String formatParams(HttpServletRequest req) {
        return req.getParameterMap()
                .entrySet()
                .stream()
                .map(entry -> {
                    String param = String.join(" and ", entry.getValue());
                    return entry.getKey() + " => " + param;
                }).collect(Collectors.joining("\n"));
    }
}
