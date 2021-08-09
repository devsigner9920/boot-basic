package me.devsign.boot.web;

import lombok.RequiredArgsConstructor;
import me.devsign.boot.service.posts.PostsService;
import me.devsign.boot.web.dto.PostsResponseDto;
import me.devsign.boot.web.dto.PostsSaveRequestDto;
import me.devsign.boot.web.dto.PostsUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

/*
 * @RequiredArgsConstructor
 * 현재 Controller에서는 PostsApiController에 대한 생성자를 생성하여 PostsService에 의존성을 주입할 수 있도록 해준다.
 * private final PostsService postsService; -> 해당 필드는 final 하기 때문에 해당 필드에 대해서 생성자를 통해 의존성을 주입한다.
 * public PostsApiController(PostsService postsService) {
 *      this.postsService = postsService;
 * }
 */
@RequiredArgsConstructor
@RestController
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }

    /*
     * 게시글 생성
     * 신규 insert일 경우
     */
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);

        return id;
    }
}
