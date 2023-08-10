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

public class DisciplinaDAO {
	public Disciplina salvar(Disciplina disciplina) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a disciplina no banco de dados
		session.save(disciplina);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return disciplina;

	}
	public Disciplina getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		// Usando get() para ler a disciplina com o ID especificado
		Disciplina disciplina = (Disciplina) session.get(Disciplina.class, id);

//		// Carregando uma disciplina com ID 
//		Disciplina disciplina = (Disciplina) session.load(Disciplina.class, id);

		// Encerrando a sessão
		session.close();
		return disciplina;
	}

	public Disciplina update(Disciplina p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Atualizando a disciplina no banco de dados
		session.update(p);

		tx.commit();
		session.close();

		return p;

	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma disciplina existente pelo ID
		Disciplina disciplina = (Disciplina) session.get(Disciplina.class, id);

		// Excluindo a disciplina do banco de dados
		session.delete(disciplina);

		tx.commit();
		session.close();

	}

	public List<Disciplina> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Disciplina");
		List<Disciplina> disciplinas = query.list();
		session.close();
		return disciplinas;
	}

	public List<Disciplina> findPorIdadeMinima(int idade) {
		int idadeMinima = idade;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Disciplina WHERE idade > :idade");
		query.setParameter("idade", idadeMinima);
		List<Disciplina> disciplinas = query.list();
		session.close();
		return disciplinas;
	}

	public List<Disciplina> listByNomeSQL(String nome) {
		String sql = "SELECT * FROM disciplina WHERE nome = :nome";
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Disciplina.class);
		sqlQuery.setParameter("nome", nome);
		List<Disciplina> disciplinas = sqlQuery.list();
		session.close();
		return disciplinas;

	}
}