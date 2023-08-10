package br.unb.dominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Professor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;

	@OneToMany(mappedBy = "professor")
	private List<Disciplina> disciplinas;

	public Professor() {
		super();
	}

	public Professor(String nome) {
		super();
		this.nome = nome;
	}

	public Professor(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Professor(int id, String nome, List<Disciplina> disciplinas) {
		super();
		this.id = id;
		this.nome = nome;
		this.disciplinas = disciplinas;
	}

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

	public List<Disciplina> getDisciplinas() {
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}