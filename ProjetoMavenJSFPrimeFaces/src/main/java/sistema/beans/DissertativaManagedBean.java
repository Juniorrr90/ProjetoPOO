package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import sistema.modelos.Conteudo;
import sistema.modelos.Dissertativa;
import sistema.service.ConteudoService;
import sistema.service.DissertativaService;

@ManagedBean(name = "dissertativaManagedBean")
@ViewScoped
public class DissertativaManagedBean {

	private Dissertativa dissertativa = new Dissertativa();
	private Conteudo conteudo;
	private DissertativaService prodService = new DissertativaService();
	private ConteudoService fornService = new ConteudoService();
	private List<Dissertativa> dissertativas;

	public void salvar() {
		dissertativa.setTipo("Dissertativa");
		conteudo.addPergunta(dissertativa);
		dissertativa.setConteudo(conteudo);
		dissertativa = prodService.salvar(dissertativa);

		if (dissertativas != null)
			dissertativas.add(dissertativa);

		dissertativa = new Dissertativa();
		conteudo = null;
	}

	public List<Conteudo> getConteudos() {
		return fornService.getConteudos();
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void remove(Dissertativa dissertativa) {
		prodService.remover(dissertativa);
		dissertativas.remove(dissertativa);
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public Dissertativa getDissertativa() {
		return dissertativa;
	}

	public void setDissertativa(Dissertativa dissertativa) {
		this.dissertativa = dissertativa;
	}

	public List<Dissertativa> getDissertativas() {
		if (dissertativas == null)
			dissertativas = prodService.getDissertativas();

		return dissertativas;
	}

	public void onRowEdit(RowEditEvent event) {
		Dissertativa p = ((Dissertativa) event.getObject());
		prodService.alterar(p);
	}
}