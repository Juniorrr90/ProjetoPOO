package sistema.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;

import org.primefaces.event.RowEditEvent;

import sistema.beans.datamodel.ConteudoDataModel;
import sistema.beans.datamodel.DisciplinaDataModel;
import sistema.modelos.Disciplina;
import sistema.modelos.Pergunta;
import sistema.modelos.Conteudo;
import sistema.service.DisciplinaService;
import sistema.service.ConteudoService;

@ManagedBean(name = "conteudoManagedBean")
@ViewScoped
public class ConteudoManagedBean {

	private Conteudo conteudo = new Conteudo();
	private Disciplina disciplina;
	private ConteudoService contService = new ConteudoService();
	private DisciplinaService disciplinaService = new DisciplinaService();
	private List<Conteudo> conteudos;
	private Conteudo conteudoSelecionado;

	public Conteudo getConteudoSelecionado() {
		return conteudoSelecionado;
	}

	public void setConteudoSelecionado(Conteudo conteudoSelecionado) {
		this.conteudoSelecionado = conteudoSelecionado;
	}

	public void salvar() {
		disciplina.addConteudo(conteudo);
		conteudo.setDisciplina(disciplina);

		conteudo = contService.salvar(conteudo);

		if (conteudos != null)
			conteudos.add(conteudo);

		conteudo = new Conteudo();
		disciplina = null;
	}
	
	public DataModel<Conteudo> getConteudos() {
		if (conteudos == null)
			conteudos = contService.getConteudos();

		return new ConteudoDataModel(conteudos);
	}

	public List<Disciplina> getDisciplinas() {
		return disciplinaService.getDisciplinas();
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void remove(Conteudo conteudo) {
		if (contService.pesquisarPerguntasConteudo(conteudo).size() > 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Não é possível remover conteudo",
					"Conteudo possui perguntas!"));
		} else {
			contService.remover(conteudo);
			conteudos.remove(conteudo);
		}
	}
	
	public List<Pergunta> getPerguntasConteudo() {
		if (conteudoSelecionado != null) {
			return contService.pesquisarPerguntasConteudo(conteudoSelecionado);
		} else
			return null;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public void onRowEdit(RowEditEvent event) {
		Conteudo p = ((Conteudo) event.getObject());
		contService.alterar(p);
	}
}