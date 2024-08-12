package org.alexkekiy.examtasksktech;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/modify")
public class SkExampleController {

    private final SkExampleService service;

    public SkExampleController(SkExampleService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> modify(@RequestBody ModifyRequest request) {
        try {
            int current = service.modifyCurrent(request.getId(), request.getAdd());
            return ResponseEntity.ok(new ModifyResponse(current));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(e.getMessage());
        }
    }

    @Data
    static class ModifyRequest {
        private Long id;
        private int add;
    }

    @Getter
    static class ModifyResponse {
        private final int current;

        public ModifyResponse(int current) {
            this.current = current;
        }
    }
}
