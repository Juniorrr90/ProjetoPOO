package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import sistema.dao.ProvaDAO;
import sistema.modelos.Prova;

public class ProvaService {
	ProvaDAO provaDAO = new ProvaDAO();

	public Prova salvar(Prova prova) {
		prova = provaDAO.save(prova);
		provaDAO.closeEntityManager();
		return prova;
	}

	public List<Prova> getProvas() {
		List<Prova> list = provaDAO.getAll(Prova.class);
		provaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Prova prova) {
		provaDAO.save(prova);
		provaDAO.closeEntityManager();
	}

	public void remover(Prova prova) {
		prova = provaDAO.getById(Prova.class, prova.getCodigo());
		provaDAO.remove(prova);
		provaDAO.closeEntityManager();
	}
}
