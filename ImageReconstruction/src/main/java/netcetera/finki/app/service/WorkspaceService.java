package netcetera.finki.app.service;

import java.util.List;

import netcetera.finki.app.model.*;

public interface WorkspaceService {
	public List<Workspace> getAllWorkspaces();
	public boolean editWorkspace(long id, String name, String description);//update na red
	public void deleteWorkspace(long id);
	public long addWorkspace(String name, String description);
	public Workspace getWorkspaceById(long id);
}
