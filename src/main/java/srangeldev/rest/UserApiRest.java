package srangeldev.rest;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.*;
import srangeldev.config.Config;
import srangeldev.model.User;
import srangeldev.rest.responses.Response;

public interface UserApiRest {
    String API_USER_URL = Config.getProperty("API_USER_URL");

    //Todos los usuarios
    @GET("users")
    Single<Response> getUsers();

    //Usuarios por id
    @GET("users/{id}")
    Single<Response> getUser(@Path("id") String id);

    //Crear un nuevo usuario
    @POST("users")
    Single<Response> createUser(@Body User user);

    //Actualizar usuario por id
    @PUT("users/{id}")
    Single<Response> updateUser(@Path("id") String id, @Body User user);

    //Eliminar usuario por id
    @DELETE("users/{id}")
    Single<Response> deleteUser(@Path("id") String id);

}
