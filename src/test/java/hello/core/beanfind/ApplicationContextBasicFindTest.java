package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

  AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

  @Test
  @DisplayName("빈 이름으로 조회")
  void findBeanByName() {
    MemberService memberService = ac.getBean("memberService", MemberService.class);
    assertTrue(memberService instanceof MemberServiceImpl);
  }

  @Test
  @DisplayName("이름 없이 타입으로만 조회")
  void findBeanByType() {
    MemberService memberService = ac.getBean(MemberService.class);
    assertTrue(memberService instanceof MemberServiceImpl);
  }

  @Test
  @DisplayName("구체 타입으로 조회")
  void findBeanByName2() {
    MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); // 구현체를 가져와도 됨. 그런데 좋지 않음. 역할과 구현을 구분하고, 역할에 의존해야함.
    assertTrue(memberService instanceof MemberServiceImpl);
  }

  @Test
  @DisplayName("빈 이름으로 조회X")
  void findBeanByNameX() {
//    MemberService xxxxx = ac.getBean("xxxxx", MemberService.class); // NoSuchBeanDefinitionException
    assertThrows(NoSuchBeanDefinitionException.class,
      () -> ac.getBean("xxxxx", MemberService.class)); // 터지면 성공
  }
}
