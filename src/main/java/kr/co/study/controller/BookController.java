package kr.co.study.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.study.article.Book;
import kr.co.study.service.BookService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books") // 이 컨트롤러의 기본 URL 경로를 "books"로 변경
public class BookController {

    private final BookService bookService; // BookService 주입

    public BookController(BookService bookService) {
        this.bookService = bookService;
    } 

    // 새 Book을 생성하는 엔드포인트 (POST 요청)
    // 예시: POST http://localhost:8080/api/books
    // Body (JSON): { "name": "새 책 제목", "author": "새 책 저자" }
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
    	Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); // 201 Created 응답
    }

    // 모든 Book을 조회하는 엔드포인트 (GET 요청)
    // 예시: GET http://localhost:8080/api/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books); // 200 OK 응답
    }

    // 특정 ID의 Book을 조회하는 엔드포인트 (GET 요청)
    // 예시: GET http://localhost:8080/api/books/60c72b2f3e40b1a1a1a1a1a1 (MongoDB ID)
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok) // Book이 있으면 200 OK 반환
                      .orElseGet(() -> ResponseEntity.notFound().build()); // 없으면 404 Not Found 반환
    }

    // 특정 ID의 Book을 삭제하는 엔드포인트 (DELETE 요청)
    // 예시: DELETE http://localhost:8080/api/books/60c72b2f3e40b1a1a1a1a1a1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build(); // 204 No Content 응답 
    }
}