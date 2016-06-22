package sistema.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import sistema.dao.VFDAO;
import sistema.modelos.VF;

public class VFService {
	VFDAO vfDAO = new VFDAO();

	public VF salvar(VF vf) {
		vf = vfDAO.save(vf);
		vfDAO.closeEntityManager();
		return vf;
	}

	public List<VF> getVerdadeiros() {
		List<VF> list = vfDAO.getAll(VF.class);
		vfDAO.closeEntityManager();
		return list;
	}

	public void alterar(VF vf) {
		vfDAO.save(vf);
		vfDAO.closeEntityManager();
	}

	public void remover(VF vf) {
		vf = vfDAO.getById(VF.class, vf.getCodigo());
		vfDAO.remove(vf);
		vfDAO.closeEntityManager();
	}
}