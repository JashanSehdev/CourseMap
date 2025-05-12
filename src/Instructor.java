
import java.time.LocalDate;
import java.util.*;

public class Instructor extends User{
    CourseManager manager;
    HashMap<String,Course> myCourse;
    Instructor(String username, String password, String role , CourseManager manager){
        super(username, password, role);
        this.manager = manager;
        myCourse = new HashMap<>();
    }

    void show(){
        System.out.println("name: "+username);
        System.out.println("profile: "+ role);
        System.out.println("Your Courses");
        int i=1;
        for(Map.Entry<String,Course> e:myCourse.entrySet()){
            System.out.println(i+". "+e.getValue().course_name);
        }
    }

    void createCourse(String name){
        Course c = manager.createCourse(name);
        c.course_instructor=username; // setting course
        myCourse.put(name,c);
        System.out.println("Course has been created by name "+c.course_name);
    }

    void removeCourse(String name){
        Course c = getCourseByName(name);
        if(c!=null){
            myCourse.remove(c.course_name);
            manager.removeCourse(c);
        }
    }

    void createAssignment(String course_name, String name, String question, LocalDate due){
        Course c = getCourseByName(course_name);
        if(c!=null){
            c.createAssignment(name,question,due);
        }
    }

    void removeAssignment(String course_name, String name){
        Course c = getCourseByName(course_name);
        if(c!=null){
            c.removeAssignment(name);
        }
    }
    Course getCourseByName(String name){
        Course c = myCourse.get(name);
        if(c==null){
            System.out.println("No Course has been found");
            return null;
        }
        return c;
    }

    void showAssignments(String course_name){
        Course c = getCourseByName(course_name);
        c.show();
    }

    void EvaluateAssignment(Course c, String name, Scanner sc){
        Course.Assignment a = c.getAssignmentByName(name);
        for(Map.Entry<String, Course.Assignment.SubmittedAssignment> e : a.submitted_assignment.entrySet()){
            if(e.getValue().grade.equalsIgnoreCase("nil")){
                e.getValue().show("teacher");
                System.out.println("--Set Grade--");
                String grade = e.getValue().setGrade(sc.nextLine());
                Student s = e.getValue().student;
                s.performance.put(c.course_name, e.getValue());
            }
        }
        System.out.println("All Submission has been submitted");
    }

    void showStudentScore (String course_name, String assignment_name){
        Course c = getCourseByName(course_name);
        Course.Assignment a = c.getAssignmentByName(assignment_name);
        a.show("teacher");
    }


}
