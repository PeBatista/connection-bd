package testes;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import model.Usuario;
import repository.UsuarioDAO;

public class Executavel {

	public static void main(String[] args) throws SQLException {
		Calendar hoje = Calendar.getInstance(); // CLASSE
		Date data = new Date(hoje.getTimeInMillis()); //
		UsuarioDAO usuariodao = new UsuarioDAO();
//		Usuario usuario = new Usuario(1, "Pedro Batista", "pedrobatista242526@gmail.com", 983804483, data); 
//		Usuario usuario = new Usuario(1, "Pablo Batista", "pedrobatista242526@gmail.com", 984804483, data); TESTE DE UPDATE DE PEDRO PARA PABLO
		Usuario lerry = new Usuario(2, "Luis Batista", "luisbatista2425276@gmail.com", 986866686, data);
		Usuario lerro = new Usuario(3, "Victor Batista", "victorbatista2425276@gmail.com", 985866686, data);

//		try {
//			usuariodao.insert(lerro);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

//		try {
//			usuariodao.update(usuario);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		List<Usuario> lista = usuariodao.select(); // COLOCAMOS OQUE O SELECT PEGA DENTRO DO LISTA
		for (Usuario user : lista) { // PASSANDO PELOS DADOS PEGADOS E MOSTRANDO APENAS OS QUE QUEREMOS
			System.out.println(user.getNome());
		}
		
		try {
			usuariodao.selectById(1l);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
