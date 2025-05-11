package kr.co.study.service;

import kr.co.study.article.User; // User 엔티티 임포트
import kr.co.study.repository.UserRepository; // UserRepository 임포트
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // 트랜잭션 관리 어노테이션

import java.util.List;
import java.util.Optional;

@Service // 이 클래스가 스프링 서비스 컴포넌트임을 나타냅니다.
@Transactional(readOnly = true) // 기본적으로 읽기 전용 트랜잭션으로 설정 (성능 최적화)
public class UserService {

    private final UserRepository userRepository;

    // 생성자 주입 (Dependency Injection)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. 새로운 사용자 생성 또는 기존 사용자 업데이트
    @Transactional // 쓰기 작업이므로 트랜잭션 활성화
    public User createUser(User user) {
        // 이메일 중복 확인 (필요하다면)
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + user.getEmail());
        }
        return userRepository.save(user);
    }

    // 2. 모든 사용자 조회
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 3. ID로 사용자 조회
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // 4. ID로 사용자 업데이트
    @Transactional
    public Optional<User> updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(existingUser -> {
            // 업데이트할 필드만 설정
            existingUser.setUsername(userDetails.getUsername());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword()); // 실제 앱에서는 비밀번호 암호화 로직 필요

            // 이메일이 변경되었고 중복되는지 확인
            if (!existingUser.getEmail().equals(userDetails.getEmail()) && userRepository.existsByEmail(userDetails.getEmail())) {
                 throw new IllegalArgumentException("이미 사용 중인 이메일입니다: " + userDetails.getEmail());
            }
            return userRepository.save(existingUser);
        });
    }

    // 5. ID로 사용자 삭제
    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("존재하지 않는 사용자 ID입니다: " + id);
        }
        userRepository.deleteById(id);
    }
}