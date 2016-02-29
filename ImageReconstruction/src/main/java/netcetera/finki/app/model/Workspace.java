package netcetera.finki.app.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="workspace")
public class Workspace {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="id",nullable=false)
	private long id;
	
	@Column(name="name",nullable=false)
	private String name;
	@Column(name="path",nullable=false)
	private String path;
	
	@Column(name="description",nullable=true)
	private String description;

	@Column(name="dateCreated",nullable=true)
	private Date dateCreated;

	
	public Workspace(){}
	public Workspace(String name, String path, String description, Date dateCreated) {
		super();
		this.name = name;
		this.description = description;
		this.dateCreated = dateCreated;
		this.path = path;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
}


