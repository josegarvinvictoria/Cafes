package com.josegarvin.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.josegarvin.models.Cafe;
import com.josegarvin.models.Ingredient;
import com.josegarvin.repositories.CafesRepository;
import com.josegarvin.repositories.IngredientsRepository;

@Controller
public class HomeController {

	IngredientsRepository ingredientsRepository;
	CafesRepository cafeRepository;
	List<Ingredient> llistaObjIng;
	EntityManager entityManager;

	@Autowired
	public HomeController(IngredientsRepository ingredientsRepository,
			CafesRepository cafeRepository) {
		this.ingredientsRepository = ingredientsRepository;
		this.cafeRepository = cafeRepository;
	}

	/**
	 * Mètode per capturar l'inici de la pàgina.
	 * 
	 * @return --> Retorna la vista d'inici.
	 */
	@RequestMapping(value = { "", "/", "inici", "index" })
	public String home() {
		return "index";
	}

	/**
	 * Mètode que captura les peticions GET que arriben per novaComanda.
	 * 
	 * @param model
	 *            --> Model.
	 * @return --> Retorna una vista.
	 */
	@RequestMapping(value = "/novaComanda", method = RequestMethod.GET)
	public String novaComanda(Model model) {
		// List ingredients = (List) ingredientsRepository.findAll();
		List<Ingredient> ingredients = (List<Ingredient>) ingredientsRepository
				.findAll();
		model.addAttribute("ingredients", ingredients);
		return "novaComanda";
	}

	/**
	 * Mètode per capturar les peticions POST que arriben per novaComanda.
	 * 
	 * @param ingredients
	 *            --> llistat d'ingredients.
	 * @param model
	 *            --> Model.
	 * 
	 * @return --> Retorna una vista
	 */
	@RequestMapping(value = "/novaComanda", method = RequestMethod.POST)
	public String novaComanda(
			@RequestParam("ingredients") List<String> ingredients, Model model) {
		double preuTotal = 0;
		int preuCafe = 3;

		String nomCafe = "";
		llistaObjIng = new ArrayList<Ingredient>();

		for (int i = 0; i < ingredients.size(); i++) {
			Ingredient ingredientObject = ingredientsRepository
					.findByNom(ingredients.get(i));

			if (ingredientObject != null) {
				nomCafe += ingredientObject.getNom();
				preuTotal += ingredientObject.getPreu();
				llistaObjIng.add(ingredientObject);
			}
		}

		Cafe nouCafe = new Cafe(llistaObjIng);

		Cafe cafeTrobat = cafeRepository.findByNom(nomCafe);

		if (cafeTrobat != null) {
			cafeTrobat.setNumVendes(cafeTrobat.getNumVendes() + 1);
			cafeRepository.save(cafeTrobat);
		} else {
			nouCafe.setNumVendes(1);
			nouCafe.setNom(nomCafe);
			cafeRepository.save(nouCafe);
		}

		preuTotal += preuCafe;

		model.addAttribute("cafe", nouCafe);
		model.addAttribute("total", preuTotal);

		return "resumComanda";
	}

	/**
	 * Mètode per capturar les peticions GET que arriben des de "zonaPrivada" o
	 * "zonaPrivada.html".
	 * 
	 * @param model
	 *            --> Model.
	 * @return --> Retorna la vista zonaPrivada.
	 */
	@RequestMapping(value = { "zonaPrivada", "zonaPrivada.html" }, method = RequestMethod.GET)
	public String zonaPrivada(Model model) {
		Ingredient ingredient = new Ingredient();
		model.addAttribute("ingredient", ingredient);

		int maxVendes = cafeRepository.findTopVentes();
		List<Cafe> topVentes = cafeRepository.findByNvendes(maxVendes);
		model.addAttribute("topVentes", topVentes);
		return "zonaPrivada";
	}

	/**
	 * Mètode per capturar les peticions POST que arriben des de "zonaPrivada" o
	 * "zonaPrivada.html".
	 * 
	 * @param ingredient
	 *            --> Objecte "Ingredient" rebut.
	 * @param model
	 *            --> Model.
	 * 
	 * @return --> Retorna la vista zonaPrivada en el cas de que l'ingredient ja
	 *         existeixi. Si no redirecciona a la vista novaComanda.
	 */
	@RequestMapping(value = { "zonaPrivada", "zonaPrivada.html" }, method = RequestMethod.POST)
	public String zonaPrivada(Ingredient ingredient, Model model) {

		if (ingredientsRepository.findByNom(ingredient.getNom()) != null) {
			System.out.println("Ingredient repetit!");
			return "zonaPrivada";
		}

		ingredientsRepository.save(ingredient);
		return "redirect:/novaComanda";
	}
}
