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
    //@Convert(converter = JsonbConverter.class)
    private Map<String, Object> obj;

    @Converter

    static class JsonbConverter implements AttributeConverter<Map<String, Object>, byte[]> {


        private final ObjectMapper objectMapper;

        JsonbConverter(ObjectMapper objectMapper) {
            this.objectMapper = objectMapper;
        }

        @Override
        public byte[] convertToDatabaseColumn(Map<String, Object> attribute) {
            try {
                return objectMapper.writeValueAsBytes(attribute);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Could not convert map to JSON string.", e);
            }
        }

        @Override
        public Map<String, Object> convertToEntityAttribute(byte[] dbData) {
            try {
                return objectMapper.readValue(dbData, Map.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
