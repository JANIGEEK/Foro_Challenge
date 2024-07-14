package janigeek.foroapi.users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record DatosRegistroUsuarios(
    @NotBlank
    String nombre,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String usuario,
    @NotEmpty
    String password
) {

}
