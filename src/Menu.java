import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Vector;

public class Menu
{
    private Facultate college;


    public Menu (Facultate fac)
    {
        college = fac;
    }

    private void saveData () {
        CSVProc.getNewCSVProc ().saveCSV (this.college.saveProfessors (),  "professors.csv");
        CSVProc.getNewCSVProc ().saveCSV (this.college.saveSubjects (),  "subjects.csv");
        CSVProc.getNewCSVProc ().saveCSV (this.college.saveStudents (),  "students.csv");
        CSVProc.getNewCSVProc ().saveCSV (this.college.saveMarks (),  "marks.csv");
    }

    private void loadData () throws Exception {
        this.college.clearData ();

        this.college.loadProfessors (CSVProc.getNewCSVProc ().loadCSV ( "professors.csv"));
        this.college.loadSubjects (CSVProc.getNewCSVProc ().loadCSV ( "subjects.csv"));
        this.college.loadStudents (CSVProc.getNewCSVProc ().loadCSV ( "students.csv"));
        this.college.loadMarks (CSVProc.getNewCSVProc ().loadCSV ( "marks.csv"));
    }


    private void menuShowProfessors ()
    {
        ArrayList <Professor> professors = this.college.getProfessors ();

        System.out.println ("Profesori: (nume, varsta, gen, pozitie, ani de experienta)");
        int i = 0;
        if (professors.size () > 0) {
            for (Professor prof : professors)
                System.out.println("\t" + (i + 1) + ". " + prof.getName() + " " + prof.getAge() + " " + prof.getSex()
                        + " " + prof.getPosition() + " " + prof.getYearsOfExperience());
            i++;
        }
        else
            System.out.println ("\tNu exista profesori inregistrati");

        System.out.println();
    }

    private void menuShowStudents ()
    {
        ArrayList <Student> students = this.college.getStudents ();

        System.out.println ("Studenti: (nume, varsta, gen, specialitate, an)");
        int i = 0;
        if (students.size () > 0) {
            for (Student stud : students)
                System.out.println("\t" + (i + 1) + ". " + stud.getName() + " " + stud.getAge() + " " + stud.getSex()
                        + " " + stud.getMajor() + " " + stud.getYear());
            i++;
        }
        else
            System.out.println ("\tNu exista studenti inregistrati");

        System.out.println();
    }

    private void menuShowSubjects ()
    {
        ArrayList <Subject> subjects = this.college.getSubjects ();

        System.out.println ("Materii: (nume, semestre, domeniu)");
        int i = 0;
        if (subjects.size () > 0) {
            for (Subject sub : subjects)
                System.out.println("\t" + (i + 1) + ". " + sub.getName() + " " + sub.getSemesters() + " "
                        + sub.getField());
            i++;
        }
        else
            System.out.println ("\tNu exista materii inregistrate");

        System.out.println();
    }

    private void menuShowMarks ()
    {
        ArrayList <Mark> marks = this.college.getMarks ();

        System.out.println ("Note: (nume student, nume profesor, materie, data, valoare)");
        int i = 0;
        if (marks.size () > 0) {
            for (Mark mark : marks)
                System.out.println("\t" + (i + 1) + ". " + mark.getStudentName() + " " + mark.getProfessorName() +
                        " " + mark.getSubjectName() + " " + mark.getDate() + " " + mark.getValue());
            i++;
        }
        else
            System.out.println ("\tNu exista note inregistrate");

        System.out.println();
    }

    private void clearData ()
    {
        this.college.clearData ();
    }


    private void menuAddStudent (String nm, int ag, String sx, String mjr, int yr)
    {
        this.college.addStudent (nm, ag, sx, mjr,yr);
    }

    private void menuAddProfessor (String nm, int ag, String sx, String pos, int yrexp)
    {
        this.college.addProfessor (nm, ag, sx, pos, yrexp);
    }

    private void menuAddSubject (String nm, int sem, String fld)
    {
        this.college.addSubject (nm, sem, fld);
    }

    private void menuAddMark (String nmstud, String nmprof, String nmsub, String date, int val) throws Exception {
        this.college.addMark (college.findStudent(nmstud), college.findProfessor(nmprof), college.findSubject(nmsub),
                new SimpleDateFormat("dd/MM/yyyy").format(date), val);
    }


