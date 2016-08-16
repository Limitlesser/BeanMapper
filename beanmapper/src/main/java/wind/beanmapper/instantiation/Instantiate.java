package wind.beanmapper.instantiation;

/**
 * Created by wind on 2016/8/15.
 */
public interface Instantiate {

    <T> T instantiate(Class<T> cls);

}
