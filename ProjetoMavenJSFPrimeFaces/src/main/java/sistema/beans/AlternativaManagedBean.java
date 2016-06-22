package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Conteudo;
import sistema.modelos.Alternativa;
import sistema.service.ConteudoService;
import sistema.service.AlternativaService;

@ManagedBean(name = "alternativaManagedBean")
@ViewScoped
public class AlternativaManagedBean {

	private Alternativa alternativa = new Alternativa();
	private Conteudo conteudo;
	private AlternativaService prodService = new AlternativaService();
	private ConteudoService fornService = new ConteudoService();
	private List<Alternativa> alternativas;

	public void salvar() {
		alternativa.setTipo("Alternativa");
		conteudo.addPergunta(alternativa);
		alternativa.setConteudo(conteudo);

		alternativa = prodService.salvar(alternativa);

		if (alternativas != null)
			alternativas.add(alternativa);

		alternativa = new Alternativa();
		conteudo = null;
	}

	public List<Conteudo> getConteudos() {
		return fornService.getConteudos();
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void remove(Alternativa alternativa) {
		prodService.remover(alternativa);
		alternativas.remove(alternativa);
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public Alternativa getAlternativa() {
		return alternativa;
	}

	public void setAlternativa(Alternativa alternativa) {
		this.alternativa = alternativa;
	}

	public List<Alternativa> getAlternativas() {
		if (alternativas == null)
			alternativas = prodService.getAlternativas();

		return alternativas;
	}

	public void onRowEdit(RowEditEvent event) {
		Alternativa p = ((Alternativa) event.getObject());
		prodService.alterar(p);
	}
}