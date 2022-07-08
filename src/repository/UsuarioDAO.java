package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import connectionFactory.ConnectionFactory;
import model.Usuario;

public class UsuarioDAO {
	private Connection conexao; // PEGANDO A CONEXAO DA CLASSE CONEXAO

	public UsuarioDAO() {
		this.conexao = new ConnectionFactory().conectar(); // ESTA CONEXAO É = MÉTODO DE CONEXAO

	}

	public void insert(Usuario usuario) throws SQLException {
		String sql = "INSERT INTO usuarios(id, nome, email, telefone, data) values (?,?,? ,?, ?)";
		PreparedStatement stmt = conexao.prepareStatement(sql); // CONVERSÃO DESTE COMANDO NO SQL
		stmt.setLong(1, usuario.getId());
		stmt.setString(2, usuario.getNome());
		stmt.setString(3, usuario.getEmail());
		stmt.setInt(4, usuario.getTelefone());
		stmt.setDate(5, usuario.getData());

		stmt.execute(); // EXECUTANDO DEPOIS DE TER GUARDADO OS REGISTROS NO COMANDO
		stmt.close(); // fechando...

	}

	public List<Usuario> select() throws SQLException {
		List<Usuario> usuarios = new ArrayList<Usuario>(); // RETORNANDO UMA LISTA
		String sql = "select * from usuarios";
		PreparedStatement stmt = conexao.prepareStatement(sql); // CONVERSÃO DESTE COMANDO NO SQL
		ResultSet rs = stmt.executeQuery(); // SALVANDO NO RESULT, o PROCESSO

		while (rs.next()) {
			Usuario usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"),
					rs.getInt("telefone"), (rs.getDate("data")));//DENTRO DA PRÓPRIA INSTANCIA, JÁ COLOCAMOS AUTOMATICAMENTE OS DADOS QUE GET

			// PEGO OS DADOS E CONSTRUO UM OBJETO PARA EXIBIR

			usuarios.add(usuario); // ADICIONANDO NA LISTA OS DADOS QUE PEGUEI DA TABELA NO BANCO
		}
		rs.close();
		stmt.close();
		return usuarios;
	}

	public Usuario selectById(Long id) throws SQLException {
		Usuario usuario = null;
		String sql = "select * from usuarios where id=?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setLong(1, id); //PUXANDO, GUARDANDO e ANALISANDO
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			usuario = new Usuario(rs.getLong("id"), rs.getString("nome"), rs.getString("email"), rs.getInt("telefone"),
					rs.getDate("data")); //DENTRO DA PRÓPRIA INSTANCIA, JÁ COLOCAMOS AUTOMATICAMENTE OS DADOS QUE GET
			System.out.println(rs.getString("nome")); //QUEREMOS RETORNAR O NOME PELO ID --> ID PASSADO
		}
		rs.close();
		stmt.close();
		return usuario;

	}

	public void delete(Long id) throws SQLException {
		String sql = "delete from usuarios where id=?";
		PreparedStatement stmt = conexao.prepareStatement(sql); // CONVERSÃO DESTE COMANDO NO SQL
		stmt.setLong(1, id);
		stmt.execute();
		stmt.close();
	}

	public void update(Usuario usuario) throws SQLException {
		String sql = "update usuarios set nome=?, email=?, telefone=? where id=?";

		PreparedStatement stmt = conexao.prepareStatement(sql); // CONVERSÃO DESTE COMANDO NO SQL
		stmt.setString(1, usuario.getNome());
		stmt.setString(2, usuario.getEmail());
		stmt.setInt(3, usuario.getTelefone());
		stmt.setLong(4, usuario.getId());

		stmt.execute();
		stmt.close();
	}

}