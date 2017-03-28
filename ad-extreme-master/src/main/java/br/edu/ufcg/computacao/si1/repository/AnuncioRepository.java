package br.edu.ufcg.computacao.si1.repository;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {

}
