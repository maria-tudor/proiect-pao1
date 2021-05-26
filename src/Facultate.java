import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Facultate
{
    private ArrayList<Subject> subjects = new ArrayList<Subject>();
    private ArrayList<Student> students= new ArrayList<Student>();
    private ArrayList<Mark> marks = new ArrayList<Mark>();
    private ArrayList<Professor> professors = new ArrayList<Professor>();

    public void addStudent (String nm, int ag, String sx, String mjr, int yr)
    {
        this.students.add (new Student(nm, ag, sx, mjr, yr));
    }

    public void addSubject (String nm, int sms, String fld)
    {
        this.subjects.add (new Subject(nm, sms, fld));
    }

    public void addProfessor (String nm, int ag, String sx, String pos, int yrexp)
    {
        this.professors.add (new Professor(nm, ag, sx, pos, yrexp));
    }

    public void addMark (Student stud, Professor prof, Subject sub, String dat, int val) throws Exception
    {
        Subject sub2 = null;

        for (Subject subject : this.subjects)
            if (subject.getName ().equals (sub.getName()))
            {
                sub2 = subject;
                break;
            }

        if (sub2 == null)
            throw new Exception ("Materia nu exista!");

        for (Student student : this.students)
            if (student.getName ().equals (stud.getName()))
            {
                boolean found = false;
                for (Subject subject :student.getSubjects ())
                    if (subject.getName ().equals (sub.getName()))
                    {
                      //  Date convertedDate = new SimpleDateFormat("dd/MM/yyyy").parse(dat);
                        Mark newMark = new Mark(stud, prof, sub, dat, val);
                        found = true;
                    }
                if (!found)
                    throw new Exception ("Studentul nu face acea materie!");
                break;
            }
    }

    public ArrayList <Student> getStudents ()
    {
        return this.students;
    }

    public ArrayList <Professor> getProfessors ()
    {
        return this.professors;
    }

    public ArrayList <Subject> getSubjects ()
    {
        return this.subjects;
    }

    public ArrayList <Mark> getMarks ()
    {
        return this.marks;
    }

    public void clearData ()
    {
        this.students = new ArrayList <>();
        this.professors   = new ArrayList <>();
        this.subjects = new ArrayList <>();
        this.marks  = new ArrayList <>();
    }

    public ArrayList <ArrayList <String>>  saveStudents ()
    {
        ArrayList <ArrayList <String>> returnStudents = new ArrayList <> ();

        for (Student student : this.students)
        {
            ArrayList <String> nw = new ArrayList <> ();

            nw.add (student.getName ());
            nw.add (Integer.toString (student.getAge ()));
            nw.add (student.getSex());
            nw.add (student.getMajor());
            nw.add (Integer.toString(student.getYear()));

            returnStudents.add (nw);
        }

        return (returnStudents);
    }

    public void loadStudents (ArrayList <ArrayList <String>> students) {
        for (ArrayList <String> ln : students)
        {
            this.addStudent (ln.get (0), Integer.parseInt (ln.get (1)),
                    ln.get (2), ln.get (3), Integer.parseInt (ln.get (4)));
        }
    }


    public ArrayList <ArrayList <String>> saveProfessors ()
    {
        ArrayList <ArrayList <String>> returnProfessors = new ArrayList<>();

        for (Professor professor : this.professors)
        {
            ArrayList <String> nw = new ArrayList<>();

            nw.add (professor.getName ());
            nw.add (Integer.toString (professor.getAge ()));
            nw.add (professor.getSex());
            nw.add (professor.getPosition());
            nw.add (Integer.toString(professor.getYearsOfExperience()));

            returnProfessors.add (nw);
        }

        return (returnProfessors);
    }

    public void loadProfessors (ArrayList <ArrayList <String>> professors)
    {
        for (ArrayList <String> ln : professors)
        {
            this.addProfessor (ln.get (0), Integer.parseInt (ln.get (1)),
                    ln.get (2), ln.get (3), Integer.parseInt (ln.get (4)));
        }
    }


    public ArrayList <ArrayList <String>> saveSubjects ()
    {
        ArrayList <ArrayList <String>> returnSubjects = new ArrayList<>();

        for (Subject subject : this.subjects)
        {
            ArrayList <String> nw = new ArrayList<>();

            nw.add (subject.getName ());
            nw.add (Integer.toString (subject.getSemesters ()));
            nw.add (subject.getField());

            returnSubjects.add (nw);
        }

        return (returnSubjects);
    }

    public void loadSubjects (ArrayList <ArrayList <String>> subjects)
    {
        for (ArrayList <String> ln : subjects)
            this.addSubject (ln.get (0), Integer.parseInt (ln.get (1)), ln.get(2));
    }


    public ArrayList <ArrayList <String>> saveMarks ()
    {
        ArrayList <ArrayList <String>> returnMarks = new ArrayList <> ();

        for (Student student : this.students)
        {
            for (Mark mark : student.getMarks ()) {
                ArrayList<String> nw = new ArrayList<>();

                nw.add(student.getName ());
                nw.add(mark.getProfessorName());
                nw.add(mark.getSubjectName());
                SimpleDateFormat cv = new SimpleDateFormat("dd/MM/yyyy");
                String dat = cv.format(mark.getDate());
                nw.add(dat);
                nw.add(Integer.toString(mark.getValue ()));

                returnMarks.add(nw);
            }
        }

        return (returnMarks);
    }

    public Student findStudent(String nm){
        for (Student stud : students)
            if (stud.getName().equals(nm))
                return stud;
        return null;
    }

    public Subject findSubject(String nm){
        for (Subject sub : subjects)
            if (sub.getName().equals( nm))
                return sub;
        return null;
    }

    public Professor findProfessor(String nm){
        for (Professor prof : professors)
            if (prof.getName().equals(nm))
                return prof;
        return null;
    }

    public Mark findMark(String nmstud, String nmprof, String nmsub, String date) throws ParseException {
        Date convertedDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
        for (Mark mark : marks)
            if (mark.getStudentName().equals (nmstud) && mark.getSubjectName().equals(nmsub) &&
                    mark.getProfessorName().equals(nmprof) && mark.getDate().equals(convertedDate))
                return mark;
        return null;
    }

    public void loadMarks (ArrayList <ArrayList <String>> marks) throws Exception
    {

        for (ArrayList <String> ln : marks)
            this.addMark (findStudent(ln.get (0)), findProfessor(ln.get (1)),
                    findSubject(ln.get (2)),
                    new SimpleDateFormat("dd/MM/yyyy").format(ln.get (3)),
                    Integer.parseInt (ln.get (4)));
    }

    public void removeStudent (String nm)
    {
        for (Student student : this.students)
            if (student.getName ().equals (nm))
            {
                for (Professor professor : this.professors)
                        professor.students.remove (findStudent(nm));
                this.students.remove(findStudent(nm));
                break;
            }
    }

    public void removeProfessor (String nm)
    {
        for (Professor professor : this.professors)
            if (professor.getName ().equals (nm))
            {
                for (Subject subject : this.subjects)
                        subject.professors.remove(findProfessor(nm));
                this.professors.remove(findProfessor(nm));
                break;
            }
    }

    public void removeSubject (String nm)
    {
        for (Subject subject : this.subjects)
            if (subject.getName ().equals (nm))
            {
                for (Professor professor : this.professors)
                        professor.subjects.remove (findSubject(nm));
                for (Student student : this.students)
                    if (student.subjects.contains(nm))
                        student.subjects.remove(findSubject(nm));
                this.subjects.remove(findProfessor(nm));
                break;
            }
    }

    public void removeMark(String nmstud, String nmprof, String nmsub, String date) throws ParseException {
        Date convertedDate = new SimpleDateFormat("yyyy/MM/dd").parse(date);
        for (Mark mark : this.marks)
            if (mark.getStudentName().equals (nmstud) && mark.getSubjectName().equals(nmsub) &&
            mark.getProfessorName().equals(nmprof) && mark.getDate().equals(convertedDate))
            {
                for (Student student : this.students)
                        student.marks.remove (findMark(nmstud, nmprof, nmsub, date));
                this.marks.remove(findMark(nmstud,nmprof, nmsub, date));
                break;
            }
    }

    public void updateMark(String nmstudVechi, String nmprofVechi, String nmsubVechi, String dateVechi,
                            int valNou) throws ParseException {
        Date convertedDate = new SimpleDateFormat("yyyy/MM/dd").parse(dateVechi);
        for(Mark mark : this.marks){
            if(mark.getStudentName().equals (nmstudVechi) && mark.getSubjectName().equals(nmsubVechi) &&
                    mark.getProfessorName().equals(nmprofVechi) && mark.getDate().equals(convertedDate)){
                mark.setValue(valNou);
            }
        }
    }


    public void updateStudent(String nmVechi, String nm, int ag, String mjr, int yr){

        for(Student student : this.students){
            if(student.getName().equals (nmVechi)){
                student.setName(nm);
                student.setAge(ag);
                student.setMajor(mjr);
                student.setYear(yr);
            }
        }
    }

    public void updateProfessor(String nmVechi, String nm, int ag, String pos, int yrsexp){

        for(Professor professor : this.professors){
            if(professor.getName().equals (nmVechi)){
                professor.setName(nm);
                professor.setAge(ag);
                professor.setPosition(pos);
                professor.setYearsOfExperience(yrsexp);
            }
        }
    }

    public void updateSubject(String nmVechi, String nm, int sem){

        for(Subject subject : this.subjects){
            if(subject.getName().equals (nmVechi)){
                subject.setName(nm);
                subject.setSemesters(sem);
            }
        }
    }

}
