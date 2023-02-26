package com.blogMaven.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false , length = 100 , unique = true)
    private String username;

    @Column(nullable = false , length = 100)
    private String password;

    @Column(nullable = false , length = 50)
    private String email;

    // @ColumnDefault("'user'") - default - enum 으로 값을 강제
    // db는 roletype 이라는 type 이 없으니 , string 타입이라고 알려주는 어노테이션
    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp
    private Timestamp createDate;
}
