import java.util.ArrayList;


public class Subject {
    ArrayList<Professor> professors= new ArrayList<Professor>();
    private String name;
    private int semesters;
    private final String field;

    public Subject(String nm, int sem, String fld){
        this.name = nm;
        this.semesters = sem;
        this.field = fld;
    }

    public void display(){
        System.out.println("Subject: " + this.name + ", Duration: " + this.semesters + " semesters" + ", Field: " + this.field);
    }

    public void addProfessor(Professor newProfessor){
        if(!professors.contains(newProfessor)){
            professors.add(newProfessor);
        }
    }

    public void removeProfessor(Professor removedProfessor){
        professors.remove(removedProfessor);
    }

    public int getSemesters(){ return semesters;}

    public void setSemesters(int sem){ this.semesters = sem;}

    public String getField(){ return field; }

    public String getName(){ return name;}

    public void setName(String nm){ this.name = nm;}

}
