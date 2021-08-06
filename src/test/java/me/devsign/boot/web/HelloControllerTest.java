package me.devsign.boot.web;

import com.jayway.jsonpath.JsonPath;
import me.devsign.boot.web.dto.HelloResponseDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

/*
 * RunWith(SpringRunner.class)
 * 테스트를 진행할 때 Junit에 내장된 실행자 외에 다른 실행자를 실행시킨다.
 * SpringRunner라 스프링 실행자를 사용
 * 즉, 스프링 부트 테스트와 Junit 사이에 연결자 역할
 */
@RunWith(SpringRunner.class)
/*
 * 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
 * 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있다.
 * 단, @Service, @Component, @Repository 등은 사용할 수 없다.
 * 여기서는 컨트롤러만 사용하기 때문에 선언한다.
 */
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {
    /*
     * Autowired
     * 스프링이 관리하는 빈(Bean)을 주입 받는다.
     */
    @Autowired
    /*
     * 웹 API 테스트 시 사용
     * 스프링 MVC 테스트의 시작점
     * 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 가능
     */
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void hello() throws Exception {
        String hello = "hello";

        /*
         * MockMvc를 통해 /hello 주소로 HTTP GET 요청을 한다.
         * 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언할 수 있다.
         */
        mvc.perform(get("/hello"))
                /*
                 * mvc.perform의 결과를 검증
                 * HTTP Header의 Status를 검증
                 * 200, 404, 500 등의 상태를 검증
                 * 여기선 OK 즉, 200인지 아닌지를 검증
                 */
                .andExpect(status().isOk())
                /*
                 * mvc.perform의 결과를 검증
                 * 응답 본문의 내용 검증
                 * Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
                 */
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto() throws Exception {
        String name = "test";
        int amount = 9090;
        HelloResponseDto helloResponseDto = new HelloResponseDto(name, amount);

        mvc.perform(get("/hello/dto")
                        /*
                         * API 테스트 시, 사용될 요청 파라미터를 설정
                         * 값은 String만 허용
                         * 숫자/날짜 데이터는 등록할 때 문자열로 변경
                         */
                        .param("name", name)
                        .param("amount", String.valueOf(amount))
                )
                .andExpect(status().isOk())
                /*
                 * JSON 응답값을 필드별로 검증할 수 있는 메소드
                 * $를 기준으로 필드명을 명시
                 */
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}