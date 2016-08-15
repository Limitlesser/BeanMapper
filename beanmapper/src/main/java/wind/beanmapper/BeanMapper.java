package wind.beanmapper;

import java.util.HashMap;
import java.util.Map;

import wind.beanmapper.instantiation.ReflectInstantiaer;
import wind.beanmapper.mapper.ClassMapper;
import wind.beanmapper.mapper.Mapper;
import wind.beanmapper.mapper.MapperContext;
import wind.beanmapper.property.ReflectPropertyResolver;

/**
 * Created by wind on 2016/8/15.
 */
public class BeanMapper {

    private static Mapper mapper;

    static {
        new MapperContext<>(null, new Context.Callback<Mapper>() {
            @Override
            public void end(Mapper mapper) {
                BeanMapper.mapper = mapper;
            }
        }).config()
                .instantiater(new ReflectInstantiaer())
                .propertyResolver(new ReflectPropertyResolver())
                .end()
                .end();
    }

    private static Map<Class<?>, Map<Class<?>, ClassMapper<?, ?>>> classMapperMap = new HashMap<>();


    public static <S, D> D map(S source, Class<D> desClass) {
        ClassMapper classMapper = getClassMapper(source.getClass(), desClass);
        if (classMapper != null) {
            return (D) classMapper.map(source, desClass);
        }
        return (D) mapper.map(source, desClass);
    }

    private static ClassMapper<?, ?> getClassMapper(Class clsA, Class clsB) {
        Map<Class<?>, ClassMapper<?, ?>> map = classMapperMap.get(clsA);
        if (map == null) {
            map = classMapperMap.get(clsB);
            if (map == null) {
                return null;
            }
            return map.get(clsA);
        } else {
            return map.get(clsB);
        }
    }

}
