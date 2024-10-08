package hello.core.singleton;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

  @Test
  @DisplayName("상태 유지 싱글톤 객체 테스트")
  void statefulServiceSingleton() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    StatefulService statefulService1 = ac.getBean(StatefulService.class);
    StatefulService statefulService2 = ac.getBean(StatefulService.class);

    //ThreadA : A 사용자가 10000원 주문
    int userAPrice = statefulService1.order("userA", 10000);
    //ThreadB : B 사용자가 20000원 주문
    int userBPrice = statefulService2.order("userB", 20000);

    //ThreadA : 사용자A 주문 금액 조회
//    int price = statefulService1.getPrice();
    System.out.println("price = " + userAPrice);

//    Assertions.assertEquals(statefulService1.getPrice(), 20000);
  }

  static class TestConfig {
    @Bean
    public StatefulService statefulService() {
      return new StatefulService();
    }
  }

}