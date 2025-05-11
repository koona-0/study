package kr.co.study.article;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data; // Getter, Setter, ToString, EqualsAndHashCode, RequiredArgsConstructor를 자동으로 생성합니다.
import lombok.NoArgsConstructor; // 인자 없는 기본 생성자를 생성합니다. (Spring Data 필요)
// import lombok.AllArgsConstructor; // 모든 필드를 인자로 받는 생성자를 생성합니다. (ID가 자동 생성되는 경우 주의)


@Data // 이 어노테이션 하나로 게터, 세터, toString(), equals(), hashCode() 메소드가 자동으로 생성됩니다.
@NoArgsConstructor // Spring Data MongoDB가 도큐먼트를 인스턴스화 할 때 필요한 기본 생성자입니다.
// @AllArgsConstructor // 만약 모든 필드(id 포함)를 인자로 받는 생성자가 필요하다면 이 주석을 해제하세요.
                      // 하지만 MongoDB는 보통 _id를 자동으로 생성하므로, 명시적으로 ID를 인자로 받지 않는 생성자가 더 흔합니다.
@Document(collection = "books")
public class Book {

    @Id
    private String id;
    private String name;
    private String author;

    // Lombok을 사용하더라도 특정 필드만으로 이루어진 생성자가 필요하다면 직접 정의할 수 있습니다.
    // 이 생성자는 새로운 책을 생성할 때 ID 없이 이름과 저자만으로 객체를 만들 때 유용합니다.
    public Book(String name, String author) {
        this.name = name;
        this.author = author;
    }

}
