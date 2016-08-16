package wind.beanmapper;

import java.util.HashMap;
import java.util.Map;

import wind.beanmapper.instantiation.ReflectInstantiate;
import wind.beanmapper.mapper.ClassMapper;
import wind.beanmapper.mapper.ClassMapperContext;
import wind.beanmapper.mapper.Mapper;
import wind.beanmapper.mapper.MapperContext;
import wind.beanmapper.property.ReflectPropertyResolver;

/**
 * Created by wind on 2016/8/15.
 */
public class BeanMapper {

    private static Mapper mapper;

    static {
        mapper = new MapperContext<Context, Mapper>(null).config()
                .instantiater(new ReflectInstantiate())
                .propertyResolver(new ReflectPropertyResolver())
                .end()
                .get();
    }

    private static Map<Class<?>, Map<Class<?>, ClassMapper<?, ?>>> classMapperMap = new HashMap<>();


    public static <S, D> D map(S source, Class<D> desClass) {
        ClassMapper classMapper = getClassMapper(source.getClass(), desClass);
        if (classMapper != null) {
            return classMapper.map(source, desClass);
        }
        return mapper.map(source, desClass);
    }

    public static <A, B> ClassMapperContext<?, A, B> classMapper(Class<A> clsA, Class<B> clsB) {
        return new ClassMapperContext<Object, A, B>(null, null).classMapper(clsA, clsB);
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