    private void clear ()
    {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    public static ArrayList<String> getVals (String request)
    {
        ArrayList <String> valsOut     = new ArrayList<>();
        StringBuilder strBld = new StringBuilder();
        boolean inQuote = false;

        request = request.trim ();
        for (char chr : request.toCharArray ())
        {

            if (chr == '"')
                inQuote = !inQuote;

            if (chr == ' ' && !inQuote)
            {
                valsOut.add (strBld.toString ());
                strBld.delete (0, strBld.length ());
            }
            else
                strBld.append (chr);
        }
        valsOut.add (strBld.toString ());

        for (int i = 0; i < valsOut.size (); i++)
            if (valsOut.get (i).charAt (0) == '"' && valsOut.get (i).charAt (valsOut.get (i).length () - 1) == '"')
                valsOut.set (i, valsOut.get (i).substring (1, valsOut.get (i).length () - 1));

        return (valsOut);
    }

    public void request (String request) throws Exception
    {
        menuSelect(Menu.getVals(request));
    }

    private void lenCheck (ArrayList <String> vals, int nr, boolean constraint) throws Exception
    {
        if (constraint && vals.size () > nr)
            throw new Exception ("Numar gresit de argumente");
        if (vals.size () < nr)
            throw new Exception ("Numar gresit de argumente");
    }

    public void console () throws Exception
    {
        BufferedReader rdr  = new BufferedReader( new InputStreamReader(System.in));
        String         request = "";

        System.out.println ("Se deschide consola");
        loadData ();
        System.out.println ("Poti scrie 'ajutor' pentru a se afisa o lista de comenzi");
        System.out.print (">>> ");
        while (!request.equals ("iesire"))
        {
            request = rdr.readLine();
            try
            {
                this.request (request);
            }
            catch (Exception e)
            {
                System.out.println (e.getMessage ());
            }
            System.out.print (">>> ");
        }
    }



    private void menuSelect (ArrayList <String> vals) throws Exception
    {
        switch (vals.get(0)) {
            case "ajutor":
                help (vals);
                break;

            case "curatare":
                this.lenCheck(vals, 1, true);
                clear ();
                break;

            case "iesire":
                System.out.println ("Se salveaza in '.csvs/'...");
                saveData ();
                System.out.println ("La revedere!");
                break;

            case "salvare":
                if (vals.size () == 2) {
                    if (vals.get(1).equals(".csvs")) {
                        System.out.println("Acel folder nu poate fi folosit pentru salvarea datelor");
                        break;
                    }
                    System.out.println ("Se salveaza in '" + vals.get (1) + "/'...");
                    saveData ();
                } else if (vals.size () == 1) {
                    System.out.println ("Se salveaza in '.csvs/'...");
                    saveData ();
                } else
                    this.lenCheck(vals, 2, true);
                break;

            case "incarcareDate":
                if (vals.size () == 2) {
                    System.out.println ("Se incarca datele din '" + vals.get (1) + "'.");
                    loadData();
                }
                else if (vals.size () == 1) {
                    System.out.println ("Se incarca datele din '.csvs/'...");
                    loadData();
                }
                else
                    this.lenCheck(vals, 2, true);
                break;

            case "stergereDate":
                this.lenCheck (vals, 1, true);
                clearData ();
                break;

            case "adaugareStudent":
                this.lenCheck(vals, 7, true);
                menuAddStudent (vals.get (1) + " " + vals.get (2), Integer.parseInt(vals.get (3)),
                        vals.get(4), vals.get(5), Integer.parseInt(vals.get(6)));
                break;

            case "adaugareProfesor":
                this.lenCheck(vals, 7, true);
                menuAddProfessor(vals.get(1) + " " + vals.get(2), Integer.parseInt(vals.get(3)), vals.get(4), vals.get(5),
                        Integer.parseInt(vals.get(6)));
                break;

            case "adaugareMaterie":
                this.lenCheck(vals, 4, true);
                menuAddSubject(vals.get(1), Integer.parseInt(vals.get(2)), vals.get(3));
                break;

            case "adaugareNota":
                this.lenCheck(vals, 8, true);
                menuAddMark(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4), vals.get(5),
                        vals.get(6), Integer.parseInt(vals.get(7)));
                break;

            case "afisareStudenti":
                this.lenCheck(vals, 1, true);
                menuShowStudents();
                break;

            case "afisareProfesori":
                this.lenCheck(vals, 1, true);
                menuShowProfessors();
                break;

            case "afisareMaterii":
                this.lenCheck(vals, 1, true);
                menuShowSubjects();
                break;

            case "afisareNote":
                this.lenCheck(vals, 1, true);
                menuShowMarks();
                break;

            default:
                throw new Exception ("Comanda necunoscuta. Incearca sa scrii 'ajutor'.");
        }
    }


    private void help (ArrayList <String> vals) {

        String[] ajtr = {
                "'ajutor'",
                "'curatare'",
                "'iesire'",
                "'salvare [numeFolder]'",
                "'incarcareDate [numeFolder]'",
                "'stergereDate [numeFolder]'",
                "'clearData'",
                "'showTeachers'",
                "'adaugaProfesor <nume> <prenume> <varsta> <sex> <pozitie> <aniDeExperienta'",
                "'adaugaStudent <nume> <prenume> <varsta> <sex> <specializare> <an>'",
                "'adaugaMaterie <nume> <nrSemestre> <domeniu>'",
                "'adaugaNota <numeStudent> <prenumeStudent> <numeProfesor> <prenumeProfesor> " +
                        "<numeMaterie> <data(in format dd/mm//yyyy> <valoare>'",
                "'afisareStudenti'",
                "'afisareProfesori'",
                "'afisareMaterii'",
                "'afisareNote'",
        };

        if (vals.size() == 1) {
            for (String command : ajtr)
                System.out.println(command);
        }
        else if (vals.size() > 2) {
            System.out.println ("Prea multe valori introduse");
        }
        else
            System.out.println ("Comanda nu exista");
        }
    }


