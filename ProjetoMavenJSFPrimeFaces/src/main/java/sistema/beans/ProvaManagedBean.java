package sistema.beans;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import sistema.modelos.Conteudo;
import sistema.modelos.Disciplina;
import sistema.modelos.Pergunta;
import sistema.modelos.Prova;
import sistema.service.ConteudoService;
import sistema.service.DisciplinaService;
import sistema.service.PerguntaService;
import sistema.service.ProvaService;

@ManagedBean(name = "provaManagedBean")
@ViewScoped
public class ProvaManagedBean {
	
	private Prova prova = new Prova();
	private Disciplina disciplina;
	private ProvaService prodService = new ProvaService();
	private List<Conteudo> conteudosselecionados;
	private List<Conteudo> conteudos;
	private ConteudoService contService = new ConteudoService();
	private List<Pergunta> perguntas;
	private double tempo = 0;
	private PerguntaService pergService = new PerguntaService();
	private DisciplinaService fornService = new DisciplinaService();
	private List<Prova> provas;

	public void salvar() {
		addConteudos();
		addPerguntas();
		prova.setNomeprova(prova.getFaculdade()+ " " + prova.getTurma());
		
		prova.setTempo(tempo);
		tempo = 0;
		disciplina.addProva(prova);
		prova.setDisciplina(disciplina);
		
		prova = prodService.salvar(prova);

		if (provas != null)
			provas.add(prova);
		
		prova = new Prova();
		disciplina = null;
	}
	
    public void addPerguntas()
    {
    	int cont = 0;
    	perguntas = getPerguntas();
    	
    	Collections.shuffle(perguntas); 
    	Collections.reverse(perguntas); 

    	for(Pergunta p: perguntas)
    	{
    		for(Conteudo c: conteudosselecionados)
    		{
    			if(p.getConteudo().getNome() == c.getNome())
    			{
    				if(p.getNivel() == prova.getNivel())
    				{
    					if(cont < prova.getQuantidade())
    					{
    					  prova.addPergunta(p);
    					  tempo = tempo + p.getTempo();
    					  cont++;
    					}
    				}
    			}
    		}
    	}
    	
    	if(cont < prova.getQuantidade()) 
		{ 
 			int resto = 0; 
 			 
 			resto = prova.getQuantidade() - cont; 
 			 
 			for(int i = 0; i < resto; i++) 
 			{ 
 				for(int j = 0; j < perguntas.size(); j++) 
 				{	
 					for(Conteudo c: conteudosselecionados)
 					{
 						if(perguntas.get(j).getConteudo().getNome() == c.getNome())
 						{
 				           if(perguntas.get(j).getNivel() < prova.getNivel() && !prova.getPerguntas().contains(perguntas.get(j))) 
 				           { 
 					         prova.addPergunta(perguntas.get(j)); 
 					         tempo = tempo + perguntas.get(j).getTempo();
 				           } 
 						}      
 					}
 			    }
 			} 
 		} 
    }
    
    public List<Pergunta> getPerguntas() {
		return pergService.getPerguntas();
	}
	
	public List<Disciplina> getDisciplinas() {
		return fornService.getDisciplinas();
	}

	public List<Conteudo> getConteudos() {
		return contService.getConteudos();
	}
	
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
 
	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void remove(Prova prova) {
		prodService.remover(prova);
		provas.remove(prova);
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public List<Prova> getProvas() {
		if (provas == null)
			provas = prodService.getProvas();

		return provas;
	}

	public void onRowEdit(RowEditEvent event) {
		Prova p = ((Prova) event.getObject());
		prodService.alterar(p);
	}
	
	public List<Conteudo> getConteudosselecionados() {
		return conteudosselecionados;
	}

	public void setConteudosselecionados(List<Conteudo> conteudosselecionados) {
		this.conteudosselecionados = conteudosselecionados;
	}
	
	public void addConteudos()
	{
		for (int i = 0; i < conteudosselecionados.size(); i++) 
		{
			prova.addConteudo(conteudosselecionados.get(i));
		}
	}

}