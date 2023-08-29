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
@Table(name = "Tranport")
public class Tranport implements Serializable{

	@Id
	@GeneratedValue(generator = "id_tranport")
	@GenericGenerator(name = "id_tranport",
	strategy = "booking.travel.generator.TranportGenerateId")
	@Column(columnDefinition = "varchar(10)", name="tranport_id")
	private String tranportId;

	@Column(columnDefinition = "nvarchar(100)")
	private String name;

	@JsonIgnore
	@OneToMany(mappedBy="tranport")
	private List<TourPost> tourPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy="tranport")
	private List<EventPost> eventPosts;
}
