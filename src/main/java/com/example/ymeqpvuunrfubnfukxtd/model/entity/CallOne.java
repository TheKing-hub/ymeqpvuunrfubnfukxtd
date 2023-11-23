package com.example.ymeqpvuunrfubnfukxtd.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// имя, год рождения, номер
//телефона, второй номер телефона, дата создания, id
@Entity
@Table(name = "postgres", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CallOne extends BaseEntity {
    private String name;
    private Integer year;
    private String phoneOne;
    private String phoneTwo;
    private String creationDate;
}
