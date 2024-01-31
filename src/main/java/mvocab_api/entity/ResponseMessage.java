package mvocab_api.entity;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class ResponseMessage {
    public static ResponseEntity<Object> responseMessage(String key, Object value) {
            return ResponseEntity.ok(Map.of(key, value));
    }

    public static ResponseEntity<Object> responseMessage(Object value) {
        return ResponseEntity.ok(value);
    }
}
