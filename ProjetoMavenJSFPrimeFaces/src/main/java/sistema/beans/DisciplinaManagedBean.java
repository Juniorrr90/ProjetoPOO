package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import sistema.beans.datamodel.DisciplinaDataModel;
import sistema.modelos.Conteudo;
import sistema.modelos.Professor;
import sistema.modelos.Disciplina;
import sistema.service.ProfessorService;
import sistema.service.DisciplinaService;

@ManagedBean(name = "disciplinaManagedBean")
@ViewScoped
public class DisciplinaManagedBean {

	private Disciplina disciplina = new Disciplina();
	private Professor professor;
	private DisciplinaService prodService = new DisciplinaService();
	private ProfessorService fornService = new ProfessorService();
	private Disciplina disciplinaSelecionada;
	private List<Disciplina> disciplinas;

	public Disciplina getDisciplinaSelecionada() {
		return disciplinaSelecionada;
	}

	public void setDisciplinaSelecionada(Disciplina disciplinaSelecionada) {
		this.disciplinaSelecionada = disciplinaSelecionada;
	}

	public void salvar() {
		professor.addDisciplina(disciplina);
		disciplina.setProfessor(professor);

		disciplina = prodService.salvar(disciplina);

		if (disciplinas != null)
			disciplinas.add(disciplina);

		disciplina = new Disciplina();
		professor = null;
	}
	
	public DataModel<Disciplina> getDisciplinas() {
		if (disciplinas == null)
			disciplinas = prodService.getDisciplinas();

		return new DisciplinaDataModel(disciplinas);
	}

	public List<Professor> getProfessores() {
		return fornService.getProfessores();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void remove(Disciplina disciplina) {
		if (prodService.pesquisarConteudosDisciplina(disciplina).size() > 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não é possível remover disciplina",
					"Disciplina possui conteudos!"));
		} else {
			prodService.remover(disciplina);
			disciplinas.remove(disciplina);
		}
	}
	
	public List<Conteudo> getConteudosDisciplina() {
		if (disciplinaSelecionada != null) {
			return prodService.pesquisarConteudosDisciplina(disciplinaSelecionada);
		} else
			return null;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public void onRowEdit(RowEditEvent event) {
		Disciplina p = ((Disciplina) event.getObject());
		prodService.alterar(p);
	}
}