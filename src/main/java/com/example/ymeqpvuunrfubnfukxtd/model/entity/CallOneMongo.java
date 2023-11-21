package com.example.ymeqpvuunrfubnfukxtd.model.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "call_mongo")
public class CallOneMongo {
    @Id
    private String id;
    private String name;
    private Integer year;
    private String phoneOne;
    private String phoneTwo;
    private String creationDate;
}
