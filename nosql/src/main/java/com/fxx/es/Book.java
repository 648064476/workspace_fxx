package com.fxx.es;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName="book",type="book",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Book {

    @Id
    private String id;

    private String title;

    private String content;

    private int userId;

    private int weight;

}