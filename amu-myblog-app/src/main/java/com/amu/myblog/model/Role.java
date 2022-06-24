package com.amu.myblog.model;

import lombok.Data;

import javax.persistence.*;

/**
 * @Created 2022/06/24
 * @Author Amu
 */
@Data
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 60)
    private String name;
}
