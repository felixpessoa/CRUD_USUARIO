package dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import dao.UsuarioDAO;
import model.Usuario;
import model.util.JpaUtil;

public class UsuarioDAOImpl implements UsuarioDAO {
	private EntityManager em;
	private EntityTransaction et;

	@Override
	public void salvar(Usuario usuario) {
		try {
			this.em = JpaUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.persist(usuario);
			et.commit();
		} catch (Exception e) {
			if (em.isOpen()) {
				et.rollback();
			}
			System.out.println("Erro ao salvar");
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

	}

	@Override
	public void altera(Usuario usuario) {
		try {
			this.em = JpaUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.merge(usuario);
			et.commit();
		} catch (Exception e) {
			if (em.isOpen()) {
				et.rollback();
			}
			System.out.println("Erro na alteração");
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public void remover(String cpf) {
		try {
			this.em = JpaUtil.getEntityManager();
			et = em.getTransaction();
			et.begin();
			em.remove(em.find(Usuario.class, cpf));
			et.commit();
		} catch (Exception e) {
			if (em.isOpen()) {
				et.rollback();
			}
			System.out.println("Erro para remover");
		} finally {
			if (em.isOpen()) {
				em.close();
			}
		}

	}

	@Override
	public Usuario pesquisar(String cpf) {
		Usuario usuario = new Usuario();
		try {
			this.em = JpaUtil.getEntityManager();
			usuario = em.find(Usuario.class, cpf);
		} catch (Exception e) {
			if (em.isOpen()) {
				et.rollback();
			}System.out.println("Erro na pesquisa");
		}finally {
			if (em.isOpen()) {
				em.close();
			}
		}
		return usuario;
	}

	@Override
	public List<Usuario> listaTodos() {
		this.em = JpaUtil.getEntityManager();
		Query query = em.createQuery("from Usuario e");
		List<Usuario> listaUsuario = query.getResultList();
		em.close();
		return listaUsuario;
	}

}
