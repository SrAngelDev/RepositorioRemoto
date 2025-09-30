package srangeldev.dao;

import lombok.Builder;
import lombok.Data;
import org.jdbi.v3.core.mapper.reflect.ColumnName;
import srangeldev.model.User;

import java.time.LocalDateTime;

@Data
@Builder
public class UserEntity {
    //Valor por defecto para nuevos usuarios
    @Builder.Default
    private long id = User.NEW_ID;

    private String name;
    private String username;
    private String email;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public UserEntity(
            @ColumnName("id") long id,
            @ColumnName("name") String name,
            @ColumnName("username") String username,
            @ColumnName("email") String email,
            @ColumnName("createdAd") LocalDateTime createdAt,
            @ColumnName("updatedAd") LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
