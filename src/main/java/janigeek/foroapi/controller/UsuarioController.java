package janigeek.foroapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.validation.Valid;

import jakarta.transaction.Transactional;
import janigeek.foroapi.users.DatosActualizarUsuario;
import janigeek.foroapi.users.DatosListadoUsuarios;
import janigeek.foroapi.users.DatosRegistroUsuarios;
import janigeek.foroapi.users.DatosRespuestaUsuario;
import janigeek.foroapi.users.Usuario;
import janigeek.foroapi.users.UsuarioRepository;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping()
    public ResponseEntity<DatosRespuestaUsuario> postRegistrar(@RequestBody @Valid DatosRegistroUsuarios datosRegistroUsuarios, UriComponentsBuilder uriComponentsBuilder){
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistroUsuarios));
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getUsuario(), usuario.getPassword());
        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoUsuarios>> listadoUsuarios(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(usuarioRepository.findByActivoTrue(paginacion).map(DatosListadoUsuarios::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity putActualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario) {
        Usuario usuario = usuarioRepository.getReferenceById(datosActualizarUsuario.id());
        usuario.actualizarDatos(datosActualizarUsuario);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getUsuario(), usuario.getPassword());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuario.desactivarUsuario();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaUsuario> getUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        DatosRespuestaUsuario datosRespuestaUsuario = new DatosRespuestaUsuario(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getUsuario(), usuario.getPassword());
        return ResponseEntity.ok(datosRespuestaUsuario);
    }
    
    
}
