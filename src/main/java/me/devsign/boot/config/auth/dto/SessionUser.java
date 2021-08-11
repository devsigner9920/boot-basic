package me.devsign.boot.config.auth.dto;

import lombok.Getter;
import me.devsign.boot.web.domain.user.User;

import java.io.Serializable;

/*
 * SessionUser에는 인증된 사용자 정보만 필요하다.
 * 그 외에 필요한 정보들은 없으니 name, email, picture만 필드로 선언한다.
 *
 * Serializable 인터페이스
 * 해당 클래스를 직렬화시켜준다.
 * 직렬화란, 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수
 * 있도록 바이트(byte) 형태로 데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는
 * 기술(역질렬화)를 아울러 얘기한다.
 * JVM의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술과
 * 직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태를 같이 얘기한다.
 *
 * User 엔티티를 Serializable 하게 만들지 않는 이유는 User 클래스가 엔티티기 때문이다.
 * 엔티티 클래스에는 언제 다른 엔티티와 관계가 형성될지 모른다.
 * 예를 들어 @OneToMany, @ManyToMany 등 자식 엔티티를 갖고 있다면 직렬화 대상에
 * 자식들까지 포함되니 성능 이슈, 부수 효과가 발생할 확률이 높다.
 * 그러므로 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때
 * 많은 도움이 된다.
 */
@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
