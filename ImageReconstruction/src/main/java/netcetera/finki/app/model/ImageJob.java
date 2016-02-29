package netcetera.finki.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "image_job")
public class ImageJob {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "isFinished", nullable = false)
	private boolean isFinished;

	@ManyToOne
	@JoinColumn(name = "idImage")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Image idImage;

	@ManyToOne
	@JoinColumn(name = "idWorkspaceJob")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private WorkspaceJob idWorkspaceJob;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Image getIdImage() {
		return idImage;
	}

	public void setIdImage(Image idImage) {
		this.idImage = idImage;
	}

	public WorkspaceJob getIdWorkspaceJob() {
		return idWorkspaceJob;
	}

	public void setIdWorkspaceJob(WorkspaceJob idWorkspaceJob) {
		this.idWorkspaceJob = idWorkspaceJob;
	}
	
	
}
