package kr.co.study.service;

import org.springframework.stereotype.Service;

import kr.co.study.article.Book;
import kr.co.study.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository; // BookRepository 주입

    // 생성자 주입 (Dependency Injection)
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 새 Book 저장 또는 기존 Book 업데이트
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // 모든 Book 조회
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // ID로 Book 조회
    public Optional<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }

    // ID로 Book 삭제
    public void deleteBook(String id) {
        bookRepository.deleteById(id);
    }
}