import java.time.LocalDate;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        AuthenticationManager auth = new AuthenticationManager(manager);

        auth.register("Jashan", "1234", "Instructor");
        auth.register("Aarav", "pass123", "Student");
        auth.register("Ishita", "hello321", "Instructor");
        auth.register("Rohan", "rohan987", "Student");
        auth.register("Meera", "meera@123", "Student");
        auth.register("Karan", "karan!456", "Instructor");
        auth.register("Priya", "priya789", "Student");
        auth.register("Ananya", "ananya111", "Instructor");

//        auth.showAllUsers();
//        auth.showAllUsers("Student");
//        auth.showAllUsers("Instructor");

        User in = auth.login("Jashan","1234","Instructor");

        User st = auth.login("Aarav","pass123","Student");

        Instructor instructor = (Instructor)in;

//        instructor.show();
        instructor.createCourse("DSA");
//        instructor.show();
//        manager.show();

        Student student  = (Student) st;

//        student.show();
        student.enroll("DSA");
        student.show();

        instructor.removeCourse("DSA");
        student.show();

        //instructor.createAssignment("DSA","assignment 1","What is the Universe", LocalDate.of(2025,5,27));



    }
}
//        instructor.show();
//        manager.show();
//        instructor.removeCourse("DSA");
//        instructor.show();
//        manager.show();
