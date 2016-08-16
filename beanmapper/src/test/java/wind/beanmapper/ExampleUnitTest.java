package wind.beanmapper;

import org.junit.Test;

import wind.beanmapper.converter.Converter;
import wind.beanmapper.instantiation.ReflectInstantiate;
import wind.beanmapper.mapper.ClassMapper;
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
        A a = new A("wind", 17);
        B b = BeanMapper.map(a, B.class);
        System.out.print(b);
        assertEquals("wind", b.getName());
        assertEquals(17, b.getAge());

    }

    @Test
    public void testCustom() {
        A a = new A("wind", 17);
        ClassMapper<A, B> classMapper = BeanMapper.classMapper(A.class, B.class)
                .config()
                .propertyResolver(new ReflectPropertyResolver())
                .instantiater(new ReflectInstantiate())
                .field("name", "Name", new Converter<String, String>() {
                    @Override
                    public String convert(String s) {
                        return s.toUpperCase();
                    }

                    @Override
                    public String reconvert(String s) {
                        return s.toLowerCase();
                    }
                })
                .end()
                .get();
        B b = classMapper.map(a, B.class);
        System.out.print(b);
        a = classMapper.map(b, A.class);
        System.out.print(a);
    }
}