import java.util.ArrayList;

public class Professor extends Person {
    ArrayList<Subject> subjects = new ArrayList<Subject>();
    ArrayList<Student> students= new ArrayList<Student>();
    private String position;
    private int years_of_experience;

    public Professor(String nm, int ag, String sx, String pos, int yrs){
        super(nm, ag, sx);
        this.position = pos;
        this.years_of_experience = yrs;
    }

    public void display(){
        System.out.println("Name: " + this.getName() + ", Age: " + this.getAge() + ", Sex: " + this.getSex() + ", Position: " + this.position + ", Years of experience: " + this.years_of_experience);
    }

    public void addStudent(Student newStudent){
        if(!students.contains(newStudent)){
            students.add(newStudent);
        }
    }

    public void removeStudent(Student removedStudent){
        students.remove(removedStudent);
    }

    public void addSubject(Subject newSubject){
        if(!subjects.contains(newSubject)){
            subjects.add(newSubject);
        }
    }

    public void removeSubject(Subject removedSubject){
        subjects.remove(removedSubject);
    }

    public void increaseYearsOfExperience(){
        this.years_of_experience += 1;
    }

    public String getPosition() { return position; }

    public int getYearsOfExperience() { return years_of_experience; }

    public void setPosition(String pos) { this.position = pos; }
}
