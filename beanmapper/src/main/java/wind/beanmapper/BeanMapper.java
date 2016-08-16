package wind.beanmapper;

import wind.beanmapper.context.ClassMapperContext;
import wind.beanmapper.context.Context;
import wind.beanmapper.context.MapperContext;
import wind.beanmapper.instantiation.ReflectInstantiate;
import wind.beanmapper.mapper.ClassMapper;
import wind.beanmapper.mapper.Mapper;
import wind.beanmapper.property.ReflectPropertyResolver;
import wind.beanmapper.utils.BothDoubleKeyMap;

/**
 * Created by wind on 2016/8/15.
 */
public class BeanMapper {

    private static Mapper mapper;

    static {
        mapper = new MapperContext<Context, Mapper>(null)
                .config()
                .instantiater(new ReflectInstantiate())
                .propertyResolver(new ReflectPropertyResolver())
                .end()
                .get();
    }

    private static BothDoubleKeyMap<Class, ClassMapper> classMapperMap = new BothDoubleKeyMap<>();


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

    public static void addClassMapper(ClassMapper classMapper) {
        classMapperMap.put(classMapper.sClass(), classMapper.dClass(), classMapper);
    }

    private static ClassMapper<?, ?> getClassMapper(Class clsA, Class clsB) {
        return classMapperMap.get(clsA, clsB);
    }

}
