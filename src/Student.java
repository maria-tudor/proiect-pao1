import java.util.ArrayList;


public class Student extends Person {
    ArrayList<Subject> subjects= new ArrayList<Subject>();
    ArrayList<Mark> marks= new ArrayList<Mark>();
    private String major;
    private int year;

    public Student(String nm, int ag, String sx, String mjr, int yr){
        super(nm, ag, sx);
        this.major = mjr;
        this.year = yr;
    }

    public void display(){
        System.out.println("Name: " + this.getName() + ", Age: " + this.getAge() + ", Sex: " + this.getSex() + ", Major: " + this.major + ", Year: " + this.year);
    }

    public String getMajor(){ return major; }

    public void setMajor(String mjr){this.major = mjr;}

    public void setYear(int yr){this.year = yr;}

    public int getYear(){ return year;}

    public void increaseYear(){
        this.year += 1;
    }

    public void addSubject(Subject newSubject){
        if(!subjects.contains(newSubject)){
            subjects.add(newSubject);
        }
    }

    public void removeSubject(Subject removedSubject){
        subjects.remove(removedSubject);
    }

    public int worstGradeInSubject(String subName){
        int min = 11;
        for (Mark i:this.marks){
            if (i.getValue() < min && subName.equals(i.getSubjectName())){
                min = i.getValue();
            }
        }
        return min;
    }

    public int averageInSubject(String subName){
        int nr = 0;
        int s =  0;
        for (Mark i:this.marks){
            if (subName.equals(i.getSubjectName())){
                s += i.getValue();
                nr += 1;
            }
        }
        return s/nr;
    }

    public void showMarks(){
        for (Mark mark : this.marks) {
            System.out.print(mark.getValue() + " ");
        }
    }

    public void           addMark (Mark newMark) { this.marks.add (newMark); }

    public ArrayList<Mark> getMarks () { return this.marks; }

    public ArrayList<Subject> getSubjects () { return this.subjects; }
}
