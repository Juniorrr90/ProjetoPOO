package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	private String nome;
	
	@ManyToOne
	private Professor professor;
	
	@OneToMany(mappedBy="disciplina")
	private List<Prova> provas = new ArrayList<Prova>();
	
	@OneToMany(mappedBy="disciplina")
	private List<Conteudo> conteudos = new ArrayList<Conteudo>();
	
	public void addProva(Prova prova)
	{
		provas.add(prova);
	}
	
	public void addConteudo(Conteudo conteudo)
	{
		conteudos.add(conteudo);
	}

	

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public List<Conteudo> getConteudos() {
		return conteudos;
	}

	public void setConteudos(List<Conteudo> conteudos) {
		this.conteudos = conteudos;
	}

	public Disciplina() {
		super();
	}

	public Disciplina(String nome, Professor professor) {
		super();
		this.nome = nome;
		this.professor = professor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + codigo;
		result = prime * result + ((conteudos == null) ? 0 : conteudos.hashCode());
		result = prime * result + ((professor == null) ? 0 : professor.hashCode());
		result = prime * result + ((provas == null) ? 0 : provas.hashCode());
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
		Disciplina other = (Disciplina) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (codigo != other.codigo)
			return false;
		if (conteudos == null) {
			if (other.conteudos != null)
				return false;
		} else if (!conteudos.equals(other.conteudos))
			return false;
		if (professor == null) {
			if (other.professor != null)
				return false;
		} else if (!professor.equals(other.professor))
			return false;
		if (provas == null) {
			if (other.provas != null)
				return false;
		} else if (!provas.equals(other.provas))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Disciplina [codDisciplina=" + codigo + ", nome=" + nome + ", professor=" + professor
				+ ", provas=" + provas + ", conteudos=" + conteudos + "]";
	}
}
