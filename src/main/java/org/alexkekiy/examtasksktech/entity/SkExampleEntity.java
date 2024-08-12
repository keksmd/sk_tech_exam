package org.alexkekiy.examtasksktech.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.io.IOException;
import java.util.Map;


@Entity
@Table(name = "sk_example_table")
@Data
@NoArgsConstructor

public class SkExampleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "obj", columnDefinition = "jsonb")

    private Map<String, Object> obj;


}
