package wind.beanmapper.instantiation;

/**
 * Created by wind on 2016/8/15.
 */
public interface Instantiater {

    <T> T instantiate(Class<T> cls);

}
