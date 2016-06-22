package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.DisciplinaDAO;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;

public class DisciplinaService {

	DisciplinaDAO disciplinaDAO = new DisciplinaDAO();

	public Disciplina salvar(Disciplina disciplina) {
		disciplina = disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();
		return disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		List<Disciplina> list = disciplinaDAO.getAll(Disciplina.class);
		disciplinaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Disciplina disciplina) {
		disciplinaDAO.save(disciplina);
		disciplinaDAO.closeEntityManager();
	}

	public void remover(Disciplina disciplina) {
		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getCodigo());
		disciplinaDAO.remove(disciplina);
		disciplinaDAO.closeEntityManager();
	}

	public Disciplina pesquisar(Disciplina disciplina) {
		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getCodigo());
		disciplinaDAO.closeEntityManager();
		return disciplina;
	}

	public List<Conteudo> pesquisarConteudosDisciplina(Disciplina disciplina) {
		List<Conteudo> conteudos;

		disciplina = disciplinaDAO.getById(Disciplina.class, disciplina.getCodigo());
		conteudos = disciplina.getConteudos();

		return conteudos;
	}
}
