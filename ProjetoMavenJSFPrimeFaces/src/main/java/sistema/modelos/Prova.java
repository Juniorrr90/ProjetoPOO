package sistema.modelos;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Prova implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	
	@Temporal(TemporalType.DATE)
	private Date dataAplicacao;
	
	private String nomeprova;
	
	private String curso;
	private String faculdade;
	private int nivel;
	
	private double tempo;
	private String turma;
	private int quantidade;
	
	@ManyToOne
	private Disciplina disciplina;
	
	@ManyToMany(mappedBy="provas")
	private List<Pergunta> perguntas = new ArrayList<Pergunta>();
	
	@ManyToMany(mappedBy="provas")
	private List<Conteudo> conteudos = new ArrayList<Conteudo>();
	
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNomeprova() {
		return nomeprova;
	}
	public void setNomeprova(String nomeprova) {
		this.nomeprova = nomeprova;
	}
	
	public Date getDataAplicacao() {
		return dataAplicacao;
	}
	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}
	public String getFaculdade() {
		return faculdade;
	}
	public void setFaculdade(String faculdade) {
		this.faculdade = faculdade;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public double getTempo() {
		return tempo;
	}
	public void setTempo(double tempo) {
		this.tempo = tempo;
	}
	public String getTurma() {
		return turma;
	}
	public void setTurma(String turma) {
		this.turma = turma;
	}
	public Disciplina getDisciplina() {
		return disciplina;
	}
	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	public List<Pergunta> getPerguntas() {
		return perguntas;
	}
	public void setPerguntas(List<Pergunta> perguntas) {
		this.perguntas = perguntas;
	}
	public List<Conteudo> getConteudos() {
		return conteudos;
	}
	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}
	
	public void addPergunta(Pergunta pergunta)
	{
		perguntas.add(pergunta);
	}
	
	public void addConteudo(Conteudo conteudo)
	{
		conteudos.add(conteudo);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
		result = prime * result + ((faculdade == null) ? 0 : faculdade.hashCode());
		result = prime * result + ((nomeprova == null) ? 0 : nomeprova.hashCode());
		result = prime * result + ((turma == null) ? 0 : turma.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prova other = (Prova) obj;
		if (codigo != other.codigo)
			return false;
		if (faculdade == null) {
			if (other.faculdade != null)
				return false;
		} else if (!faculdade.equals(other.faculdade))
			return false;
		if (nomeprova == null) {
			if (other.nomeprova != null)
				return false;
		} else if (!nomeprova.equals(other.nomeprova))
			return false;
		if (turma == null) {
			if (other.turma != null)
				return false;
		} else if (!turma.equals(other.turma))
			return false;
		return true;
	}
}