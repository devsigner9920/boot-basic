package me.devsign.boot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
 * 같은 코드가 반복될 경우 -> IndexController에서 세션값을 가져오는 부분 개선
 * index 메소드 외에 다른 컨트롤러와 메소드에서 세션값이 필요하면 중복 코드가 많아지므로 이 부분을 메소드 인자로
 * 세션값을 바로 받을 수 있도록 변경하기 위한 목적이다.
 */
/*
 * @Target(ElementType.PARAMETER)
 * 어노테이션이 생성될 수 있는 위치를 지정한다.
 * PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {}
