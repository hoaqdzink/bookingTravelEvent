package booking.travel.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "Type")
public class Type implements Serializable{

	@Id
	@GeneratedValue(generator = "id_type")
	@GenericGenerator(name = "id_type",
	strategy = "booking.travel.generator.TypeGenerateId")
	@Column(columnDefinition = "varchar(10)", name="type_id")
	private String typeId;

	@Column(columnDefinition = "nvarchar(100)", name = "main_name")
	private String mainName;

	@Column(columnDefinition = "nvarchar(100)", name = "extra_name")
	private String extraName;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String logo;
	
	@JsonIgnore
	@OneToMany(mappedBy="type")
	private List<TourPost> tourPosts;
	
	@Transient
	public String getFolder() {
		if(this.getTypeId() == null) {
			return "";
		}
		return this.getTypeId();
	}
}
