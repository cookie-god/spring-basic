package hello.core.singleton;

public class SingletonService {
  // static으로 만들어서 클래스 영역에 딱 하나만 저장됨
  private static final SingletonService instance = new SingletonService();

  // 인스턴스 조회
  public static SingletonService getInstance() {
    return instance;
  }

  // private으로 외부에서 인스턴스 만들지 못하도록 막음
  private SingletonService() {

  }

  public void logic() {
    System.out.println("싱글톤 객체 로직 호출");
  }
}
