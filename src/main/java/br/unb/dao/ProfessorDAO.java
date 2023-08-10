package br.unb.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import br.unb.dominio.Professor;

public class ProfessorDAO {

	public Professor salvar(Professor professor) {
		// Configuração da sessão do Hibernate (SessionFactory)
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		// Iniciando a transação
		Transaction tx = session.beginTransaction();

		// Salvando a professor no banco de dados
		session.save(professor);

		// Comitando a transação
		tx.commit();

		// Fechando a sessão
		session.close();
		return professor;

	}
	public Professor getById(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Usando get() para ler a professor com o ID especificado
		Professor professor = (Professor) session.get(Professor.class, id);

//		// Carregando uma professor com ID 
//		Professor professor = (Professor) session.load(Professor.class, id);

		// Encerrando a sessão
		session.close();
		return professor;
	}

	public Professor update(Professor p) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Atualizando a professor no banco de dados
		session.update(p);

		tx.commit();
		session.close();

		return p;

	}

	public void delete(Long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		// Lendo uma professor existente pelo ID
		Professor professor = (Professor) session.get(Professor.class, id);

		// Excluindo a professor do banco de dados
		session.delete(professor);

		tx.commit();
		session.close();

	}

	public List<Professor> findAll() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery("FROM Professor");
		List<Professor> professores = query.list();
		session.close();
		return professores;
	}

	public List<Object[]> professoresPorDisciplina(int idDisciplina) {
		long disciplinaId = 1L;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(
				"SELECT COUNT(p), p.nome FROM Professor p WHERE p.disciplina.id = :disciplinaId GROUP BY p.nome ORDER BY p.nome");
		query.setParameter("disciplinaId", disciplinaId);
		List<Object[]> result = query.list();
		session.close();
		return result;
	}

	public List<Professor> listByNomeSQL(String nome) {
		String sql = "SELECT * FROM professor WHERE nome = :nome";
		Session session = HibernateUtil.getSessionFactory().openSession();
		SQLQuery sqlQuery = session.createSQLQuery(sql);
		sqlQuery.addEntity(Professor.class);
		sqlQuery.setParameter("nome", nome);
		List<Professor> professores = sqlQuery.list();
		session.close();
		return professores;

	}
}