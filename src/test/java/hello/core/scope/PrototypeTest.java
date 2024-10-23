package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

  @Test
  void prototypeBeanFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
    System.out.println("find prototypeBean1");
    ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
    System.out.println("find prototypeBean2");
    ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);

    System.out.println("bean1 = " + bean1);
    System.out.println("bean2 = " + bean2);

    Assertions.assertNotSame(bean1, bean2);

    ac.close();

    // 빈 조회시 생성, 초기화 메서드도 실행됨
    // 생성하고 관리를 하지 않아, 2개의 빈이 다른 주소를 가짐
    // 의존성과 초기화 까지만 담당
  }

  @Scope("prototype")
  static class ProtoTypeBean {
    @PostConstruct
    public void init() {
      System.out.println("ProtoTypeBean.init");
    }

    @PreDestroy
    public void destroy() {
      System.out.println("ProtoTypeBean.destroy");
    }
  }
}
