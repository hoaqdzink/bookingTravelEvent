package booking.travel.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	@Autowired
	ServletContext app;
	
	static final String ROOT = "./src/main/resources/static/assets/images/";
//	static final String ROOT = "/assets/images/";

	public String saveFile(String folder, MultipartFile multipartFile) throws IOException{
		try {
			if(!multipartFile.isEmpty()) {
//				Path uploadPath = Paths.get(app.getRealPath(ROOT),folder);
				Path uploadPath = Paths.get(ROOT,folder);
				
				if(!Files.exists(uploadPath)){
					Files.createDirectories(uploadPath);
				}
				Path filePath = uploadPath.resolve(multipartFile.getOriginalFilename());
				multipartFile.transferTo(filePath);
				System.out.println(filePath.toFile().getAbsolutePath());
				return multipartFile.getOriginalFilename();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
