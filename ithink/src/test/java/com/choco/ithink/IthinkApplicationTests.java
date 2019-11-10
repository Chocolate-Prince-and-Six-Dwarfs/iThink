package com.choco.ithink;

import com.choco.ithink.service.UserServiceTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(Suite.class)
@Suite.SuiteClasses({UserServiceTest.class})
@SpringBootTest
public class IthinkApplicationTests {

    @Test
    public void contextLoads() {
    }

}
