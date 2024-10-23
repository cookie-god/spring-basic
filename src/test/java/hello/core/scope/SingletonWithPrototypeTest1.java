package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import static org.junit.jupiter.api.Assertions.*;

public class SingletonWithPrototypeTest1 {

  @Test
  void prototypeFind() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
    PrototypeBean bean1 = ac.getBean(PrototypeBean.class);
    bean1.addCount();
    assertEquals(bean1.getCount(), 1);

    PrototypeBean bean2 = ac.getBean(PrototypeBean.class);
    bean2.addCount();
    assertEquals(bean2.getCount(), 1);

  }

  @Test
  void singletonClientUsePrototype() {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

    ClientBean clientBean1 = ac.getBean(ClientBean.class);
    int count1 = clientBean1.logic();
    assertEquals(count1, 1);

    ClientBean clientBean2 = ac.getBean(ClientBean.class);
    int count2 = clientBean2.logic();
    assertEquals(count2, 1);

  }

  @Scope("singleton")
  static class ClientBean {
    @Autowired
    private ObjectProvider<PrototypeBean> prototypeBeanProvider; // Dependency Lookup

    public int logic() {
      PrototypeBean prototypeBean = prototypeBeanProvider.getObject(); // getObject()를 하는 순간 조회하기 때문에 생성됨
      prototypeBean.addCount();
      return prototypeBean.getCount();
    }
  }

  @Scope("prototype")
  static class PrototypeBean {
    private int count = 0;

    public void addCount() {
      count++;
    }

    public int getCount() {
      return count;
    }

    @PostConstruct
    public void init() {
      System.out.println("PrototypeBean.init " + this);
    }

    @PreDestroy
    public void destroy() {
      System.out.println("PrototypeBean.destroy");
    }
  }
}
