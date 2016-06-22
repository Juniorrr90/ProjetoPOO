package sistema.modelos;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Alternativa extends Pergunta implements Serializable  {
	
	private char respostaalt ;
	private String alt1;
	private String alt2;
	private String alt3;
	private String alt4;

	private static final long serialVersionUID = 1L;
	
	public String getAlt1() {
		return alt1;
	}

	public void setAlt1(String alt1) {
		this.alt1 = alt1;
	}

	public String getAlt2() {
		return alt2;
	}

	public void setAlt2(String alt2) {
		this.alt2 = alt2;
	}

	public String getAlt3() {
		return alt3;
	}

	public void setAlt3(String alt3) {
		this.alt3 = alt3;
	}

	public String getAlt4() {
		return alt4;
	}

	public void setAlt4(String alt4) {
		this.alt4 = alt4;
	}

	public Alternativa() {
		super();
	}

	public char getRespostaalt() {
		return respostaalt;
	}

	public void setRespostaalt(char respostaalt) {
		this.respostaalt = respostaalt;
	}
}