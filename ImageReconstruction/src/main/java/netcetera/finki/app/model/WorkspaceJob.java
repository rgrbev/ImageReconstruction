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
@Table(name = "workspace_job")
public class WorkspaceJob {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "dateCreated", nullable = false)
	private Date dateCreated;

	@Column(name = "detector", nullable = false)
	private String detector;

	@Column(name = "additionalDetectorOptions", nullable = true)
	private String additionalDetectorOptions;

	@Column(name = "descriptor", nullable = false)
	private String descriptor;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "dateFinished", nullable = true)
	private Date dateFinished;

	@Column(name = "isFinished", nullable = true)
	private boolean isFinished;

	@ManyToOne
	@JoinColumn(name = "idWorkspace")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Workspace workspace;

	public WorkspaceJob(String name, Date dateCreated, String detector, String additionalDetectorOptions, String descriptor,
			 Workspace workspace) {
		super();
		this.name=name;
		this.dateCreated = dateCreated;
		this.detector = detector;
		this.additionalDetectorOptions = additionalDetectorOptions;
		this.descriptor = descriptor;
		this.isFinished = false;
		
		this.workspace = workspace;
	}

	public WorkspaceJob() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public void setDateFinished(Date dateFinished) {
		this.dateFinished = dateFinished;
	}

	public String getDetector() {
		return detector;
	}

	public void setDetector(String detector) {
		this.detector = detector;
	}

	public String getAdditionalDetectorOptions() {
		return additionalDetectorOptions;
	}

	public void setAdditionalDetectorOptions(String additionalDetectorOptions) {
		this.additionalDetectorOptions = additionalDetectorOptions;
	}

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}
	
}
