package sistema.beans;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import sistema.modelos.Alternativa;
import sistema.modelos.Dissertativa;
import sistema.modelos.Pergunta;
import sistema.modelos.Prova;
import sistema.modelos.VF;
import sistema.service.ProvaService;

@ManagedBean(name = "PDFManagedBean")
@ViewScoped
public class PDFManagedBean {
	private Prova prova;
	private List<Prova> provas;
	private ProvaService provaService = new ProvaService();
	
	public Prova getProva() {
		return prova;
	}
	public void setProva(Prova prova) {
		this.prova = prova;
	}
	public List<Prova> getProvas() {
		if(provas == null)
		{
			provas = provaService.getProvas();
		}
		return provas;
	}
	public void setProvas(List<Prova> provas) {
		this.provas = provas;
	}
	public ProvaService getProvaService() {
		return provaService;
	}
	public void setProvaService(ProvaService provaService) {
		this.provaService = provaService;
	}
	
	public void GeradorPDF()
	{
		Document pdf = new Document(PageSize.A4);
		Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
		Paragraph titulo = new Paragraph("Avaliação", f);
		
		titulo.setAlignment(Element.ALIGN_CENTER);
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("C:\\prova"+ prova.getCodigo() + ".pdf"));
          pdf.open();        
          
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade() + "     Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma() + "     Disciplina : " + prova.getDisciplina().getNome()));
          pdf.add(new Paragraph("Data: " + prova.getDataAplicacao().getDate() 
        		          		   +"/"+(prova.getDataAplicacao().getMonth()+1)+"/"+(prova.getDataAplicacao().getYear()-100))); 
          pdf.add(Chunk.NEWLINE);
          pdf.add(titulo);
          
          pdf.add(Chunk.NEWLINE);
          
          for(int i = 0; i < prova.getPerguntas().size(); i++)
          {
        	  if(prova.getPerguntas().get(i) instanceof Alternativa)
        	  {
        		  Alternativa alt = (Alternativa)prova.getPerguntas().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getPerguntas().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  A - " +  
     	                 alt.getAlt1()));
        		  pdf.add(new Paragraph("  B - " +  
      	                 alt.getAlt2()));
        		  pdf.add(new Paragraph("  C - " +  
      	                 alt.getAlt3()));
        		  pdf.add(new Paragraph("  D - " +  
      	                 alt.getAlt4()));
        	     pdf.add(Chunk.NEWLINE);
        	  }
        	  else if(prova.getPerguntas().get(i) instanceof VF)
        	  {
        		  pdf.add(new Paragraph(""+(i + 1)+") " + "Assinale (V)erdadeiro ou (F)also"));
        		  pdf.add(new Paragraph("  (  ) " +  
     	                 prova.getPerguntas().get(i).getEnunciado())); 
     	        	  pdf.add(Chunk.NEWLINE);
        	  }
        	  else
        	  {
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
        	                 prova.getPerguntas().get(i).getEnunciado())); 
        	        	  pdf.add(Chunk.NEWLINE);
        	  }
          }
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(Chunk.NEWLINE);
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
	
	public void GeradorGabaritoPDF()
	{
		Document pdf = new Document(PageSize.A4);
		Font f = new Font(FontFamily.COURIER, 20, Font.BOLD);
		Paragraph titulo = new Paragraph("Gabarito", f);
		
		titulo.setAlignment(Element.ALIGN_CENTER);
		
		try
		{
          PdfWriter.getInstance(pdf, new FileOutputStream("C:\\gabarito"+ prova.getCodigo() + ".pdf"));
          pdf.open();
          
          pdf.add(new Paragraph("Faculdade: " + prova.getFaculdade() + "     Curso: " + prova.getCurso()));
          pdf.add(new Paragraph("Turma: " + prova.getTurma() + "     Disciplina : " + prova.getDisciplina().getNome()));
          pdf.add(new Paragraph("Data: " + prova.getDataAplicacao().getDate() 
        		          		   +"/"+(prova.getDataAplicacao().getMonth()+1)+"/"+(prova.getDataAplicacao().getYear()-100))); 
          
          pdf.add(Chunk.NEWLINE);
          pdf.add(titulo);
          
          pdf.add(Chunk.NEWLINE);
          
          for(int i = 0; i < prova.getPerguntas().size(); i++)
          {
        	  if(prova.getPerguntas().get(i) instanceof Alternativa)
        	  {
        		  Alternativa alt = (Alternativa)prova.getPerguntas().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getPerguntas().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  R: " +  
        				  alt.getRespostaalt())); 
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getPerguntas().get(i) instanceof Dissertativa)
        	  {
        		  Dissertativa dis = (Dissertativa)prova.getPerguntas().get(i);
        		  pdf.add(new Paragraph(""+(i + 1)+") " +  
      	                 prova.getPerguntas().get(i).getEnunciado()));
        		  pdf.add(new Paragraph("  R: " +  
        				  dis.getRespostadis())); 
        		  pdf.add(Chunk.NEWLINE);
        	  }
        	  
        	  if(prova.getPerguntas().get(i) instanceof VF)
        	  {
        		 VF verd = (VF)prova.getPerguntas().get(i);
        		 pdf.add(new Paragraph(""+(i + 1)+") " +  
     	                 prova.getPerguntas().get(i).getEnunciado()));
        	     pdf.add(new Paragraph("  R: " +  
        	    		 verd.getRespostavf())); 
        	     pdf.add(Chunk.NEWLINE);
        	  }
          }
		}
		catch(DocumentException e)
		{
			System.err.println(e.getMessage());
		}
		catch(IOException e)
		{
			System.err.println(e.getMessage());
		}
		pdf.close();
	}
}