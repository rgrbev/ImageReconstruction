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
@Table(name = "image")
public class Image {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "namePath", nullable = false)
	private String namePath;
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "width", nullable = false)
	private int width;

	@Column(name = "height", nullable = false)
	private int height;

	@Column(name = "size", nullable = false)
	private double size;

	@ManyToOne
	@JoinColumn(name = "idWorkspace")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Workspace workspace;

	public Image() {
	}

	public Image(String namePath, String name, int width, int height, double size, Workspace workspace) {
		super();
		this.namePath = namePath;
		this.width = width;
		this.height = height;
		this.size = size;
		this.workspace = workspace;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getSize() {
		return String.format("%.2f MB",size);
	}

	public void setSize(double size) {
		this.size = size;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
