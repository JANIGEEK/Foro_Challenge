package janigeek.foroapi.users;

public record DatosListadoUsuarios(Long id, String nombre, String email, String usuario, String password) {
    public DatosListadoUsuarios(Usuario usuario){
        this(usuario.getId(),usuario.getNombre(), usuario.getEmail(), usuario.getUsuario(), usuario.getPassword());
    }
}
