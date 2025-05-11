package kr.co.study.article;

import jakarta.persistence.*; // JPA 어노테이션 임포트
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.CreationTimestamp; // 생성 시간 자동 관리를 위한 Hibernate 어노테이션

import java.time.LocalDateTime; // Java 8 날짜/시간 API 사용

@Entity // 이 클래스가 JPA 엔티티임을 나타냅니다. 
@Table(name = "user") // 매핑할 데이터베이스 테이블 이름을 "user"로 지정합니다.
@Data // Lombok: Getter, Setter, toString, equals, hashCode 자동 생성
@NoArgsConstructor // Lombok: 인자 없는 기본 생성자 자동 생성 (JPA 필요)
@AllArgsConstructor // Lombok: 모든 필드를 인자로 받는 생성자 자동 생성 (선택 사항, 필요시 사용)
public class User {

    @Id // 이 필드가 Primary Key임을 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Primary Key 자동 증가 전략 (MySQL AUTO_INCREMENT에 적합)
    private Long id; // INT는 Long으로 매핑하는 것이 일반적입니다.

    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @CreationTimestamp // 엔티티가 저장될 때 현재 시간을 자동으로 삽입합니다.
    @Column(name = "created_at", nullable = false, updatable = false) // created_at 컬럼, null 불가, 업데이트 불가
    private LocalDateTime createdAt;

    // 생성자: ID와 created_at 없이 사용자 생성 시 사용
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        // createdAt은 @CreationTimestamp에 의해 자동으로 설정됩니다.
    }
}