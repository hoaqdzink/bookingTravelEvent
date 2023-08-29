package booking.travel.entity;

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
@Table(name = "Region")
public class Region implements Serializable{

	@Id
	@GeneratedValue(generator = "id_region")
	@GenericGenerator(name = "id_region",
	strategy = "booking.travel.generator.RegionGenerateId")
	
	@Column(columnDefinition = "varchar(10)", name = "region_id")
	private String regionId;

	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String icon;
	
	@JsonIgnore
	@OneToMany(mappedBy="region")
	private List<ToCity> toCities;
}
