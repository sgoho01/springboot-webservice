package com.ghsong.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
// JPA Entity 들이 해당 클래스를 상속할 경우 이 클래스의 필드들도 컬럼으로 인식할수 있도록함
@MappedSuperclass
// BaseTimeEntity 클래스에 Auditing 기능을 포함
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    // Entity 생성되어 저장될 때 시간이 자동 저장
    @CreatedDate
    private LocalDateTime createdDate;

    // Entity의 값이 변경될 때 시간이 자동 저장
    @LastModifiedDate
    private LocalDateTime modifiedDate;



}
