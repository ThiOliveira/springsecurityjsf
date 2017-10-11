package br.edu.unicatolica.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class DownloadArquivoUtil {

	public static DownloadArquivoUtil getInstance() {
		return new DownloadArquivoUtil();
	}

	private DownloadArquivoUtil() {

	}

	public static String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext()
			.getRealPath("/WEB-INF/arquivos");

	// Download de Arquivo
	public StreamedContent downloadArquivo(byte[] arquivoData, StreamedContent arquivo, String nomeDoArquivo,
			String tipoArquivo) {

		try {

			FileUtils.writeByteArrayToFile(new File(caminhoWebInf + nomeDoArquivo), arquivoData);

			InputStream stream = new FileInputStream(caminhoWebInf + nomeDoArquivo);
			arquivo = new DefaultStreamedContent(stream, "arquivo/" + tipoArquivo, nomeDoArquivo + "." + tipoArquivo);
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext()
					.getResponse();
			response.setContentType("application/pdf");
			response.setHeader("application/pdf", "Content-Type");
			response.addHeader("content-disposition", "inline; filename=\\" + nomeDoArquivo + ".pdf");
			response.setHeader("Content-Length", String.valueOf(arquivoData.length));
			response.getOutputStream().write(arquivoData);
			ServletOutputStream streamm = response.getOutputStream();
			
			streamm.flush();
			streamm.close();
			
			FacesContext.getCurrentInstance().responseComplete();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (NullPointerException npe) {
			Message.addErrorMessage("", "Arquivo de avaliação não anexado!");
		}

		return arquivo;
	}

}
