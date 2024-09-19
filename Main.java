import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Create an Administrator
        Administrator admin = new Administrator("John");

        // Add some users (Programmers and Managers) via the Administrator
        Programmer programmer1 = new Programmer("Bob");
        Programmer programmer2 = new Programmer("Charlie");
        Manager manager1 = new Manager("Alice");
        Manager manager2 = new Manager("David");

        admin.addUser(programmer1);
        admin.addUser(programmer2);
        admin.addUser(manager1);
        admin.addUser(manager2);

        // 2. Ask for the username
        System.out.println("Welcome to the Project Management System!");
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine();

        // 3. Check if the user exists
        User user = admin.findUserByName(username);
        if (user == null) {
            System.out.println("User not found. Exiting...");
            return;
        }

        // 4. Determine user role and display the appropriate menu
        if (user instanceof Administrator) {
            Administrator currentAdmin = (Administrator) user;
            System.out.println("Welcome, Administrator " + currentAdmin.getName() + "!");
            adminMenu(scanner, currentAdmin);

        } else if (user instanceof Programmer) {
            Programmer currentProgrammer = (Programmer) user;
            System.out.println("Welcome, Programmer " + currentProgrammer.getName() + "!");
            programmerMenu(scanner, currentProgrammer);

        } else if (user instanceof Manager) {
            Manager currentManager = (Manager) user;
            System.out.println("Welcome, Manager " + currentManager.getName() + "!");
            managerMenu(scanner, currentManager, admin);

        } else {
            System.out.println("Unknown role detected.");
        }

        scanner.close();
        System.out.println("Goodbye!");
    }

    // Administrator menu
    public static void adminMenu(Scanner scanner, Administrator admin) {
        boolean running = true;
        while (running) {
            System.out.println("\nAdministrator Menu:");
            System.out.println("1. Add User");
            System.out.println("2. List Users");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (option) {
                case 1:
                    System.out.print("Enter the new user's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the new user's role (Programmer/Manager): ");
                    String role = scanner.nextLine();

                    if (role.equalsIgnoreCase("Programmer")) {
                        admin.addUser(new Programmer(name));
                    } else if (role.equalsIgnoreCase("Manager")) {
                        admin.addUser(new Manager(name));
                    } else {
                        System.out.println("Invalid role. Try again.");
                    }
                    break;

                case 2:
                    admin.listUsers();
                    break;

                case 3:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Programmer menu
    public static void programmerMenu(Scanner scanner, Programmer programmer) {
        boolean running = true;
        while (running) {
            System.out.println("\nProgrammer Menu:");
            System.out.println("1. View Assigned Projects");
            System.out.println("2. View Assigned Tasks");
            System.out.println("3. Mark Task as Completed");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (option) {
                case 1:
                    programmer.listAssignedProjects();
                    break;

                case 2:
                    programmer.listAssignedTasks();
                    break;

                case 3:
                    System.out.print("Enter the task description to mark as completed: ");
                    String taskDesc = scanner.nextLine();
                    programmer.markTaskAsCompleted(taskDesc);
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    // Manager menu
    public static void managerMenu(Scanner scanner, Manager manager, Administrator admin) {
        boolean running = true;
        while (running) {
            System.out.println("\nManager Menu:");
            System.out.println("1. Create Project");
            System.out.println("2. List Projects");
            System.out.println("3. Assign Programmer to Project");
            System.out.println("4. Create Task in Project");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume the newline

            switch (option) {
                case 1:
                    System.out.print("Enter the project name: ");
                    String projectName = scanner.nextLine();
                    manager.createProject(projectName);
                    break;

                case 2:
                    manager.listProjects();
                    break;

                case 3:
                    System.out.print("Enter the project name: ");
                    String projName = scanner.nextLine();
                    Project project = manager.findProjectByName(projName);
                    if (project != null) {
                        System.out.print("Enter the programmer's name: ");
                        String progName = scanner.nextLine();
                        Programmer programmer = (Programmer) admin.findUserByName(progName);
                        if (programmer != null) {
                            manager.assignProgrammerToProject(project, programmer);
                        } else {
                            System.out.println("Programmer not found.");
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter the project name: ");
                    String projectForTask = scanner.nextLine();
                    Project taskProject = manager.findProjectByName(projectForTask);
                    if (taskProject != null) {
                        System.out.print("Enter the task description: ");
                        String taskDesc = scanner.nextLine();
                        System.out.print("Enter the programmer's name to assign: ");
                        String progName = scanner.nextLine();
                        Programmer taskProgrammer = (Programmer) admin.findUserByName(progName);
                        if (taskProgrammer != null) {
                            manager.createTaskInProject(taskProject, taskDesc, taskProgrammer);
                        } else {
                            System.out.println("Programmer not found.");
                        }
                    } else {
                        System.out.println("Project not found.");
                    }
                    break;

                case 5:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
}
