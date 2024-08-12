package org.alexkekiy.examtasksktech;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.alexkekiy.examtasksktech.entity.SkExampleEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class SkExampleService {

    private final SkExampleRepository repository;
    private final ObjectMapper objectMapper;

    public SkExampleService(SkExampleRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
        ;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    public int modifyCurrent(Long id, int add) throws Exception {
        SkExampleEntity entity = repository.findById(id).orElseThrow();
        Map<String, Object> values = entity.getObj();
        if (values.containsKey("current")) {
            int current = (int) values.get("current");
            current += add;
            values.replace("current",current);
            entity.setObj(values);
            repository.save(entity);
            return current;
        } else {
            throw new Exception("Json-entity is not valid");
        }

    }
}
