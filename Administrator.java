import java.util.ArrayList;
import java.util.List;

public class Administrator extends User {
    private List<User> users;

    public Administrator(String name) {
        super(name, RoleNames.ADMINISTRATOR);
        this.users = new ArrayList<>();
    }

    // Add a user to the system
    public void addUser(User user) {
        users.add(user);
    }

    // Remove a user from the system
    public void removeUser(User user) {
        users.remove(user);
    }

    // List all users in the system
    public void listUsers() {
        for (User user : users) {
            System.out.println(user.getName() + " - " + user.getRole());
        }
    }

    // Find a user by name
    public User findUserByName(String name) {
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                return user;
            }
        }
        return null; // Return null if not found
    }

    @Override
    public void displayRolePermissions() {
        System.out.println("Administrator Permissions: Manage users, create/remove users.");
    }
}


