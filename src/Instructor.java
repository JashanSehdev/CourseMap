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


}
