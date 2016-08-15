package wind.beanmapper;

import org.junit.Test;

import java.util.Date;

import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.ReflectInstantiaer;
import wind.beanmapper.mapper.ClassMapper;
import wind.beanmapper.property.CachedPropertyResolver;
import wind.beanmapper.property.ReflectPropertyResolver;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() throws Exception {
        A a = new A();
        a.setName("wind");
        a.setAge(17);
        B b = BeanMapper.map(a, B.class);
        System.out.print(b);
        assertEquals("wind", b.getName());
        assertEquals(17, b.getAge());

    }

    @Test
    public void testCustom() {
        A a = new A();
        ClassMapper<A, B> classMapper = BeanMapper.classMapper(A.class, B.class)
                .config()
                .propertyResolver(new CachedPropertyResolver(new ReflectPropertyResolver()))
                .instantiater(new ReflectInstantiaer())
                .field("name", "Name")
                .field("birthday", "age", new Converter<Date, Integer>() {
                    @Override
                    public Integer convert(Date date) {
                        return new Date().getYear() - date.getYear();
                    }

                    @Override
                    public Date reconvert(Integer integer) {
                        return new Date(new Date().getYear() - integer, 0, 0);
                    }
                })
                .end()
                .get();
        B b = classMapper.map(a, B.class);
    }
}