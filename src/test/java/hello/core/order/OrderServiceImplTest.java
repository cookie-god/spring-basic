package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

  @Test
  void createOrder() {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();
    memoryMemberRepository.save(new Member(1L, "name", Grade.VIP));

    OrderServiceImpl orderService = new OrderServiceImpl(memoryMemberRepository, new FixDiscountPolicy()); // 목으로 가짜 객체를 만들어야함
    Order order = orderService.createOrder(1L, "itemA", 10000);
    Assertions.assertEquals(order.getDiscountPrice(), 1000);
  }

}