package wind.beanmapper;

import java.util.List;

/**
 * Created by wind on 2016/8/13.
 */
public interface PropertyResolver {

    List<Property<?>> getProperties(Class cls);

}
