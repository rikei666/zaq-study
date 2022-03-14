package com.zaqbest.study;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@ActiveProfiles(profiles = "unittest")
@SpringBootTest(classes = ZaqStudyApplication.class)
@TestPropertySource(locations={"classpath:application.properties"})
public class BaseTest {
}
