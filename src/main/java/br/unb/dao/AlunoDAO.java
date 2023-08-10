package br.unb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Aluno;
import br.unb.dominio.Disciplina;

public class AlunoDAO {
	public Aluno salvar(Aluno aluno) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		session.save(aluno);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return aluno;

	}
	public Aluno getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Aluno aluno = (Aluno) session.get(Aluno.class, id);

//		// Carregando uma disciplina com ID 
//		Disciplina disciplina = (Disciplina) session.load(Disciplina.class, id);

		// Encerrando a sessão
		session.close();
		return aluno;
	}

	public Aluno update(Aluno p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		session.update(p);

		tx.commit();
		session.close();

		return p;

	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma disciplina existente pelo ID
		Aluno aluno = (Aluno) session.get(Aluno.class, id);

		session.delete(aluno);

		tx.commit();
		session.close();

	}

	public List<Aluno> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Aluno");
		List<Aluno> alunos = query.list();
		session.close();
		return alunos;
	}

	public List<Aluno> findPorIdadeMinima(int idade) {
		int idadeMinima = idade;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Aluno WHERE idade > :idade");
		query.setParameter("idade", idadeMinima);
		List<Aluno> alunos = query.list();
		session.close();
		return alunos;
	}

	public List<Aluno> listByNomeSQL(String nome) {
		String sql = "SELECT * FROM aluno WHERE nome = :nome";
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Aluno.class);
		sqlQuery.setParameter("nome", nome);
		List<Aluno> alunos = sqlQuery.list();
		session.close();
		return alunos;

	}
}