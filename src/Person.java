abstract class Person {
    private String name;
    private int age;
    private final String sex;

    public Person(String nm, int ag, String sx){
        this.name = nm;
        this.age = ag;
        this.sex = sx;
    }

    public abstract void display();

    public void increaseAge(){
        this.age += 1;
    }

    public String getName() { return name; }

    public void setName(String nm) {
        this.name = nm;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int ag) {
        this.age = ag;
    }

    public String getSex() { return sex; }

}
