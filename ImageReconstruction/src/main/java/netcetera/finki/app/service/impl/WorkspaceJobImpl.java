package netcetera.finki.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.Workspace;
import netcetera.finki.app.model.WorkspaceJob;
import netcetera.finki.app.repository.ImageRepository;
import netcetera.finki.app.repository.WorkspaceJobRepository;
import netcetera.finki.app.repository.WorkspaceRepository;
import netcetera.finki.app.service.WorkspaceJobService;
import netcetera.finki.app.service.WorkspaceService;

@Service
public class WorkspaceJobImpl implements WorkspaceJobService {

	@Autowired
	WorkspaceJobRepository workspaceJobRep;

	@Autowired
	WorkspaceRepository workspaceRep;

	@Override
	public List<WorkspaceJob> getAllWorkspaceJobsForWorkspace(long id) {
		return workspaceJobRep.findByWorkspaceId(id);
	}

	@Override
	public long addWorkspaceJob(String name, long idWokrspace, String detector, String additionalDetectorOptions,
			String descriptor) {
		Date now = new Date();
		if (detector != null && detector.length() > 0 && additionalDetectorOptions != null
				&& additionalDetectorOptions.length() > 0 && descriptor != null && descriptor.length() > 0) {
			WorkspaceJob workspaceJob = new WorkspaceJob(name,now, detector, additionalDetectorOptions, descriptor,
					workspaceRep.findOne(idWokrspace));

			WorkspaceJob saved = workspaceJobRep.save(workspaceJob);
			if (saved != null) {
				return saved.getId();
			}
			return -1;
		}
		return -1;
	}

	@Override
	public void deleteWorkspaceJob(long id) {
		workspaceJobRep.delete(id);

	}

	@Override
	public WorkspaceJob getWorkspaceJob(long id) {
		return workspaceJobRep.findOne(id);
	}

	@Override
	public void setDateFinished(long id) {
		Date now = new Date();
		WorkspaceJob workspaceJob = workspaceJobRep.findOne(id);
		workspaceJob.setDateFinished(now);
		workspaceJobRep.save(workspaceJob);
	}

}
