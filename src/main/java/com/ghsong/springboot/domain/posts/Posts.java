package com.ghsong.springboot.domain.posts;

import com.ghsong.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
// 기본 생성자 자동 추가 (롬복)
@NoArgsConstructor
// 테이블과 링크 될 클래스, 클래스의 카멜케이스 이름으로 테이블 생성
@Entity
public class Posts extends BaseTimeEntity {

    // 테이블의 PK 필드
    @Id
    // PK 생성규칙. 스프링부트 2.0 에서는 GenerationType.IDENTITY 옵션 추가해야만 auto_increment가 됨
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 테이블 컬럼. 굳이 선언 안해도 필드는 전부 컬럼.
    // 기본값 외에 변경하려면 선언후 수정 (사이즈, 타입..)
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
