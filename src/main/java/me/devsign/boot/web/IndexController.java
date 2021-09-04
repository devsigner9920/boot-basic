package me.devsign.boot.web;

import lombok.RequiredArgsConstructor;
import me.devsign.boot.config.auth.LoginUser;
import me.devsign.boot.config.auth.dto.SessionUser;
import me.devsign.boot.service.posts.PostsService;
import me.devsign.boot.web.dto.PostsResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        /*
         * (SessionUser) httpSession.getAttribute("user")
         * 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성했다.
         * 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져온다.
         *
         * SessionUser user = (SessionUser) httpSession.getAttribute("user");
         * 반복된 코드를 피하기 위해 LoginUser Annotation을 구현하여 위 코드를 대체한다.
         */
        /*
         * if(user != null)
         * 세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
         * 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
         */
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
