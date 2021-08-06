package me.devsign.boot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void testLombok() {
        // given
        String name = "test_user";
        int amount = 100000;

        // when
        HelloResponseDto helloResDto = new HelloResponseDto(name, amount);

        /*
         * assertThat
         * assertj라는 테스트 검증 라이브러리의 검증 메소드
         * 검증하고 싶은 대상을 메소드 인자로 받습니다.
         * 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용할 수 있습니다.
         *
         * isEqualTo
         * assertj의 동등 비교 메소드입니다.
         * assertThat에 있는 값과 isEqualTo의 같을 비교해서 같을 때만 성공
         */
        // then
        assertThat(helloResDto.getName()).isEqualTo(name);
        assertThat(helloResDto.getAmount()).isEqualTo(amount);

        /*
         * Junit 기본 assertThat이 아닌 assertj assertThat 사용
         * CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않다.
         * Junit의 assertThat을 쓰게 되면 is와 같이 CoreMatchers 라이브러리가 필요
         *
        */
    }
}