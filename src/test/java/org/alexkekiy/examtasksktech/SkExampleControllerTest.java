package org.alexkekiy.examtasksktech;

import org.alexkekiy.examtasksktech.SkExampleController.ModifyRequest;
import org.alexkekiy.examtasksktech.SkExampleController.ModifyResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SkExampleControllerTest {

    @Mock
    private SkExampleService service;

    @InjectMocks
    private SkExampleController controller;

    @Test
    public void testModify_Success() throws Exception {
        ModifyRequest request = new ModifyRequest();
        request.setId(1L);
        request.setAdd(5);

        when(service.modifyCurrent(1L, 5)).thenReturn(15);

        ResponseEntity<Object> response = controller.modify(request);

        assertEquals(200, response.getStatusCodeValue());
        assertTrue(response.getBody() instanceof ModifyResponse);
        assertEquals(15, ((ModifyResponse) response.getBody()).getCurrent());
    }

    @Test
    public void testModify_Exception() throws Exception {
        ModifyRequest request = new ModifyRequest();
        request.setId(1L);
        request.setAdd(5);

        when(service.modifyCurrent(1L, 5)).thenThrow(new Exception("Json-entity is not valid"));

        ResponseEntity<Object> response = controller.modify(request);

        assertEquals(418, response.getStatusCodeValue());
        assertEquals("Json-entity is not valid", response.getBody());
    }
}
