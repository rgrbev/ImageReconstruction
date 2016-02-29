package netcetera.finki.app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.WorkspaceJob;

public interface WorkspaceJobService {
	public WorkspaceJob getWorkspaceJob(long id);
	public List<WorkspaceJob> getAllWorkspaceJobsForWorkspace(long id);
	public long addWorkspaceJob(String name, long idWokrspace, String detector,String additionalDetectorOptions ,String descriptor);
	public void deleteWorkspaceJob(long id);
	public void setDateFinished(long id);
}
