package me.devsign.boot.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.devsign.boot.web.domain.posts.Posts;

/*
 * Entity 클래스와 유사한 형태임에도 Dto 클래스를 추가로 생성함.
 * 절대로 Entiy 클래스를 Request/Response 클래스로 사용해서는 안된다.
 * 이유는 데이터베이스와 맞닿은 핵심 클래스이기 때문이다.
 * Entity 클래스를 기준으로 테이블 생성, 스키마가 변경된다.
 * 많은 서비스 클래스나 비즈니스 로직들이 Entity 클래스를 기준으로 동작.
 * Entity 클래스가 변경되면 여러 클래스에 영향을 끼치지만,
 * Request와 Response용 Dto는 View를 위한 클래스라 자주 변경 필요하다.
 * View Layer와 DB Layer의 역할 분리를 철저하게 하는 것이 좋다.
 * Controller에서 결괏값으로 여러 테이블을 조인해서 줘야할 경우가 빈번하므로
 * Entity 클래스와 Controller에서 쓸 Dto는 분리해서 사용해야 한다.
 */

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content,
                               String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
