package br.com.advinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.advinha.model.Foto;

@Repository
public interface FotoRepository extends JpaRepository<Foto, Long> {

	public List<Foto> findAllByNomeContainignIgnoreCase(@Param("nome")String nome);
}
