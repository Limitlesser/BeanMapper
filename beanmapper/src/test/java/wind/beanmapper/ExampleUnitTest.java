package wind.beanmapper;

import org.junit.Test;

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
}