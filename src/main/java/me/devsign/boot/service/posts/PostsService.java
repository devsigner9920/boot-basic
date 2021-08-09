package me.devsign.boot.service.posts;

import lombok.RequiredArgsConstructor;
import me.devsign.boot.web.domain.posts.Posts;
import me.devsign.boot.web.domain.posts.PostsRepository;
import me.devsign.boot.web.dto.PostsListResponseDto;
import me.devsign.boot.web.dto.PostsResponseDto;
import me.devsign.boot.web.dto.PostsSaveRequestDto;
import me.devsign.boot.web.dto.PostsUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository
                .save(requestDto.toEntity())
                .getId();
    }

    /*
     * update 부분이지만 데이터베이스에 쿼리를 날리는 부분이 없다.
     * 이것이 가능한 이유는 JPA의 영속성 컨텍스트 때문이다.
     * 영속성 컨텍스트란, 엔티티를 영구저장하는 환경이다.
     * 일종의 논리적 개념이며, JPA의 핵심 내용은 엔티티가 영속성 컨텍스트에 포함되어 있냐 아니냐로 갈린다.
     */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        /*
         * 트랜잭션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
         * 이 상태에서 해당 데이터의 값을 변경하면 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영한다.
         * 즉, Entity 객체의 값만 변경하면 별도로 Update 쿼리를 날릴 필요가 없다.
         * 이 개념을 더티 체킹(dirty checking)이라 한다.
         */
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
