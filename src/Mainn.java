import java.time.LocalDate;
import java.util.*;
public class Mainn {
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
//        instructor.show();
    }

    static void CreateAssignment(Instructor instructor, String Course, String ass_name,String question){
        instructor.createAssignment(Course,ass_name,question,LocalDate.of(2025,5,27));
//        instructor.getCourseByName(Course).getAssignmentByName(ass_name).show();
    }

    static void enroll(Student s, String name){
        s.enroll(name);
    }

    static void writeAnswer(Student s, String course_name, String assignment_name){
        Course c = s.getCourseByName(course_name);
        s.writeAnswer(c,assignment_name,s.username+" I have an Answer for "+assignment_name);
    }

    static void evaluation(Instructor instructor, String Course_name, String assignment_name, Scanner sc){
        Course c = instructor.getCourseByName(Course_name);
        instructor.EvaluateAssignment(c,assignment_name,sc);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CourseManager manager = new CourseManager();
        AuthenticationManager auth = new AuthenticationManager(manager);

        register(auth);

        User in = auth.login("Jashan","1234","Instructor");
        User st = auth.login("Aarav","pass123","Student");
        User st2 = auth.login("Rohan","rohan987","Student");
        Instructor instructor = (Instructor)in;
        Student student  = (Student) st;
        Student student1 = (Student) st2;

        CreateCourse(instructor,"DSA");
        CreateAssignment(instructor,"DSA","assignment1","Why are you here");
        CreateAssignment(instructor,"DSA","assignment2","Why are you here again");
        CreateAssignment(instructor,"DSA","assignment3","Why are you here again again");


        enroll(student,"DSA");
        enroll(student1,"DSA");


        Course c = student.getCourseByName("DSA");

        writeAnswer(student,"DSA","assignment1");
        writeAnswer(student1,"DSA","assignment1");
        writeAnswer(student,"DSA","assignment2");
        writeAnswer(student,"DSA","assignment3");

        evaluation(instructor,"DSA","assignment1",sc);
        c.getAssignmentByName("assignment1").show("teacher");

        student.showPerformance();
        student1.showPerformance();

        //instructor.createAssignment("DSA","assignment 1","What is the Universe", LocalDate.of(2025,5,27));



    }
}
//        instructor.show();
//        manager.show();
//        instructor.removeCourse("DSA");
//        instructor.show();
//        manager.show();
