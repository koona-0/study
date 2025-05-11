package kr.co.study.repository;

import kr.co.study.article.User; // User 엔티티 임포트
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional; // Optional 임포트

@Repository // 이 인터페이스가 Spring Data JPA 리포지토리임을 나타냅니다.
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository는 save(), findById(), findAll(), deleteById() 등 기본 CRUD 메소드를 제공합니다.

    // 사용자 정의 쿼리 메소드 (필요하다면 추가)
    Optional<User> findByUsername(String username); // username으로 사용자 조회
    Optional<User> findByEmail(String email); // email로 사용자 조회 (UNIQUE 속성이 있으므로 Optional)

    boolean existsByEmail(String email); // email 존재 여부 확인
}