package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
  excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class) // AppConfig 제외 @Configuration내에 Component 어노테이션 존재,
  // 하지만 SpringBootApplication 어노테이션에 이미 ComponentScan 어노테이션이 있어서 AppConfig랑 겹침
)
public class AutoAppConfig {
//  @Bean(name = "memoryMemberRepository")
//  MemberRepository memberRepository() {
//    return new MemoryMemberRepository();
//  }
}