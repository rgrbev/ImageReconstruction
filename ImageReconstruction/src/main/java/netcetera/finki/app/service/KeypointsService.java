package netcetera.finki.app.service;

import java.util.List;

import netcetera.finki.app.model.Keypoints;

public interface KeypointsService {
	public void readAndSaveResults(String path, long idImage);
	public Keypoints getKeypointsById(long idImage);
	public List<Keypoints> getAllKeypointsForWorkspaceJob(long idWorkspaceJob);
}
