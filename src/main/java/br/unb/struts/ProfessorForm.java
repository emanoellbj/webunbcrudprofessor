package br.unb.struts;

import org.apache.struts.action.ActionForm;

public class ProfessorForm extends ActionForm {
	private int id;
	private String nome;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
