package com.capgemini.jpa.entities;

import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@SQLDelete(sql = "UPDATE server SET deleted=true WHERE id=?")
@Where(clause = "deleted=false or deleted is null")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Server {

    @Id
    @SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String ip;

    @Transient
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column
    @UpdateTimestamp
    private LocalDateTime lastUpdateDate;

    @Column
    private Boolean deleted = false;

    @Version
    @Column
    private Long version;

    public Server(String name, String ip) {
        super();
        this.name = name;
        this.ip = ip;
    }
}
