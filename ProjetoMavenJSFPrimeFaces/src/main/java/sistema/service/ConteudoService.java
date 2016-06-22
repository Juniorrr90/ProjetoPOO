package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.ConteudoDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Pergunta;

public class ConteudoService {
	ConteudoDAO conteudoDAO = new ConteudoDAO();

	public Conteudo salvar(Conteudo conteudo) {
		conteudo = conteudoDAO.save(conteudo);
		conteudoDAO.closeEntityManager();
		return conteudo;
	}

	public List<Conteudo> getConteudos() {
		List<Conteudo> list = conteudoDAO.getAll(Conteudo.class);
		conteudoDAO.closeEntityManager();
		return list;
	}

	public void alterar(Conteudo conteudo) {
		conteudoDAO.save(conteudo);
		conteudoDAO.closeEntityManager();
	}

	public void remover(Conteudo conteudo) {
		conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getCodigo());
		conteudoDAO.remove(conteudo);
		conteudoDAO.closeEntityManager();
	}

	public Conteudo pesquisar(Conteudo conteudo) {
		conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getCodigo());
		conteudoDAO.closeEntityManager();
		return conteudo;
	}

	public List<Pergunta> pesquisarPerguntasConteudo(Conteudo conteudo) {
		List<Pergunta> perguntas;

		conteudo = conteudoDAO.getById(Conteudo.class, conteudo.getCodigo());
		perguntas = conteudo.getPerguntas();

		return perguntas;
	}
}
