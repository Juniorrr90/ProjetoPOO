package sistema.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name="TipoPergunta", discriminatorType=DiscriminatorType.STRING,length=20)
public class Pergunta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int codigo;
	private int nivel;
	private double tempo;
	private String Imagem;

	private String tipo;
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@ManyToOne
	private Conteudo conteudo;
	
	@ManyToMany
	@JoinTable(name="TBL_PERGUNTA_PROVA",joinColumns=@JoinColumn(name="pergunta_id"),inverseJoinColumns=@JoinColumn(name="prova_id"))
	private List<Prova> provas = new ArrayList<Prova>();
	
	private String Enunciado;

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public double getTempo() {
		return tempo;
	}

	public void setTempo(double tempo) {
		this.tempo = tempo;
	}

	public String getImagem() {
		return Imagem;
	}

	public void setImagem(String imagem) {
		Imagem = imagem;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public void setConteudo(Conteudo conteudo) {
		this.conteudo = conteudo;
	}

	public List<Prova> getProvas() {
		return provas;
	}

	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}

	public String getEnunciado() {
		return Enunciado;
	}

	public void setEnunciado(String enunciado) {
		Enunciado = enunciado;
	}

	public void addProva(Prova prova)
	{
		provas.add(prova);
	}
}
