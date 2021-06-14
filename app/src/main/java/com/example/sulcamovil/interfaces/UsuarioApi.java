package com.example.sulcamovil.interfaces;

import com.example.sulcamovil.models.Usuario;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UsuarioApi {
    @GET("api/Usuarios/{idUsuario}/{clave}")
    public Call<Usuario> find(@Path("idUsuario") String idUsuario,@Path("clave") String clave);
}
