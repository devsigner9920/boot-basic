package me.devsign.boot.web.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    /*
     * 스프링 시큐리티에서는 권한 코드가 ROLE_xxx 로 구성되어야 한다.
     */
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}
