package sistema.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.RowEditEvent;

import sistema.modelos.Conteudo;
import sistema.modelos.VF;
import sistema.service.ConteudoService;
import sistema.service.VFService;

@ManagedBean(name = "vfManagedBean")
@ViewScoped
public class VFManagedBean {

	private VF verdadeiro = new VF();
	private Conteudo conteudo;
	private VFService vfService = new VFService();
	private ConteudoService contService = new ConteudoService();
	private List<VF> verdadeiros;

	public void salvar() {
		verdadeiro.setTipo("Verdadeiro/Falso");
		conteudo.addPergunta(verdadeiro);
		verdadeiro.setConteudo(conteudo);

		verdadeiro = vfService.salvar(verdadeiro);

		if (verdadeiros != null)
			verdadeiros.add(verdadeiro);

		verdadeiro = new VF();
		conteudo = null;
	}

	public List<Conteudo> getConteudos() {
		return contService.getConteudos();
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void remove(VF verdadeiro) {
		vfService.remover(verdadeiro);
		verdadeiros.remove(verdadeiro);
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public VF getVerdadeiro() {
		return verdadeiro;
	}

	public void setVerdadeiro(VF verdadeiro) {
		this.verdadeiro = verdadeiro;
	}

	public List<VF> getVerdadeiros() {
		if (verdadeiros == null)
			verdadeiros = vfService.getVerdadeiros();

		return verdadeiros;
	}

	public void onRowEdit(RowEditEvent event) {
		VF p = ((VF) event.getObject());
		vfService.alterar(p);
	}
}