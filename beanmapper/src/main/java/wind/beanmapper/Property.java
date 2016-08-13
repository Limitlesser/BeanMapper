package wind.beanmapper;

/**
 * Created by wind on 2016/8/13.
 */
public class Property<T> {

    interface Entry<T> {

        T get();

        void set(T value);
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

    public T get() {
        return entry.get();
    }

    public void set(T value) {
        entry.set(value);
    }
}
