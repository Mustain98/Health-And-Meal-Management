package CLI;

import User.UserDAO;
import User.User;
import java.sql.SQLException;

public class AuthenticationService {
    private final UserDAO userDAO;
    private final ConsoleIO console;

    public AuthenticationService(UserDAO userDAO, ConsoleIO console) {
        this.userDAO = userDAO;
        this.console = console;
    }

    public User authenticate() throws SQLException {
        while (true) {
            console.printHeader("MEAL PLANNER");
            int choice = console.getChoice("Login", "Register", "Exit");

            switch (choice) {
                case 1:
                    User user = login();
                    if (user != null) return user;
                    break;
                case 2:
                    user = register();
                    if (user != null) return user;
                    break;
                case 3:
                    console.printMessage("Goodbye!");
                    System.exit(0);
            }
        }
    }

    private User login() throws SQLException {
        console.printHeader("LOGIN");
        String username = console.getInput("Username");
        String password = console.getPassword("Password");

        String Password = password;
        User user = userDAO.getUserByCredentials(username, Password);
        if (user != null) {
            console.printSuccess("Welcome back, " + username + "!");
            return user;
        }

        console.printError("Invalid username or password");
        return null;
    }

    private User register() throws SQLException {
        console.printHeader("REGISTER");
        User newUser = new UserProfileService(console).createUser();
        if (newUser == null) return null;

        System.out.println(newUser);
        String password = console.getPasswordWithConfirmation();
        String Password = password;

        try {
            int userId = userDAO.createUser(newUser, password);
            console.printSuccess("Welcome, " + newUser.getName() + "!");
            return newUser;
        } catch (SQLException e) {
            console.printError("Registration failed: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}