package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import sistema.dao.AlternativaDAO;
import sistema.modelos.Alternativa;

public class AlternativaService {
	AlternativaDAO alternativaDAO = new AlternativaDAO();

	public Alternativa salvar(Alternativa alternativa) {
		alternativa = alternativaDAO.save(alternativa);
		alternativaDAO.closeEntityManager();
		return alternativa;
	}

	public List<Alternativa> getAlternativas() {
		List<Alternativa> list = alternativaDAO.getAll(Alternativa.class);
		alternativaDAO.closeEntityManager();
		return list;
	}

	public void alterar(Alternativa alternativa) {
		alternativaDAO.save(alternativa);
		alternativaDAO.closeEntityManager();
	}

	public void remover(Alternativa alternativa) {
		alternativa = alternativaDAO.getById(Alternativa.class, alternativa.getCodigo());
		alternativaDAO.remove(alternativa);
		alternativaDAO.closeEntityManager();
	}
}