package wind.beanmapper.property;

/**
 * Created by wind on 2016/8/13.
 */
public class PropertyNameMapper implements PropertyMapper {

    private String nameA;
    private String nameB;

    public PropertyNameMapper(String nameA, String nameB) {
        this.nameA = nameA;
        this.nameB = nameB;
    }

    @Override
    public String nameA() {
        return nameA;
    }

    @Override
    public String nameB() {
        return nameB;
    }

    @Override
    public boolean match(String name) {
        return name.equals(nameA) || name.equals(nameB);
    }

    @Override
    public String map(String name) {
        return name.equals(nameA) ? nameB : name.equals(nameB) ? nameA : name;
    }
}
