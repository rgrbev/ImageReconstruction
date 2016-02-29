package netcetera.finki.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "keypoints")
public class Keypoints {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id", nullable = false)
	private long id;

	@Column( name = "vectors" )
	@Lob
	private String vectors;
	
	@Column( name = "vectorsNum" )
	private String vectorsNum;
	
	@Column( name = "vectorSize" )
	private String vectorSize;
	
	@ManyToOne
	@JoinColumn(name = "idImage")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Image idImage;

	public Keypoints(){}
	
	
	public Keypoints(String vectors, String vectorsNum, String vectorSize, Image idImage) {
		super();
		this.vectors = vectors;
		this.vectorsNum = vectorsNum;
		this.vectorSize = vectorSize;
		this.idImage = idImage;
	}


	public long getId() {
		return id;
	}

	public String getVectorsNum() {
		return vectorsNum;
	}

	public void setVectorsNum(String vectorsNum) {
		this.vectorsNum = vectorsNum;
	}

	public String getVectorSize() {
		return vectorSize;
	}

	public void setVectorSize(String vectorSize) {
		this.vectorSize = vectorSize;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getVectors() {
		return vectors;
	}

	public void setVectors(String vectors) {
		this.vectors = vectors;
	}

	public Image getIdImage() {
		return idImage;
	}

	public void setIdImage(Image idImage) {
		this.idImage = idImage;
	}

	
}
