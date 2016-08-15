package wind.beanmapper.mapper;

import java.util.Map;

import wind.beanmapper.config.MapperConfig;
import wind.beanmapper.config.PropertyConfig;
import wind.beanmapper.converter.Converter;
import wind.beanmapper.property.Property;
import wind.beanmapper.property.PropertyMapper;
import wind.beanmapper.property.PropertyResolver;

/**
 * Created by wind on 2016/8/13.
 */
public class BaseMapper<S, D> implements Mapper<S, D> {


    private MapperConfig config;

    public BaseMapper(MapperConfig config) {
        this.config = config;
    }

    @Override
    public D map(S source, Class<D> desClass) {
        PropertyResolver resolver = config.getPropertyResolver();

        Map<String, Property<?>> sProperties = resolver.getProperties(source.getClass());
        Map<String, Property<?>> dProperties = resolver.getProperties(desClass);

        D des = config.getInstantiater().instantiate(desClass);

        for (Map.Entry<String, Property<?>> entry : dProperties.entrySet()) {
            String nameD = entry.getKey();
            Property propertyD = entry.getValue();

            String mappedName = nameD;

            PropertyMapper propertyMapper = config.getPropertyMapper(nameD);
            if (propertyMapper != null) {
                mappedName = propertyMapper.map(nameD);
            }

            Property propertyS = sProperties.get(mappedName);

            PropertyConfig propertyConfig = config.getPropertyConfig(nameD);

            if (propertyS.getType().equals(propertyD.getType())) {
                propertyD.set(des, propertyS.get(source));
            } else {
                Object converted;
                Converter converter = propertyConfig.getConverter();
                if (converter == null) {
                    converter = config.getConverter(propertyS.getType(), propertyD.getType());
                }
                if (propertyS.getType().equals(converter.typeA())) {
                    converted = converter.convert(propertyS.get(source));
                } else {
                    converted = converter.reconvert(propertyS.get(source));
                }
                propertyD.set(des, converted);
            }
        }
        return des;
    }
}
