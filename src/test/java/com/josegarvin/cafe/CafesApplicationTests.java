package com.josegarvin.cafe;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.josegarvin.CafesApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = CafesApplication.class)
@WebAppConfiguration
public class CafesApplicationTests {

	@Test
	public void contextLoads() {
	}

}
