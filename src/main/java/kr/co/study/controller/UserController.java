package kr.co.study.controller;

import kr.co.study.article.User; // User 엔티티 임포트
import kr.co.study.service.UserService; // UserService 임포트
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 이 클래스가 RESTful 웹 서비스를 제공함을 나타냅니다.
@RequestMapping("/api/users") // 이 컨트롤러의 기본 URL 경로를 "/api/users"로 설정합니다.
public class UserController {

    private final UserService userService;

    // 생성자 주입
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 1. 새 사용자 생성 (POST 요청)
    // 예시: POST http://localhost:8080/api/users
    // Body (JSON): { "username": "testuser", "email": "test@example.com", "password": "password123" }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // 201 Created 응답
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict (이메일 중복 등)
        }
    }

    // 2. 모든 사용자 조회 (GET 요청)
    // 예시: GET http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users); // 200 OK 응답
    }

    // 3. 특정 ID의 사용자 조회 (GET 요청)
    // 예시: GET http://localhost:8080/api/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok) // 사용자가 있으면 200 OK 반환
                .orElseGet(() -> ResponseEntity.notFound().build()); // 없으면 404 Not Found 반환
    }

    // 4. 특정 ID의 사용자 업데이트 (PUT 요청)
    // 예시: PUT http://localhost:8080/api/users/1
    // Body (JSON): { "username": "updateduser", "email": "updated@example.com", "password": "newpassword" }
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            return userService.updateUser(id, userDetails)
                    .map(ResponseEntity::ok) // 업데이트 성공 시 200 OK 반환
                    .orElseGet(() -> ResponseEntity.notFound().build()); // 사용자 ID가 없으면 404 Not Found 반환
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT); // 409 Conflict (이메일 중복 등)
        }
    }

    // 5. 특정 ID의 사용자 삭제 (DELETE 요청)
    // 예시: DELETE http://localhost:8080/api/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.noContent().build(); // 204 No Content 응답
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // 존재하지 않는 사용자 ID일 경우 404 Not Found
        }
    }
}