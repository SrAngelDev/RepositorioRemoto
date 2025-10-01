package srangeldev.dao;

import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

@RegisterConstructorMapper(UserEntity.class)
public interface UserDao {

    /**
     * Obtener todos los usuarios de la tabla users
     *
     * @return Lista de UserEntity
     */
    @SqlQuery("SELECT * FROM users")
    List<UserEntity> findAll();

    /**
     * Buscar un usuario por id.
     *
     * @param id ID del tenista.
     * @return Optional con la entidad si existe, vacío si no existe.
     */
    @SqlQuery("SELECT * FROM users WHERE id = :id")
    Optional<UserEntity> findById(@Bind("id") long id);

    /**
     * Insertar un nuevo usuario
     *
     * @param user Usuario que se va a insertar
     * @return El numero de filas insertadas
     */
    @SqlUpdate("INSERT INTO users (name, username, email, createdAt, updatedAt) VALUES (:name, :username, :email, :createdAt, :updatedAt)")
    @GetGeneratedKeys
    long save(@BindBean UserEntity user);

    /**
     * Actualizar un usuario existente
     *
     * @param user usuario que se va a actualizar.
     * @return Número de filas actualizadas
     */
    @SqlUpdate("UPDATE users SET name = :name, username = :username, email = :email, createdAt = :createdAt, updatedAt = :updatedAt")
    int update(@BindBean UserEntity user);

    /**
     * Eliminar un usuario por id
     *
     * @param id Id del usuario a eliminar
     * @return Número de filas eliminadas de la bbdd
     */
    @SqlUpdate("DELETE FROM users WHERE id = :id")
    int delete(@Bind("id") long id);
}
