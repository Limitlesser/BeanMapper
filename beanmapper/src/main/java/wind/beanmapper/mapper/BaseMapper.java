package wind.beanmapper.mapper;

import java.util.Map;

import wind.beanmapper.config.MapperConfig;
import wind.beanmapper.config.PropertyConfig;
import wind.beanmapper.converter.Converter;
import wind.beanmapper.converter.Converters;
import wind.beanmapper.property.Property;
import wind.beanmapper.property.PropertyMapper;
import wind.beanmapper.property.PropertyResolver;
import wind.beanmapper.utils.ReflectUtils;

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

            if (propertyS == null) {
                continue;
            }

            PropertyConfig propertyConfig = config.getPropertyConfig(nameD);
            Converter converter = null;
            if (propertyConfig != null) {
                converter = propertyConfig.getConverter();
            }

            if (propertyS.getType().equals(propertyD.getType()) && converter == null) {
                propertyD.set(des, propertyS.get(source));
            } else {
                Object converted;

                if (converter == null) {
                    converter = config.getConverter(propertyS.getType(), propertyD.getType());
                }
                if (converter == null) {
                    continue;
                }
                    converted = converter.convert(propertyS.get(source));
                propertyD.set(des, converted);
            }
        }
        return des;
    }
}
