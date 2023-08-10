package br.unb.dominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "idade")
    private int idade;
    @Column(name = "sex",length = 1, nullable = false )
    private String sexo;
    
//    @ManyToMany
//    @JoinTable(
//        name = "pessoa_projeto",
//        joinColumns = @JoinColumn(name = "pessoa_id"),
//        inverseJoinColumns = @JoinColumn(name = "projeto_id")
//    )
//    private List<Projeto> projetos;

//	public List<Projeto> getProjetos() {
//		return projetos;
//	}
//
//	public void setProjetos(List<Projeto> projetos) {
//		this.projetos = projetos;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}
	

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
    

    // Construtores, getters e setters omitidos para brevidade
	
	
}
