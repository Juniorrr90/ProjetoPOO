package sistema.beans.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import sistema.modelos.Conteudo;
import sistema.service.ConteudoService;

@FacesConverter("converterConteudo")
public class ConteudoConverter implements Converter {

	private ConteudoService servico = new ConteudoService();
	
	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
		if (value != null && !value.isEmpty()) {
			
			  for(Conteudo f : servico.getConteudos())
				 if(f.getNome().equals(value))
				  	return f;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic,
			Object conteudo) {
		if (conteudo == null || conteudo.equals("")) {
			return null;
		} else {
			return ((Conteudo) conteudo).getNome();
		}
	}
}