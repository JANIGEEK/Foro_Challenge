package janigeek.foroapi.users;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;



public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
    Page<Usuario> findByActivoTrue(Pageable paginacion);

    UserDetails findByUsuario(String usuario);
    
}
