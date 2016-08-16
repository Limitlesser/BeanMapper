package wind.beanmapper.mapper;

import java.util.Map;

import wind.beanmapper.config.MapperConfig;
import wind.beanmapper.config.PropertyConfig;
import wind.beanmapper.converter.Converter;
import wind.beanmapper.property.Property;
import wind.beanmapper.property.PropertyMapper;

/**
 * Created by wind on 2016/8/13.
 */
public class BaseMapper implements Mapper {


    private MapperConfig config;

    public BaseMapper(MapperConfig config) {
        this.config = config;
    }

    @Override
    public <S, D> D map(S source, Class<D> desClass) {

        Map<String, Property<?>> sProperties = resolveProperties(source.getClass());
        Map<String, Property<?>> dProperties = resolveProperties(desClass);

        D des = instantiate(desClass);

        for (Map.Entry<String, Property<?>> entry : dProperties.entrySet()) {

            String nameD = entry.getKey();
            Property propertyD = entry.getValue();

            Property propertyS = mapProperty(nameD, sProperties);
            if (propertyS == null) {
                continue;
            }

            Object value = convertProperty(propertyS.get(source), propertyS, propertyD);

            propertyD.set(des, value);
        }
        return des;
    }

    protected Converter getConverter(Property propertyS, Property propertyD) {
        Converter converter = null;
        PropertyConfig propertyConfig = config.getPropertyConfig(propertyD.getName());
        if (propertyConfig != null) {
            converter = propertyConfig.getConverter();
        }
        if (converter == null) {
            converter = config.getConverter(propertyS.getType(), propertyD.getType());
        }
        return converter;
    }

    protected Object convertProperty(Object value, Property propertyS, Property propertyD) {
        Converter converter = getConverter(propertyS, propertyD);
        if (converter != null) {
            if (propertyS.getType().equals(converter.typeA())) {
                value = converter.convert(value);
            } else {
                value = converter.reconvert(value);
            }
        }
        return value;
    }

    protected Map<String, Property<?>> resolveProperties(Class cls) {
        return config.getPropertyResolver().getProperties(cls);
    }

    protected <T> T instantiate(Class<T> cls) {
        return config.getInstantiate().instantiate(cls);
    }

    protected Property<?> mapProperty(String nameD, Map<String, Property<?>> sProperties) {
        String mappedName = nameD;

        PropertyMapper propertyMapper = config.getPropertyMapper(nameD);
        if (propertyMapper != null) {
            mappedName = propertyMapper.map(nameD);
        }

        return sProperties.get(mappedName);
    }


}
