import java.util.*;

public class Student extends User {
    CourseManager manager = new CourseManager();
    HashMap<String, Course>enrolled_course;
    HashMap<String, Course.Assignment.SubmittedAssignment> performance;

    Student(String username, String password, String role, CourseManager manager) {
        super(username, password, role);
        enrolled_course = new HashMap<>();
        this.manager = manager;
        performance = new HashMap<>();
    }
    void calibrateCourse (){
        for(Map.Entry<String,Course> e: enrolled_course.entrySet()) {
            Course c = e.getValue();
            if (!c.status.equalsIgnoreCase("active")) {
                enrolled_course.remove(c.course_name);
            }
        }
    }
    void show(){
        System.out.println("name: "+username);
        System.out.println("profile: "+ role);
        System.out.println("Enrolled Courses");
        int i=1;
        for(Map.Entry<String,Course> e: enrolled_course.entrySet()){
            System.out.println(i+". "+e.getValue().course_name);
        }
    }

    void showPerformance(){
        int i=1;
        System.out.println("Course      Assignment Name     Grade");
        for(Map.Entry<String, Course.Assignment.SubmittedAssignment> e : performance.entrySet()){
            System.out.println(i+". "+e.getKey()+" \t "+e.getValue().assignment_name+" \t "+e.getValue().grade);
            i++;
        }
    }
    void enroll(String name){
        Course c = manager.getCourseByName(name);
        if (c==null) {
            System.out.println("no Course found");
            return;
        }
        enrolled_course.put(c.course_name,c);
        manager.enrollStudent(c,this);
        System.out.println("you have enrolled in "+c.course_name);
    }


    void seeAssignmentByName(Course c,String name){
        if(c!=null){
            Course.Assignment a = c.getAssignmentByName(name);
            a.show();
        }
    }
    void writeAnswer(Course c , String assignment_name, String answer){
        Course.Assignment a = c.getAssignmentByName(assignment_name);
        a.writeAnswer(answer,this);
        System.out.println("Assignment Submitted");
    }

    Course getCourseByName(String name){
        Course c = enrolled_course.get(name);
        if(c==null){
            System.out.println("No Course found");
            return null;
        }
        return c;
    }


}
