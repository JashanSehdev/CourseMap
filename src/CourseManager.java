import java.util.*;
public class CourseManager {
    ArrayList <Course> courses;

    CourseManager(){
        courses = new ArrayList<>();
    }

    Course createCourse (String name){
        Course c  = new Course(name);
        courses.add(c);
        return c;
    }

    void removeCourse(Course c){
        c.status="removed by Instructor";
        courses.remove(c);
        System.out.println("Course have been removed");
        for(Map.Entry<String,Student> e: c.enrolled.entrySet()){
            Student s = e.getValue();
            s.calibrateCourse();
        }
        // also calibrate with Student class
    }

    void show(){
        int i=1;
        for (Course c: courses){
            System.out.println(i+". "+ c.course_name + " by "+c.course_instructor);
        }
    }

    void enrollStudent(Course c,Student s){
        c.enrollStudent(s);
    }

    Course getCourseByName(String name){
        for(Course c : courses){
            if(c.course_name.equalsIgnoreCase(name)){
                return c;
            }
        }
        return null;
    }

}
