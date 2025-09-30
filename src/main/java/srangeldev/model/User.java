package srangeldev.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Builder
public class User {
    public static Long NEW_ID = 0L;
    public String name;
    public String username;
    public String email;
    public LocalDateTime createdAt;
    public LocalDateTime updatedAt;
}
