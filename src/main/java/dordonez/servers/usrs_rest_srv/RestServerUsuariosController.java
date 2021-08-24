package dordonez.servers.usrs_rest_srv;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestServerUsuariosController {

	@Autowired
	UsuarioRepository repository;
	
	@GetMapping("/usuarios")
	public List<Usuario> getAllUsuarios() {
		return repository.findAll();
	}
	
	@GetMapping("/usuarios/{id}")
	public Usuario getUsuario(@PathVariable("id") long id) {
		return repository.findById(id).orElse(null);
	}
	
	@PostMapping("/usuarios/create")
	public ResponseEntity<?> postUsuario(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		if(!repository.existsById(usuario.getId())) {
			repository.save(usuario);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PutMapping("/usuarios/update")
	public ResponseEntity<?> putUsuario(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		repository.save(usuario);
		return ResponseEntity.ok().build();
	}	
	
	@DeleteMapping("/usuarios/delete")
	public ResponseEntity<?> deleteDato(@RequestBody Usuario usuario) {
		System.out.println(usuario);
		repository.delete(usuario);
		return ResponseEntity.ok().build();
	}
	
}
