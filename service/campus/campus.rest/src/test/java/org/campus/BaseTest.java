package org.campus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration("classpath:/META-INF/spring/*.xml")
public class BaseTest {
    @Test
    public void test() {

    }
}
