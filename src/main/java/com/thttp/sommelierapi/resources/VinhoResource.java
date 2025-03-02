package com.thttp.sommelierapi.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.thttp.sommelierapi.model.Vinho;
import com.thttp.sommelierapi.service.VinhoService;

@RestController
@RequestMapping("/vinhos")
public class VinhoResource {

	@Autowired
	private VinhoService vinhoService;

	@GetMapping
	public ResponseEntity<List<Vinho>> listar() {
		return ResponseEntity.ok().body(vinhoService.listar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Vinho> buscarPeloId(@PathVariable Long id) {

		Vinho vinho = vinhoService.buscarPeloId(id);

		return ResponseEntity.ok().body(vinho);
	}

	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Vinho vinho) {

		vinho = vinhoService.salvar(vinho);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vinho.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualiazar(@PathVariable Long id, @RequestBody Vinho vinho) {

		vinho.setId(id);
		vinhoService.atualizar(vinho);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		vinhoService.deletar(id);
		return ResponseEntity.noContent().build();
	}

}
