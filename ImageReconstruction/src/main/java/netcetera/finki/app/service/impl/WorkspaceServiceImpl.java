package netcetera.finki.app.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.Workspace;
import netcetera.finki.app.service.WorkspaceService;
import netcetera.finki.app.web.Properties;
import netcetera.finki.app.repository.*;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

	@Autowired
	WorkspaceRepository workspaceRep;
	@Autowired
	ImageRepository imageRep;

	@Override
	public List<Workspace> getAllWorkspaces() {
		return workspaceRep.findAll();
	}

	@Override
	public boolean editWorkspace(long id, String name, String description) {
		Workspace editWorkspace = workspaceRep.findOne(id);
		String oldWorspacePath = editWorkspace.getPath();
		File oldDir = new File(oldWorspacePath);
		String newWorkspacePath = Properties.workspacePath
				+ name;
        File newDir = new File(newWorkspacePath);
        
                oldDir.renameTo(newDir);
		if (name != null && !name.isEmpty()) {
			editWorkspace.setName(name);
		}
		if (description != null && !description.isEmpty()) {
			editWorkspace.setDescription(description);
		}
		editWorkspace.setPath(newWorkspacePath);
		if (workspaceRep.save(editWorkspace) != null) {
			ArrayList<Image> allImagesInWorkspace = (ArrayList<Image>) imageRep.findByWorkspaceId(id);
			for(Image i : allImagesInWorkspace){
				String newPath = "http:\\\\localhost:8181\\"+name+"\\"+i.getName();
				i.setNamePath(newPath);
				imageRep.save(i);
			}
			
			return true;
		}
		return false;

	}

	@Override
	public void deleteWorkspace(long idWorksapce) {
		String workspacePath = workspaceRep.findOne(idWorksapce).getPath();
		File directory = new File(workspacePath);

		if (directory.exists()) {
			try {
				delete(directory);
				workspaceRep.delete(idWorksapce);
			} catch (IOException x) {
				System.err.println(x);
			}

		}
	}

	public void delete(File file) throws IOException {
		if (file.isDirectory()) {
			if (file.list().length == 0) {
				file.delete();
			} else {
				String files[] = file.list();
				for (String temp : files) {
					File fileDelete = new File(file, temp);

					delete(fileDelete);
				}
				if (file.list().length == 0) {
					file.delete();
				}
			}

		} else {
			file.delete();
		}
	}

	@Override
	public long addWorkspace(String name, String description) {
		Date dateCreated = new Date();
		String workspacePath = Properties.workspacePath
				+ name;
		Workspace w = new Workspace(name, workspacePath, description, dateCreated);
		Workspace saved = workspaceRep.save(w);
		if (saved != null) {// metod za proverka na workspace so isto ime vo
							// repositorito

			File dir = new File(workspacePath);
			dir.mkdirs();
			return saved.getId();
		}
		return -1;
	}

	@Override
	public Workspace getWorkspaceById(long idWorkspace) {
		return workspaceRep.findOne(idWorkspace);
	}

	
}
