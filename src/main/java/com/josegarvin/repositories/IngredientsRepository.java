package com.josegarvin.repositories;


import org.springframework.data.repository.CrudRepository;

import com.josegarvin.models.Ingredient;

public interface IngredientsRepository extends CrudRepository<Ingredient, Long>  {

	/**
	 * Obtenir un ingredient de la BBDD a partir
	 * del nom.
	 * @param nom --> Nom ingredient.
	 * @return --> Objecte ingredient.
	 */
	Ingredient findByNom(String nom);
	
	
}