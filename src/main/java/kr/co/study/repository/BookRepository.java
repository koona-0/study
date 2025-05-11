package kr.co.study.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import kr.co.study.article.Book;

// MongoRepository를 상속받고 Book 도큐먼트와 String 타입의 ID를 지정합니다.
@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // findAll(), findById(), save(), delete() 등 기본 메소드는 자동으로 제공됩니다.

    // 필요하다면 여기에 사용자 정의 쿼리 메소드를 추가할 수 있습니다.
    // 예시: List<Book> findByNameContaining(String name);
    // 예시: List<Book> findByAuthor(String author);
}
