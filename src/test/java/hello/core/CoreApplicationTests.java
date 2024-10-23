package hello.core;

import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class CoreApplicationTests {

	@Autowired
	ApplicationContext ac;
	@Test
	void contextLoads() {
		String[] beans = ac.getBeanDefinitionNames();
		for (String bean : beans) {
			System.out.println("bean = " + bean);
		}
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean);
	}

}
