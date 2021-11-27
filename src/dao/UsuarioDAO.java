package dao;

import java.util.List;

import model.Usuario;

public interface UsuarioDAO {

	public void salvar(Usuario usuario);

	public void altera(Usuario usuario);

	public void remover(String cpf);
	
	public Usuario pesquisar(String cpf);

	public List<Usuario> listaTodos();

}
