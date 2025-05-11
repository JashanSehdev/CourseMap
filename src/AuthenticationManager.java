import java.util.*;

public class AuthenticationManager {
    CourseManager manager;
    Map<String, User> users;

    AuthenticationManager(CourseManager manager) {
        users = new HashMap<>();
        this.manager = manager;
    }

    void register(String username, String password, String role) {

        if (users.containsKey(username)) {
            System.out.println("User Already exist");
            return;
        }

        if (role.equalsIgnoreCase("Instructor")) {
            users.put(username, new Instructor(username, password, role,manager));
        } else if (role.equalsIgnoreCase("Student")) {
            users.put(username, new Student(username, password, role,manager));
        }

    }

    User login(String username, String password, String role) {

        if (!users.containsKey(username)) {
            System.out.println("username or password are incorrect");
            return null;
        }

        User user = users.get(username);
        if (user.password.equals(password)) return user;
        System.out.println("username or password are incorrect");
        return null;
    }

    void showAllUsers(){
        int i=1;
        for(Map.Entry<String,User> e : users.entrySet()){
            User user = e.getValue();
            System.out.println(i+". "+ user.username+ ", role: "+ user.role+".");
            i++;
        }
    }

    void showAllUsers(String role){
        if(users.isEmpty()){
            System.out.println("no users have been registered");
            return;
        }
        if(role.equalsIgnoreCase("Student")){
            System.out.println("List of Students");
            int i=1;
            for(Map.Entry<String,User> e : users.entrySet()){
                User user = e.getValue();
                if(user==null)return;
                if(user.role.equalsIgnoreCase("Student")){
                    System.out.println(i+". "+ user.username+".");
                }

                i++;
            }
        } else if (role.equalsIgnoreCase("Instructor")) {
            System.out.println("List of Instructors");
            int i=1;
            for(Map.Entry<String,User> e : users.entrySet()){
                User user = e.getValue();
                if(user==null)return;
                if(user.role.equalsIgnoreCase("Instructor")){
                    System.out.println(i+". "+ user.username+".");
                }
                i++;
            }
        }
        else System.out.println("Invalid Role");
    }
}
