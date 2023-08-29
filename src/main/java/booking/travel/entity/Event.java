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
@Table(name = "Event")
public class Event implements Serializable{

	@Id
	@GeneratedValue(generator = "id_event")
	@GenericGenerator(name = "id_event",
	strategy = "booking.travel.generator.EventGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "event_id")
	private String eventId;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy="event")
	List<EventPost> eventPosts;
}
