package janigeek.foroapi.users;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarUsuario(@NotNull Long id, String nombre, String email, String usuario, String password) {

}
