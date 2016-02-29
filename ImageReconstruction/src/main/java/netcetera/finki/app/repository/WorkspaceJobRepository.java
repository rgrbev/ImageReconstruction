package netcetera.finki.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import netcetera.finki.app.model.Image;
import netcetera.finki.app.model.WorkspaceJob;

public interface WorkspaceJobRepository extends JpaRepository<WorkspaceJob,Long>{
	List<WorkspaceJob> findByWorkspaceId(long idWorkspace);
}

