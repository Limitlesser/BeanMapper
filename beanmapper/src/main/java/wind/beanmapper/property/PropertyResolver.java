package wind.beanmapper.property;

import java.util.Map;

/**
 * Created by wind on 2016/8/13.
 */
public interface PropertyResolver {

    Map<String, Property<?>> getProperties(Class cls);

}
