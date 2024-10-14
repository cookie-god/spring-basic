package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
  @Test
  void AutowiredOption() {
    ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
  }

  static class TestBean {
    @Autowired(required = false) // 빈 찾지 못하는 경우
    public void setNoBean1(Member noBean1) {
      System.out.println("noBean1 = " + noBean1); // 호출 자체가 되지 않음, 의존관계가 없다면 메서드 호출이 되지 않음
    }

    @Autowired
    public void setNoBean2(@Nullable Member noBean2) {
      System.out.println("noBean2 = " + noBean2); // 호출은 되는데, null값 등록
    }

    @Autowired
    public void setNoBean3(Optional<Member> noBean3) {
      System.out.println("noBean3 = " + noBean3); // 호출은 되는데, Optional.empty로 값이 매겨짐
    }
  }
}
