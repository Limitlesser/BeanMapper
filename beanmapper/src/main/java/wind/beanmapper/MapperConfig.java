package wind.beanmapper;


import java.util.List;
import java.util.Map;

/**
 * Created by wind on 2016/8/13.
 */
public class MapperConfig {

    private List<PropertyNameMap> propertyNameMaps;

    private Map<PropertyNameMap, PropertyConfig<?, ?>> propertyConfigMap;

    public boolean mapNull;

    public boolean mapNullReverse;

}
