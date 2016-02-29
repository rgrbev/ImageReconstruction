package netcetera.finki.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import netcetera.finki.app.model.Workspace;

public interface WorkspaceRepository extends JpaRepository<Workspace,Long>{

}

