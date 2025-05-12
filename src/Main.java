import java.time.LocalDate;
import java.util.*;


public class Main {

    static String ask(Scanner sc, String question) {
        String answer;
        System.out.println(question);
        answer = sc.nextLine();
        return answer;
    }


    static void student(AuthenticationManager auth, CourseManager manager, Scanner sc, Student s) {

        while(true){

            String question = "Hi " + s.username + "\n " + "Type number corresponding to desired operation.\n" +
                    "1. show profile\n" +
                    "2. Enroll in course\n"+
                    "3. show assignments\n"+
                    "4. Submit Answer for Assignment\n"+
                    "5. Show performance\n"+
                    "6. Log out";
            String choose = ask(sc, question);
            switch (choose) {
                case "1":
                    s.show();
                    break;

                case "2":
                    System.out.println("Available Courses:-");
                    manager.show();
                    String course_name = ask(sc, "Course name?");
                    s.enroll(course_name);
                    break;

                case "3":
                    course_name = ask(sc,"Course name?");
                    Course c = s.getCourseByName(course_name);
                    if(c!=null){
                        c.show("Assignments");
                    }
                    break;

                case "4":
                    course_name = ask(sc,"Course name?");
                    c = s.getCourseByName(course_name);
                    if(c!=null){
                        c.show("Assignments");
                        String assignment = ask(sc,"which Assignment?");
                        Course.Assignment a = c.getAssignmentByName(assignment);
                        System.out.println(a.question);
                        String answer = ask(sc,"Answer::");
                        s.writeAnswer(c,assignment,answer);
                    }
                    break;

                case "5":
                    s.showPerformance();
                    break;

                case "6":
                    return;

                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    static void instructor(AuthenticationManager auth, CourseManager manager, Scanner sc, Instructor i){
        while(true){
            String question = "Hi " + i.username + "\n " + "Type number corresponding to desired operation.\n" +
                    "1. show profile\n" +
                    "2. Create course\n"+
                    "3. Remove Course\n"+
                    "4. Create Assignment\n"+
                    "5. remove Assignment\n"+
                    "6. Evaluate Assignment\n"+
                    "7. Show Student Score\n"+
                    "8. Log out";
            String choose = ask(sc, question);
            switch(choose){
                case "1":
                    i.show();
                    break;
                case "2":
                    String course_name = ask(sc,"Course name?");
                    i.createCourse(course_name);
                    break;
                case "3":
                    course_name = ask(sc,"Course name?");
                    i.removeCourse(course_name);
                    break;
                case "4":
                    course_name = ask(sc,"Course name?");
                    String assign_name = ask(sc,"Set assignment name.");
                    String Question = ask(sc,"Set question for your assignment");
                    System.out.println("Set Due date:");
                    System.out.println("year?");
                    int year = sc.nextInt();
                    System.out.println("Month number?");
                    int month = sc.nextInt();
                    System.out.println("Date?");
                    int date = sc.nextInt();
                    i.createAssignment(course_name,assign_name,Question,LocalDate.of(year,month,date));
                    break;
                    // Add exception handling
                case "5":
                    course_name = ask(sc,"Course name?");
                    assign_name = ask(sc,"assignment name?");
                    i.removeAssignment(course_name,assign_name);
                    break;

                case "6":
                    course_name = ask(sc,"Course name?");
                    assign_name = ask(sc,"assignment name?");
                    Course c = i.getCourseByName(course_name);
                    i.EvaluateAssignment(c,assign_name,sc);
                    break;
                case "7":
                    course_name = ask(sc,"Course name?");
                    assign_name = ask(sc,"assignment name?");
                    i.showStudentScore(course_name,assign_name);
                    break;
                case "8":
                    return;

                default:
                    System.out.println("Invalid Input!");
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Setting up main management classes
        CourseManager manager = new CourseManager();
        AuthenticationManager auth = new AuthenticationManager(manager);
        String question;
        String choose;

        // test code

        manager.createCourse("java");
        manager.createCourse("DSA");
        manager.getCourseByName("DSA").createAssignment("assignment1","What is the most beautiful thing on earth?", LocalDate.of(2025,5,27));
        manager.getCourseByName("DSA").createAssignment("assignment2","Date of Independence for India?", LocalDate.of(2025,5,27));
        manager.getCourseByName("DSA").createAssignment("assignment3","Who is the Father of nation?", LocalDate.of(2025,5,27));


        //Authentication


        Instructor instructor;
        Student student;
        User user = null;
        while (true) {
            question = "login or register";
            choose = ask(sc, question);
            switch (choose) {

                case "login":
                    String username = ask(sc, "What is your username?");
                    String password = ask(sc, "What is your password?");
                    String role = ask(sc, "Are you student or instructor?");
                    user = auth.login(username, password, role);
                    if (role.equalsIgnoreCase("student")) student = (Student) user;
                    else if (role.equalsIgnoreCase("instructor")) instructor = (Instructor) user;
                    break;

                case "register":
                    username = ask(sc, "What is your username?");
                    password = ask(sc, "What is your password?");
                    role = ask(sc, "Are you student or instructor?");
                    auth.register(username, password, role);
                    user = auth.login(username, password, role);
                    if (role.equalsIgnoreCase("student")) student = (Student) user;
                    else if (role.equalsIgnoreCase("instructor")) instructor = (Instructor) user;
                    break;
                default:
                    System.out.println("invalid Input");

            }


                if (user instanceof Student) {
                    student = (Student) user;

                    student(auth, manager, sc, student);


                } else if (user instanceof Instructor) {
                    instructor = (Instructor)user;

                    instructor(auth, manager, sc, instructor);

                }




        }


    }


}
