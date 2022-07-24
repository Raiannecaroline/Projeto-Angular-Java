package SoulCode.Services.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class UploudFile {
	
	public static void salvarArquivo(String uploudDir, String fileName, MultipartFile file) throws IOException {
		
		Path uploudPath = Paths.get(uploudDir);
		
		if(!Files.exists(uploudPath)) {
			Files.createDirectories(uploudPath);
		}
		
		/**
		 * Streaming - trabalhando com um fluxo de dados
		 * InputStream - Possibilita a leitura de algum dado em bytes
		 */
		try(InputStream inputStream = file.getInputStream()){
			
			Path filePath = uploudPath.resolve(fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
			
		}
		
		catch(IOException e) {
			throw new IOException("Não foi possível enviar o seu arquivo");
		}
		
	}

}
