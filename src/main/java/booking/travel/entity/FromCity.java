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
@Table(name = "from_city")
public class FromCity implements Serializable{

	@Id
	@GeneratedValue(generator = "id_from_city")
	@GenericGenerator(name = "id_from_city",
	strategy = "booking.travel.generator.FromCityGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "from_city_id")
	private String fromCityId;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "fromCity")
	private List<EventPost> eventPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "fromCity")
	private List<TourPost> tourPosts;
}
