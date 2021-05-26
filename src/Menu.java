import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Menu
{
    private Facultate college;

    public Menu (Facultate fac)
    {
        college = fac;
    }

    private Statement establishConn(){
    try {

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc-pao", "root", "pao1234");

        Statement statement = conn.createStatement();

        return statement;

    }catch(Exception e){
        e.printStackTrace();
        return null;
    }

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

    private void jdbcShowProfessors(){
        Statement statement = establishConn();
        try {
            ResultSet resultSet = statement.executeQuery("select * from professor;");
            System.out.println ("Profesori: (nume, varsta, gen, pozitie, ani de experienta)");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " " + resultSet.getInt("age")
                        + " " + resultSet.getString("sex") + " " + resultSet.getString("position") +
                        " " + resultSet.getInt("years_of_experience"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private void jdbcShowStudents(){
        Statement statement = establishConn();
        try {
            ResultSet resultSet = statement.executeQuery("select * from student;");
            System.out.println ("Studenti: (nume, varsta, gen, specialitate, an)");
            while (resultSet.next()) {

                System.out.println(resultSet.getString("name") + " " + resultSet.getInt("age")
                + " " + resultSet.getString("sex") + " " + resultSet.getString("major") +
                        " " + resultSet.getInt("year"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private void jdbcShowSubjects(){
        Statement statement = establishConn();
        try {
            ResultSet resultSet = statement.executeQuery("select * from subject;");
            System.out.println ("Materii: (nume, semestre, domeniu)");
            while (resultSet.next()) {

                System.out.println(resultSet.getString("name")+ " " + resultSet.getInt("semesters")
                        + " " +resultSet.getString("field"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private void jdbcShowMarks(){
        Statement statement = establishConn();
        try {
            ResultSet resultSet = statement.executeQuery("select * from mark;");
            System.out.println ("Note: (nume student, nume profesor, materie, data, valoare)");
            while (resultSet.next()) {

                System.out.println(resultSet.getString("studentname")+ " " +
                        resultSet.getString("profname") + " " + resultSet.getString("subject") +
                        " " + resultSet.getDate("date") + " " + resultSet.getInt("value"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
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

    private void jdbcAddStudent (String nm, int ag, String sx, String mjr, int yr)
    {
        Statement statement = establishConn();
        try{
            statement.executeUpdate("INSERT INTO student VALUES " +
                    "('"+nm+"','"+ag+"','"+sx+"','"+mjr+"','"+yr+"')");
            this.college.addStudent (nm, ag, sx, mjr,yr);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void menuAddStudent (String nm, int ag, String sx, String mjr, int yr)
    {
        this.college.addStudent (nm, ag, sx, mjr,yr);
    }

    private void jdbcAddProfessor (String nm, int ag, String sx, String pos, int yrsexp)
    {
        Statement statement = establishConn();
        try{
            statement.executeUpdate("INSERT INTO professor VALUES " +
                    "('"+nm+"','"+ag+"','"+sx+"','"+pos+"','"+yrsexp+"')");
            this.college.addProfessor (nm, ag, sx, pos, yrsexp);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    private void menuAddProfessor (String nm, int ag, String sx, String pos, int yrexp)
    {
        this.college.addProfessor (nm, ag, sx, pos, yrexp);
    }

    private void jdbcAddSubject (String nm, int sem, String fld)
    {
        Statement statement = establishConn();
        try{
            statement.executeUpdate("INSERT INTO subject VALUES " +
                    "('"+nm+"','"+sem+"','"+fld+"')");
            this.college.addSubject (nm, sem, fld);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void menuAddSubject (String nm, int sem, String fld)
    {
        this.college.addSubject (nm, sem, fld);
    }

    private void jdbcAddMark (String nmstud, String nmprof, String nmsub, String date, int val)
    {
        Statement statement = establishConn();
        try{
            statement.executeUpdate("INSERT INTO mark VALUES " +
                    "('"+nmstud+"','"+nmprof+"','"+nmsub+"','"+date+"','"+val+"')");
            this.college.addMark (college.findStudent(nmstud), college.findProfessor(nmprof), college.findSubject(nmsub),
                    date, val);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void menuAddMark (String nmstud, String nmprof, String nmsub, String date, int val) throws Exception {
        this.college.addMark (college.findStudent(nmstud), college.findProfessor(nmprof), college.findSubject(nmsub),
                date, val);
    }

    private void jdbcRemoveStudent(String nm){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("delete from student where name = '"+ nm+"';");
            this.college.removeStudent(nm);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void jdbcRemoveProfessor(String nm){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("delete from professor where name = '" + nm +"';");
            this.college.removeProfessor(nm);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void jdbcRemoveSubject(String nm){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("delete from subject where name = '"+ nm + "';");
            this.college.removeSubject(nm);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void jdbcRemoveMark(String nmstud, String nmprof, String nmsub, String date){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("delete from mark where studentname = '" + nmstud + "' and profname = '" +
                    nmprof + "' and subject = '" + nmsub  + "' and date = '" + date + "';");
            this.college.removeMark(nmstud, nmprof, nmsub, date);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void jdbcUpdateMark(String nmstudVechi, String nmprofVechi, String nmsubVechi, String dateVechi,
                                int val){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("update mark set value = " + val +
                    " where studentname = '" + nmstudVechi + "' and profname = '" + nmprofVechi +
                    "' and subject = '" + nmsubVechi + "' and date = '" + dateVechi + "';");
            this.college.updateMark(nmstudVechi, nmprofVechi, nmsubVechi, dateVechi, val);

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void jdbcUpdateStudent(String nmVechi, String nm, int ag, String mjr, int yr){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("update student set name = '" + nm + "', age = " +
                    ag + ", major = '" + mjr +"', year = " + yr +
                    " where name = '" + nmVechi + "';");
            this.college.updateStudent(nmVechi, nm, ag, mjr, yr);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void jdbcUpdateProfessor(String nmVechi, String nm, int ag, String pos, int yrsexp){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("update professor set name = '" + nm + "', age = " +
                    ag + ", position = '" + pos +"', years_of_experience = " + yrsexp +
                    " where name = '" + nmVechi + "';");
            this.college.updateProfessor(nmVechi, nm, ag, pos, yrsexp);

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private void jdbcUpdateSubject(String nmVechi, String nm, int sem){
        Statement statement = establishConn();
        try{
            statement.executeUpdate("update subject set name = '" + nm + "', semesters = " +
                    sem + " where name = '" + nmVechi + "';");
            this.college.updateSubject(nmVechi, nm, sem);

        }catch(Exception e){
            e.printStackTrace();
        }

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
                jdbcAddStudent (vals.get (1) + " " + vals.get (2), Integer.parseInt(vals.get (3)),
                        vals.get(4), vals.get(5), Integer.parseInt(vals.get(6)));
                break;

            case "adaugareProfesor":
                this.lenCheck(vals, 7, true);
                jdbcAddProfessor(vals.get(1) + " " + vals.get(2), Integer.parseInt(vals.get(3)), vals.get(4), vals.get(5),
                        Integer.parseInt(vals.get(6)));
                break;

            case "adaugareMaterie":
                this.lenCheck(vals, 4, true);
                jdbcAddSubject(vals.get(1), Integer.parseInt(vals.get(2)), vals.get(3));
                break;

            case "adaugareNota":
                this.lenCheck(vals, 8, true);
                jdbcAddMark(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4), vals.get(5),
                        vals.get(6), Integer.parseInt(vals.get(7)));
                break;

            case "afisareStudenti":
                this.lenCheck(vals, 1, true);
                jdbcShowStudents();
                break;

            case "afisareProfesori":
                this.lenCheck(vals, 1, true);
                jdbcShowProfessors();
                break;

            case "afisareMaterii":
                this.lenCheck(vals, 1, true);
                jdbcShowSubjects();
                break;

            case "afisareNote":
                this.lenCheck(vals, 1, true);
                jdbcShowMarks();
                break;

            case "stergereStudent":
                this.lenCheck(vals, 3, true);
                jdbcRemoveStudent(vals.get(1) + " " + vals.get(2));
                break;

            case "stergereProfesor":
                this.lenCheck(vals, 3, true);
                jdbcRemoveProfessor(vals.get(1) + " " + vals.get(2));
                break;

            case "stergereMaterie":
                this.lenCheck(vals, 2, true);
                jdbcRemoveSubject(vals.get(1));
                break;

            case "stergereNota":
                this.lenCheck(vals, 7, true);
                jdbcRemoveMark(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4),
                        vals.get(5), vals.get(6));
                break;

            case "editareNota":
                this.lenCheck(vals, 8, true);
                jdbcUpdateMark(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4),
                        vals.get(5), vals.get(6), Integer.parseInt(vals.get(7)));
                break;

            case "editareStudent":
                this.lenCheck(vals, 8, true);
                jdbcUpdateStudent(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4),
                        Integer.parseInt(vals.get(5)), vals.get(6), Integer.parseInt(vals.get(7)));
                break;

            case "editareProfesor":
                this.lenCheck(vals, 8, true);
                jdbcUpdateProfessor(vals.get(1) + " " + vals.get(2), vals.get(3) + " " + vals.get(4),
                        Integer.parseInt(vals.get(5)), vals.get(6) , Integer.parseInt(vals.get(7)));
                break;

            case "editareMaterie":
                this.lenCheck(vals, 4, true);
                jdbcUpdateSubject(vals.get(1), vals.get(2), Integer.parseInt(vals.get(3)));
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
                        "<numeMaterie> <data(in format yyyy/MM/dd> <valoare>'",
                "'afisareStudenti'",
                "'afisareProfesori'",
                "'afisareMaterii'",
                "'afisareNote'",
                "'stergereStudent' <nume> <prenume>",
                "'stergereProfesor' <nume> <prenume>",
                "'stergereMaterie' <nume>",
                "'stergereNota' <numeStudent> <prenumeStudent> <numeProfesor> <prenumeProfesor>"
                                               +"<numeMaterie> <data(in format yyyy/MM/dd)>",
                "'editareStudent' <numeVechiStudent> <prenumeVechiStudent> <numeNouStudent>" +
                        "<prenumeNouStudent> <varstaNoua> <specialitateNoua> <anNou>",
                "'editareProfesor' <numeVechiProfesor> <prenumeVechiProfesor> <numeNouProfesor>" +
                        "<prenumeNouProfesor> <varstaNoua> <pozitieNoua> <aniDeExperientaNou>",
                "'editareMaterie' <numeVechiMaterie> <numeNouMaterie> <nrDeSemestreNou>",
                "'editareNota' <numeStudent> <prenumeStudent> <numeProfesor> <prenumeProfesor> <materie>" +
                        "<data(in format yyyy/MM/dd)> <valoareNoua>"
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
