package wind.beanmapper.property;

/**
 * Created by wind on 2016/8/15.
 */
public interface PropertyMapper {

    String nameA();

    String nameB();

    boolean match(String name);

    String map(String name);

}
