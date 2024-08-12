package org.alexkekiy.examtasksktech;

import org.alexkekiy.examtasksktech.entity.SkExampleEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SkExampleServiceTest {

    @Mock
    private SkExampleRepository repository;

    @InjectMocks
    private SkExampleService service;

    @Test
    public void testModifyCurrent_Success() throws Exception {
        SkExampleEntity entity = new SkExampleEntity();
        Map<String, Object> obj = new HashMap<>();
        obj.put("current", 10);
        entity.setObj(obj);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        int result = service.modifyCurrent(1L, 5);

        assertEquals(15, result);
        assertEquals(15, entity.getObj().get("current"));
        Mockito.verify(repository).save(entity);
    }

    @Test
    public void testModifyCurrent_ThrowsExceptionWhenNoCurrentField() {
        SkExampleEntity entity = new SkExampleEntity();
        Map<String, Object> obj = new HashMap<>();
        entity.setObj(obj);

        when(repository.findById(1L)).thenReturn(Optional.of(entity));

        Exception exception = assertThrows(Exception.class, () -> {
            service.modifyCurrent(1L, 5);
        });

        assertEquals("Json-entity is not valid", exception.getMessage());
    }

    @Test
    public void testModifyCurrent_ThrowsExceptionWhenEntityNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(Exception.class, () -> {
            service.modifyCurrent(1L, 5);
        });
    }
}
