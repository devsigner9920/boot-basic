package me.devsign.boot.web.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * Jpa에서는 DBLayer 접근자를 Repository라 부르며, 인터페이스를 생성 후 JpaRepository를 상속
 * Entity, Pk Type을 명시하면 기본적인 CRUD 메소드가 자동으로 생성된다.
 * Entity 클래스와 기본 Entity Repository는 함께 위치해야 한다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long> {}
