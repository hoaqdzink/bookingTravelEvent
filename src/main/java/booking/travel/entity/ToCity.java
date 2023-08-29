package booking.travel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "to_city")
public class ToCity implements Serializable{

	@Id
	@GeneratedValue(generator = "id_to_city")
	@GenericGenerator(name = "id_to_city",
	strategy = "booking.travel.generator.ToCityGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "to_city_id")
	private String toCityId;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="region_id")
	private Region region;
	
	@JsonIgnore
	@OneToMany(mappedBy = "toCity")
	private List<EventPost> eventPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "toCity")
	private List<TourPost> tourPosts;
	
	public ToCity(String toCityId) {
		this.toCityId = toCityId;
	}
}
