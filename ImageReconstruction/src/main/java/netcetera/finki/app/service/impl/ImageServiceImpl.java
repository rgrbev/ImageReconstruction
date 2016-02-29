package netcetera.finki.app.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.Workspace;
import netcetera.finki.app.repository.ImageRepository;
import netcetera.finki.app.repository.WorkspaceRepository;
import netcetera.finki.app.service.ImageService;
import netcetera.finki.app.web.Properties;

import java.awt.image.BufferedImage;

@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imageRep;

	@Autowired
	WorkspaceRepository workspaceRep;

	@Override
	public List<Image> getAllImagesForWorkspace(long idWorkspace) {
		return imageRep.findByWorkspaceId(idWorkspace);
	}

	@Override
	public long addImage(long idWorkspace, MultipartFile file, String name) {
		if (!file.isEmpty()) {
			Workspace workspace = workspaceRep.findOne(idWorkspace);

			try {
				String workspacePath = Properties.workspacePath
						+ workspace.getName();
				File dirWorkspace = new File(workspacePath);
				if (dirWorkspace.exists()) {

					byte[] bytes = file.getBytes();
					// System.out.println("ORIGINALNIOPOO " + name);
					name = name.substring(name.lastIndexOf("\\") + 1, name.length());
					File imageFile = new File(dirWorkspace, name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(imageFile));
					stream.write(bytes);
					stream.close();

					String imageFilePath = workspacePath + File.separator + name;
					// System.out.println(imageFilePath);
					BufferedImage image = null;
					try {
						image = ImageIO.read(imageFile);
						

					} catch (IOException e) {
						
						e.printStackTrace();
					}
					double fileSize = file.getBytes().length;
					long idImage = insertImageInDatabase(imageFilePath, name, image.getWidth(), image.getHeight(), fileSize/(1024*1024),
							workspace.getId());

					return idImage;
				}
				return -1;
			} catch (Exception e) {
				return -1;
			}
		} else {
			return -1;
		}
	}

	@Override
	public void deleteImage(long idImage) {
		String imagePath = imageRep.findOne(idImage).getNamePath();
		
		String [] newPathArray = imagePath.split("\\\\");
		String lastTwo = newPathArray[newPathArray.length-2]+"\\"+newPathArray[newPathArray.length-1];
		Path path = Paths.get(Properties.workspacePath+lastTwo);	
		try {
			Files.delete(path);
			imageRep.delete(idImage);
		} catch (IOException x) {
			System.err.println(x);
		}

	}

	private long insertImageInDatabase(String namePath, String name, int width, int height, double size,
			long idWokspace) {
		Workspace workspace = workspaceRep.findOne(idWokspace);
		//System.out.println("BBBBBB"+namePath);
		String[] namePathArray = namePath.split("\\\\");
		//System.out.println("AAAAAAAAAAAAA"+namePath);
		String lastTwo = namePathArray[namePathArray.length-2]+"\\"+namePathArray[namePathArray.length-1];
		
		String serverLocationPath = "http:\\\\localhost:8181\\"+lastTwo;
		
		
		Image image = new Image(serverLocationPath, name, width, height, size, workspace);
		//System.out.println("ZA BAZAAAA " + namePath);
		Image saved = imageRep.save(image);
		if (saved != null) {
			return saved.getId();
		}
		return -1;
	}

}
