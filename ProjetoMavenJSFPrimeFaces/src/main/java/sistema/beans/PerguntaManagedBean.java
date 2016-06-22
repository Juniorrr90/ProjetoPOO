package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import sistema.modelos.Conteudo;
import sistema.modelos.Pergunta;
import sistema.service.ConteudoService;
import sistema.service.PerguntaService;

@ManagedBean(name = "perguntaManagedBean")
@ViewScoped
public class PerguntaManagedBean {

	private Pergunta pergunta = new Pergunta();
	private Conteudo conteudo;
	private PerguntaService prodService = new PerguntaService();
	private ConteudoService fornService = new ConteudoService();
	private List<Pergunta> perguntas;

	public void salvar() {
		conteudo.addPergunta(pergunta);
		pergunta.setConteudo(conteudo);

		pergunta = prodService.salvar(pergunta);

		if (perguntas != null)
			perguntas.add(pergunta);

		pergunta = new Pergunta();
		conteudo = null;
	}

	public List<Conteudo> getConteudos() {
		return fornService.getConteudos();
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void remove(Pergunta pergunta) {
		prodService.remover(pergunta);
		perguntas.remove(pergunta);
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public Pergunta getPergunta() {
		return pergunta;
	}

	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}

	public List<Pergunta> getPerguntas() {
		if (perguntas == null)
			perguntas = prodService.getPerguntas();

		return perguntas;
	}

	public void onRowEdit(RowEditEvent event) {
		Pergunta p = ((Pergunta) event.getObject());
		prodService.alterar(p);
	}
}