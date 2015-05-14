package com.josegarvin.models;

import javax.persistence.Entity;
import java.io.Serializable;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.lang.Override;



@Table(name = "Ingredients")
@Entity
public class Ingredient implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	

	@Column(name = "nom")
	private String nom;

	@Column(name = "preu")
	private double preu;
	
	
	@Column(name ="cafe")
	private Cafe cafe;
	
	public Ingredient(){}

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}
	
	

//	public int getVersion() {
//		return this.version;
//	}
//
//	public void setVersion(final int version) {
//		this.version = version;
//	}

	public double getPreu() {
		return preu;
	}

	public void setPreu(double preu) {
		this.preu = preu;
	}


	public Cafe getCafe() {
		return cafe;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Ingredient)) {
			return false;
		}
		Ingredient other = (Ingredient) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}



	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nom != null && !nom.trim().isEmpty())
			result += "nom: " + nom;
		
		return result;
	}
}