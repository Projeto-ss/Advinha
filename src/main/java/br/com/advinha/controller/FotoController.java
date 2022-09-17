package br.com.advinha.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.advinha.model.Foto;
import br.com.advinha.repository.FotoRepository;


@RestController
@RequestMapping("/fotos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FotoController {

	@Autowired
	private FotoRepository fotoRepository;

	@GetMapping
	public ResponseEntity<List<Foto>> getAll() {
		return ResponseEntity.ok(fotoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Foto> getById(@PathVariable Long id){
		return fotoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Foto>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(fotoRepository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Foto> post(@Valid @RequestBody Foto foto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(fotoRepository.save(foto));
	}

	@PutMapping
	public ResponseEntity<Foto> put(@Valid @RequestBody Foto foto) {
		return fotoRepository.findById(foto.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(fotoRepository.save(foto)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Foto> foto = fotoRepository.findById(id);

		if (foto.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		fotoRepository.deleteById(id);
	}
}
