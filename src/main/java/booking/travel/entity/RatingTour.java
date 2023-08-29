package booking.travel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RatingTour")
public class RatingTour implements Serializable{

	@Id
	@GeneratedValue(generator = "id_ratingtour")
	@GenericGenerator(name = "id_ratingtour",
	strategy = "booking.travel.generator.RatingTourGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "rating_tour_id")
	private String ratingTourId;

	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@Column(columnDefinition = "datetime", name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@Column(columnDefinition = "nvarchar(255)")
	private String comment;

	@Column(columnDefinition = "float", name = "num_stars")
	private Double numStars = 0.0;
	
	private Boolean status	= false;
	
	@Column(columnDefinition = "nvarchar(255)")
	private String reply;
	
	@ManyToOne
	@JoinColumn(name="order_tour_id")
	private OrderTour orderTour;
}
