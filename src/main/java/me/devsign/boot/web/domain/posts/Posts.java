package me.devsign.boot.web.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devsign.boot.web.domain.BaseTimeEntity;

import javax.persistence.*;

/*
 * Setter가 없는 상황에서는 기본적으로 생성자를 통해 최종값을 채운 후 DB에 삽입한다.
 * 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경
 * 생성자 대신 builder를 통해 값을 채워줄 수 있다.
 */

/*
 * lombok은 필수 어노테이션이 아니므로 윗부분에 선언, 추후 코틀린 등 새언어로 전환 시, 걷어내기 유리함
 */
@Getter
@NoArgsConstructor
/*
 * @Entity
 * 테이블과 링크될 클래스임을 나타낸다.
 * 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름에 매칭한다.
 */
@Entity
public class Posts extends BaseTimeEntity {
    /*
     * @Id
     * 해당 테이블의 PK 필드를 나타낸다.
     */
    @Id
    /*
     * @GeneratedValue
     * PK의 생성 규칙을 나타낸다.
     * 스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만
     * auto_increment 된다.
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * @Column
     * 테이블 컬럼을 나타낸다.
     * 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
     * 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
     */
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
