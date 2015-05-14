package com.josegarvin.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.josegarvin.models.Cafe;

public interface CafesRepository extends CrudRepository<Cafe, Long> {

	List<Cafe> findAll();

	Cafe findByNom(String nom);

	@Query("select max(c.nvendes) from Cafe c")
	int findTopVentes();

	List<Cafe> findByNvendes(int nvendes);

	// @Query("update cafes c set c.num_vendes = c.num_vendes + 1 where c.id = %?1")
	// boolean incrementarVenda(Long id);

}