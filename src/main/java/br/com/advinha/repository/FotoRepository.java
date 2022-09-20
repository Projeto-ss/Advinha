package br.com.advinha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.advinha.model.Imagem;

@Repository
public interface FotoRepository extends JpaRepository<Imagem, Long> {

	public List<Imagem> findAllByNomeContainingIgnoreCase(@Param("nome")String nome);
}
