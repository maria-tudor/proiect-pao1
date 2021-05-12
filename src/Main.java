import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static void main(String[] vals) throws Exception {
        Date data = new SimpleDateFormat("dd/MM/yyyy").parse("20/06/2015");
        System.out.println(data);
        Facultate college = new Facultate();
        Menu menu = new Menu (college);

        menu.console ();
    }
}
    /*
// Meniul mai trebuie modificat deoarece am o problema cu input-urile, dar am apelat majoritatea functiilor direct aici
    public static void main(String[] args) throws ParseException {
        Scanner in = new Scanner(System.in);
        Facultate college = new Facultate();
        Professor prof1 = new Professor("Toma Alexia", 53, "Feminin", "Decan", 25);
        Professor prof2 = new Professor("Vlagali Razvan", 45, "Masculin", "Prodecan", 15);
        Professor prof3 = new Professor("Popescu Ryan", 51, "Masculin", "Profesor", 20);
        Professor prof4 = new Professor("Abdula Dragos", 29, "Masculin", "Asistent universitar", 5);
        Student stud1 = new Student("Bud Catalin", 23, "Masculin", "Matematica", 2);
        Student stud2 = new Student("Andrei Antemir", 30, "Masculin", "Informatica", 3);
        Student stud3 = new Student("Alessia Ioana", 25, "Feminin", "Medicina", 6);
        Student stud4 = new Student("Constantin Dragos", 23, "Masculin", "Inginerie electrica", 4);
        Subject sub1 = new Subject("Algebra",3, "Matematica");
        Subject sub2 = new Subject("Baze de date", 1, "Informatica");
        Subject sub3 = new Subject("Anatomie", 6, "Medicina");
        Subject sub4 = new Subject("Circuite electrice", 2, "Inginerie electrica");
        Mark mark1 = new Mark(stud1, prof1, sub1, "25/04/2020", 10);
        Mark mark2 = new Mark(stud2, prof2, sub2, "10/02/2019", 8);
        Mark mark3 = new Mark(stud3, prof3, sub3, "12/03/2021", 5);
        Mark mark4 = new Mark(stud4, prof4, sub4, "25/01/2021", 7);
        Mark mark5 = new Mark(stud4, prof4, sub4, "25/01/2020", 10);
        Mark mark6 = new Mark(stud4, prof4, sub4, "25/01/2019", 4);
        Mark mark7 = new Mark(stud4, prof4, sub3, "25/01/2019", 1);
        prof1.addSubject(sub1);
        prof2.addSubject(sub2);
        prof3.addSubject(sub3);
        prof4.addSubject(sub4);
        college.addProfessor(prof1);
        college.addProfessor(prof2);
        college.addProfessorToCollege(prof3);
        college.addProfessorToCollege(prof4);
        college.addSubjectToCollege(sub1);
        college.addSubjectToCollege(sub2);
        college.addSubjectToCollege(sub3);
        college.addSubjectToCollege(sub4);
        prof1.addStudent(stud1);
        prof2.addStudent(stud2);
        prof3.addStudent(stud3);
        prof4.addStudent(stud4);
        college.addStudentToCollege(stud1);
        college.addStudentToCollege(stud2);
        college.addStudentToCollege(stud3);
        college.addStudentToCollege(stud4);
        sub1.addProfessor(prof1);
        sub2.addProfessor(prof2);
        sub3.addProfessor(prof3);
        sub4.addProfessor(prof4);
        stud1.addSubject(sub1);
        stud2.addSubject(sub2);
        stud3.addSubject(sub3);
        stud4.addSubject(sub4);
        prof1.display();
        stud1.display();
        sub1.display();
        mark1.display();
        /*CONVERTIE DATA!!!*/
    /*
        prof2.increaseYearsOfExperience();
        prof2.increaseAge();
        System.out.println(prof2.getAge());
        prof2.display();
        prof2.removeStudent(stud2);
        stud1.increaseYear();
        System.out.println("Data notei 2: " + mark2.getDate());
        mark3.display();
        mark7.display();
        mark6.display();
        mark4.display();
        mark5.display();
        college.addMarkToCollege(mark1);
        college.addMarkToCollege(mark2);
        college.addMarkToCollege(mark3);
        college.addMarkToCollege(mark4);
        college.addMarkToCollege(mark5);
        college.addMarkToCollege(mark6);
        college.addMarkToCollege(mark7);

        System.out.println(stud4.worstGradeInSubject(sub4.getName()));
        System.out.println(stud4.averageInSubject(sub4.getName()));
        stud4.showMarks();
/*
            System.out.println("Pentru operatii cu studenti si/sau note apasa tasta 1.");
            System.out.println("Pentru operatii cu profesori apasa tasta 2.");
            System.out.println("Pentru operatii cu materii apasa tasta 3.");
            System.out.println("Daca nu mai vrei sa efectuezi alte operatiuni apasa tasta 0.");

            boolean quit = false;
            int x = in.nextInt();

            switch (x) {
                case 1:
                    System.out.println("Pentru a adauga un student apasa tasta 1");
                    System.out.println("Pentru a afla media unui student la o anumita materie apasa tasta 2 si dupa tasteaza numele materiei");
                    System.out.println("Pentru a afla cea mai mica nota a unui student la o anumita materie apasa tasta 3 si dupa tasteaza materia");
                    System.out.println("Pentru a inscrie un student existent la o anumita materie apassa tasta 4 si ofera numele materiei");
                    System.out.println("Pentru a vedea notele unui student apasa tasta 5 si ofera numele studentului");
                    System.out.println("Pentru a modifica numele unui student apasa 6");
                    System.out.println("Pentru a modifica varsta unui student apasa 7");
                    System.out.println("Pentru a modifica anul unui student apasa 8");
                    System.out.println("Pentru a afla varsta unui student apasa 9");
                    System.out.println("Pentru a afla specialitatea unui student apasa 10");
                    System.out.println("Pentru a afla anul unui student apasa 11");

                    int y = in.nextInt();
                    switch (y) {
                        case 1:
                            System.out.println("Nume: ");
                            String nume = in.nextLine();
                            System.out.println("Varsta: ");
                            int varsta = in.nextInt();
                            System.out.println("Sex: ");
                            String sex = in.nextLine();
                            System.out.println("Specialitate: ");
                            String specialitate = in.nextLine();
                            System.out.println("An: ");
                            int an = in.nextInt();
                            Student stud = new Student(nume, varsta, sex, specialitate, an);
                            college.addStudentToCollege(stud);
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            menu();
                            break;
                        case 2:
                            System.out.println("Nume: ");
                            String nume1 = in.nextLine();
                            System.out.println("Materie: ");
                            String numeMaterie = in.nextLine();
                            for (Student s : college.students) {
                                if (s.getName().equals(nume1)) {
                                    System.out.println(s.averageInSubject(numeMaterie));
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            menu();
                            break;
                        case 3:
                            System.out.println("Nume: ");
                            String nume2 = in.nextLine();
                            System.out.println("Materie: ");
                            String numeMaterie1 = in.nextLine();
                            for (Student s1 : college.students) {
                                if (s1.getName().equals(nume2)) {
                                    System.out.println(s1.worstGradeInSubject(numeMaterie1));
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 4:
                            System.out.println("Nume: ");
                            String nume3 = in.nextLine();
                            System.out.println("Materie: ");
                            String numeMaterie2 = in.nextLine();
                            int nr = -1;
                            int nr2 = -1;
                            for (Subject sb : college.subjects) {
                                nr += 1;
                                if (numeMaterie2.equals(sb.getName())) {
                                    nr2 = nr;
                                }
                            }
                            for (Student s3 : college.students) {
                                if (s3.getName().equals(nume3)) {
                                    s3.addSubject(college.subjects.get(nr2));
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 5:
                            System.out.println("Nume: ");
                            String nume4 = in.nextLine();
                            System.out.println("Materie: ");
                            String numeMaterie3 = in.nextLine();
                            for (Student s4 : college.students) {
                                if (nume4.equals(s4.getName())) {
                                    s4.showMarks();
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 6:
                            System.out.println("Nume actual: ");
                            String nume5 = in.nextLine();
                            System.out.println("Nume nou: ");
                            String nume7 = in.nextLine();
                            for (Student s4 : college.students) {
                                if (s4.getName().equals(nume5)) {
                                    s4.setName(nume7);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 7:
                            System.out.println("Nume: ");
                            String nume6 = in.nextLine();
                            System.out.println("Varsta noua: ");
                            int v = in.nextInt();
                            for (Student s5 : college.students) {
                                if (s5.getName().equals(nume6)) {
                                    s5.setAge(v);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 8:
                            System.out.println("Nume: ");
                            String nume9 = in.nextLine();
                            System.out.println("Anul va creste cu 1.");
                            for (Student s6 : college.students) {
                                if (s6.getName().equals(nume9)) {
                                    s6.increaseYear();
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 9:
                            System.out.println("Nume: ");
                            String nume10 = in.nextLine();
                            for (Student s7 : college.students) {
                                if (s7.getName().equals(nume10)) {
                                    System.out.println(s7.getAge());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 10:
                            System.out.println("Nume: ");
                            String nume11 = in.nextLine();
                            for (Student s8 : college.students) {
                                if (s8.getName().equals(nume11)) {
                                    System.out.println(s8.getMajor());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 11:
                            System.out.println("Nume: ");
                            String nume12 = in.nextLine();
                            for (Student s9 : college.students) {
                                if (s9.getName().equals(nume12)) {
                                    System.out.println(s9.getYear());
                                }
                            }

                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Pentru a afla varsta unui profesor apasa tasta 1");
                    System.out.println("Pentru a afla pozitia unui profesor apasa tasta 2");
                    System.out.println("Pentru a afla cati ani de experienta are un profesor apasa tasta 3");
                    System.out.println("Pentru a modifica numele unui profesor apasa tasta 4");
                    System.out.println("Pentru a modifica varsta unui profesor apasa tasta 5");
                    System.out.println("Pentru a modifica pozitia unui profesor apasa tasta 6");
                    System.out.println("Pentru a modifica anii de experienta ai unui profesor apasa tasta 7");

                    int z = in.nextInt();
                    switch (z) {
                        case 1:
                            System.out.println("Nume: ");
                            String nume12 = in.nextLine();
                            for (Professor s10 : college.professors) {
                                if (s10.getName().equals(nume12)) {
                                    System.out.println(s10.getAge());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 2:
                            System.out.println("Nume: ");
                            String nume13 = in.nextLine();
                            for (Professor s11 : college.professors) {
                                if (s11.getName().equals(nume13)) {
                                    System.out.println(s11.getPosition());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 3:
                            System.out.println("Nume: ");
                            String nume14 = in.nextLine();
                            for (Professor s12 : college.professors) {
                                if (s12.getName().equals(nume14)) {
                                    System.out.println(s12.getYearsOfExperience());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 4:
                            System.out.println("Nume actual: ");
                            String nume15 = in.nextLine();
                            System.out.println("Nume nou: ");
                            String nume16 = in.nextLine();
                            for (Professor s13 : college.professors) {
                                if (s13.getName().equals(nume15)) {
                                    s13.setName(nume16);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 5:
                            System.out.println("Nume");
                            String nume17 = in.nextLine();
                            System.out.println("Varsta va creste cu 1.");
                            for (Professor s14 : college.professors) {
                                if (s14.getName().equals(nume17)) {
                                    s14.increaseAge();
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 6:
                            System.out.println("Nume");
                            String nume18 = in.nextLine();
                            System.out.println("Pozitie noua: ");
                            String pos1 = in.nextLine();
                            for (Professor s15 : college.professors) {
                                if (s15.getName().equals(nume18)) {
                                    s15.setPosition(pos1);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 7:
                            System.out.println("Nume");
                            String nume19 = in.nextLine();
                            System.out.println("Anii de experienta vor creste cu 1.");
                            for (Professor s16 : college.professors) {
                                if (s16.getName().equals(nume19)) {
                                    s16.increaseYearsOfExperience();
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                    }
                    break;
                case 3:
                    System.out.println("Pentru a afla durata unei materii apasa tasta 1");
                    System.out.println("Pentru a afla specialitatea unei materii apasa tasta 2");
                    System.out.println("Pentru a modifica numele unei materii apasa tasta 3");
                    System.out.println("Pentru a modifica durata unei materii apasa tasta 3");

                    int a = in.nextInt();
                    switch (a) {
                        case 1:
                            System.out.println("Nume: ");
                            String nume20 = in.nextLine();
                            for (Subject s17 : college.subjects) {
                                if (s17.getName().equals(nume20)) {
                                    System.out.println(s17.getSemesters() + " semestre");
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 2:
                            System.out.println("Nume: ");
                            String nume21 = in.nextLine();
                            for (Subject s18 : college.subjects) {
                                if (s18.getName().equals(nume21)) {
                                    System.out.println(s18.getField());
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 3:
                            System.out.println("Nume actual: ");
                            String nume22 = in.nextLine();
                            System.out.println("Nume nou: ");
                            String nume23 = in.nextLine();
                            for (Subject s19 : college.subjects) {
                                if (s19.getName().equals(nume22)) {
                                    s19.setName(nume23);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                        case 4:
                            System.out.println("Nume actual: ");
                            String nume24 = in.nextLine();
                            System.out.println("Durata noua: ");
                            int durata = in.nextInt();
                            for (Subject s20 : college.subjects) {
                                if (s20.getName().equals(nume24)) {
                                    s20.setSemesters(durata);
                                }
                            }
                            System.out.println("Operatie efectuata cu succes! Apasa urmatoarea tasta dorita!");
                            break;
                    }

                    break;
                case 0:

                    quit = true;

                    break;

                default:

                    System.out.println("Alegere invalida.");
            }
            while (!quit) ;

        System.out.println("La revedere!");


    }
*/
