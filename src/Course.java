import java.time.LocalDate;
import java.util.*;

public class Course {
    String course_name;
    String course_instructor;
    String status="active";
    HashMap<String, Student> enrolled;
    HashMap<String, Assignment> assignments;
    Course (String name){
       this.course_name = name;
       enrolled = new HashMap<>();
       assignments = new HashMap<>();
    }

    void enrollStudent(Student s){
        enrolled.put(s.username,s);
    }

    void show(){
        System.out.println("Course name: "+course_name);
        System.out.println("Course instructor: "+ course_instructor);
        System.out.println("Enrolled Student:-");
        int i=1;
        for (Map.Entry<String,Student> e : enrolled.entrySet()){
            System.out.println(i+". "+e.getValue().username);
        }
    }

    void show(String s){
        System.out.println("Course name: "+course_name);
        System.out.println("Course instructor: "+ course_instructor);
        System.out.println("Assignments:-");
        int i=1;
        for (Map.Entry<String,Assignment> e : assignments.entrySet()){
            System.out.println(i+". "+e.getValue().name);
        }
    }

    void createAssignment(String name, String question, LocalDate due){
        Assignment a = new Assignment(name,question,due);
        assignments.put(a.name,a);
        System.out.println("Assignment has been created by name "+ a.name);
    }

    Assignment getAssignmentByName(String name){
        Assignment a = assignments.get(name);
        if(a==null){
            System.out.println("Assignment Not Found");
            return null;
        }
        return a;
    }

    void removeAssignment(String name){
        Assignment a = getAssignmentByName(name);
        if(a!=null){
            assignments.remove(a.name);
        }

    }

    class Assignment{

        String question;
        String name;
        HashMap <Student,String> grades;
        String status;
        LocalDate date = LocalDate.now();
        LocalDate due;
        Map <String,SubmittedAssignment> submitted_assignment;
        Assignment(String name, String question, LocalDate due){
            this.name = name;
            this.question = question;
            this.due = due;
            this.date = LocalDate.now();
        }

        class SubmittedAssignment{
            String answer;
            Student student;
            LocalDate submit;
            String grade;

            void writeAnswer(String answer){
                if(answer.isEmpty()) System.out.println("No Word detected");
                this.answer = answer;
                System.out.println("Assignment has been Submitted");
            }

        }
    }
}
