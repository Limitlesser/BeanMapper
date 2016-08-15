package wind.beanmapper.property;

/**
 * Created by wind on 2016/8/13.
 */
public class Property<T> {

    interface Entry<T> {

        T get(Object object);

        void set(Object object, T value);
    }

    private String name;

    private Class<T> type;

    private Entry<T> entry;

    public Property(String name, Class<T> type, Entry<T> entry) {
        this.name = name;
        this.type = type;
        this.entry = entry;
    }

    public String getName() {
        return name;
    }

    public Class<T> getType() {
        return type;
    }

    public T get(Object object) {
        return entry.get(object);
    }

    public void set(Object object, T value) {
        entry.set(object, value);
    }
}
