package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.PerguntaDAO;
import sistema.modelos.Pergunta;

public class PerguntaService {
	PerguntaDAO perguntaDAO = new PerguntaDAO();

	public Pergunta salvar(Pergunta pergunta) {
		pergunta = perguntaDAO.save(pergunta);
		perguntaDAO.closeEntityManager();
		return pergunta;
	}

	public List<Pergunta> getPerguntas() {
		List<Pergunta> list = perguntaDAO.getAll(Pergunta.class);
		perguntaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Pergunta pergunta) {
		perguntaDAO.save(pergunta);
		perguntaDAO.closeEntityManager();
	}

	public void remover(Pergunta pergunta) {
		pergunta = perguntaDAO.getById(Pergunta.class, pergunta.getCodigo());
		perguntaDAO.remove(pergunta);
		perguntaDAO.closeEntityManager();
	}
}
