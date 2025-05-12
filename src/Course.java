import java.sql.SQLOutput;
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
        showAllAssignments();
    }

    void showAllAssignments(){
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
        Map <String,SubmittedAssignment> submitted_assignment;// key = Student name, value = Submitted Assignment

        Assignment(String name, String question, LocalDate due){
            this.name = name;
            this.question = question;
            this.due = due;
            this.date = LocalDate.now();
            submitted_assignment = new HashMap<>();
        }

        void show(){
            System.out.println("Assignment name: "+name);
            System.out.println("Assignment Date: "+date);
            System.out.println(("Due Date "+due));
            System.out.println("Question:-");
            System.out.println(question);
        }
        void show( String any){
            System.out.println("Assignment name: "+name);
            System.out.println("Assignment Date: "+date);
            System.out.println(("Due Date "+due));
            System.out.println("Question:-");
            System.out.println(question);
            System.out.println("No. of Submissions: "+submitted_assignment.size());
            System.out.println("Name   Submission Date   Grade");
            for(Map.Entry<String,SubmittedAssignment> e: submitted_assignment.entrySet()){
                e.getValue().show();
            }

        }

        void writeAnswer(String answer,Student S){
            SubmittedAssignment sa = new SubmittedAssignment(answer,S);
            submitted_assignment.put(sa.student.username,sa);
        }

        class SubmittedAssignment{
            String answer;
            Student student;
            LocalDate submit;
            String grade="nil";

            SubmittedAssignment(String answer,Student S){
                this.answer = answer;
                this.student = S;
                this.submit = LocalDate.now();
            }

            void show(){
                System.out.println(student.username+"\t"+submit+"\t \t"+" ["+grade+"].");
            }
            void show(String any){
                System.out.println("Student Name: "+student.username);
                System.out.println("Question: "+question);
                System.out.println("Answer:- ");
                System.out.println(answer);
                System.out.println("grades: " +grade);
            }

            void setGrade(String grade){
                this.grade = grade;
            }



        }
    }
}
