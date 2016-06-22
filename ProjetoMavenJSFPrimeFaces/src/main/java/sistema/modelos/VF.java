package sistema.modelos;


import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class VF extends Pergunta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String respostavf;

	public VF() {
		super();
	}

	public String getRespostavf() {
		return respostavf;
	}

	public void setRespostavf(String respostavf) {
		this.respostavf = respostavf;
	}
}
