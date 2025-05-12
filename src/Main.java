import java.time.LocalDate;
import java.util.*;
public class Main {
    static void register(AuthenticationManager auth){
        auth.register("Jashan", "1234", "Instructor");
        auth.register("Aarav", "pass123", "Student");
        auth.register("Ishita", "hello321", "Instructor");
        auth.register("Rohan", "rohan987", "Student");
        auth.register("Meera", "meera@123", "Student");
        auth.register("Karan", "karan!456", "Instructor");
        auth.register("Priya", "priya789", "Student");
        auth.register("Ananya", "ananya111", "Instructor");

    }

    static void CreateCourse(Instructor instructor, String name){
        instructor.createCourse(name);
        instructor.show();
    }

    static void CreateAssignment(Instructor instructor, String Course, String ass_name,String question){
        instructor.createAssignment(Course,ass_name,question,LocalDate.of(2025,5,27));
    }
    public static void main(String[] args) {
        CourseManager manager = new CourseManager();
        AuthenticationManager auth = new AuthenticationManager(manager);

        register(auth);

        User in = auth.login("Jashan","1234","Instructor");
        User st = auth.login("Aarav","pass123","Student");
        Instructor instructor = (Instructor)in;
        Student student  = (Student) st;

        CreateCourse(instructor,"DSA");
        CreateAssignment(instructor,"DSA","assignment1","Why is name DDD");

        instructor.createAssignment("DSA","assignment 1","Who is your dad?",LocalDate.of(2025,5,27));

        student.enroll("DSA");


        Course c = student.getCourseByName("DSA");

        student.seeAssignmentByName(c,"assignment 1");
        student.writeAnswer(c,"assignment 1","My answer is Simple");
        c.getAssignmentByName("assignment 1").show("teacher");

        //instructor.createAssignment("DSA","assignment 1","What is the Universe", LocalDate.of(2025,5,27));



    }
}
//        instructor.show();
//        manager.show();
//        instructor.removeCourse("DSA");
//        instructor.show();
//        manager.show();
