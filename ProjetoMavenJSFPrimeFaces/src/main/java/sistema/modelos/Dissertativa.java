package sistema.modelos;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Dissertativa extends Pergunta implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String respostadis;

	public String getRespostadis() {
		return respostadis;
	}

	public void setRespostadis(String respostadis) {
		this.respostadis = respostadis;
	}

	public Dissertativa() {
		super();
	}
}
