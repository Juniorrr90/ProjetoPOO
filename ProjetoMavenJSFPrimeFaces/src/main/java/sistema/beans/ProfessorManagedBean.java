package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import sistema.beans.datamodel.ProfessorDataModel;
import sistema.modelos.Professor;
import sistema.modelos.Disciplina;
import sistema.service.ProfessorService;

@ManagedBean
@ViewScoped
public class ProfessorManagedBean {
	private Professor professor = new Professor();
	private Professor professorSelecionado;
	private ProfessorService servico = new ProfessorService();
	private List<Professor> professores;

	/**
	 * @return the professorSelecionado
	 */
	public Professor getProfessorSelecionado() {
		return professorSelecionado;
	}

	/**
	 * @param professorSelecionado
	 *            the professorSelecionado to set
	 */
	public void setProfessorSelecionado(Professor professorSelecionado) {
		this.professorSelecionado = professorSelecionado;
	}

	public void salvar() {
		professor = servico.salvar(professor);

		if (professores != null)
			professores.add(professor);

		professor = new Professor();
	}

	public DataModel<Professor> getProfessores() {
		if (professores == null)
			professores = servico.getProfessores();

		return new ProfessorDataModel(professores);
	}

	public Professor getProfessor() {
		return professor;
	}

	public void remove(Professor professor) {
		if (servico.pesquisarDisciplinasProfessor(professor).size() > 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não é possível remover professor",
					"Professor possui disciplinas!"));
		} else {
			servico.remover(professor);
			professores.remove(professor);
		}
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Disciplina> getDisciplinasProfessor() {
		if (professorSelecionado != null) {
			return servico.pesquisarDisciplinasProfessor(professorSelecionado);
		} else
			return null;
	}

	public void onRowEdit(RowEditEvent event) {
		Professor f = ((Professor) event.getObject());
		servico.alterar(f);
	}
}