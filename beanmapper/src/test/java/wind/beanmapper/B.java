package wind.beanmapper;

/**
 * Created by wind on 2016/8/15.
 */
public class B {

    private String Name;
    private int age;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "B{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }
}
