package netcetera.finki.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import netcetera.finki.app.model.*;

public interface ImageService {
	public List<Image> getAllImagesForWorkspace(long id);
	public long addImage(long id,MultipartFile image,String name);
	public void deleteImage(long id);
}
