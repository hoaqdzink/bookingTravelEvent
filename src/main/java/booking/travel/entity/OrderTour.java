package booking.travel.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "OrderTour")
public class OrderTour implements Serializable{

	@Id
	@GeneratedValue(generator = "id_ordertour")
	@GenericGenerator(name = "id_ordertour",
	strategy = "booking.travel.generator.OrderTourGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "order_tour_id")
	private String orderTourId;

	@Column(columnDefinition = "varchar(30)")
	private String status;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition = "datetime", name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(columnDefinition = "date", name = "start_date")
	@Temporal(TemporalType.DATE)
	private Date startDate = new Date();
	
	@Column(name = "price_tour", columnDefinition = "float")
	private Double priceTour;
	
	@Column(name = "price_adult", columnDefinition = "float")
	private Double priceAdult;
	
	@Column(name = "price_children", columnDefinition = "float")
	private Double priceChildren;
	
	@Column(name = "price_baby", columnDefinition = "float")
	private Double priceBaby;
	
	@Column(name = "quantity_adult")
	private Integer quantityAdult = 0;

	@Column(name = "quantity_baby")
	private Integer quantityBaby = 0;

	@Column(name = "quantity_children")
	private Integer quantityChildren = 0;

	@Column(columnDefinition = "nvarchar(max)")
	private String note;

	@Column(columnDefinition = "varchar(30)")
	private String payment;

	@Column(columnDefinition = "float")
	private Double discount = 0.0;
	
	@ManyToOne
	@JoinColumn(name="username")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "tour_id")
	private TourPost tourPost;
	
	@JsonIgnore
	@OneToMany(mappedBy = "orderTour")
	private List<RatingTour> ratingTours;
	
	public OrderTour(String orderTourId) {
		this.orderTourId = orderTourId;
	}
	
	public Double getSumPrice() {
		return (priceTour + priceAdult*quantityAdult + priceChildren*quantityChildren + priceBaby*quantityBaby) - (priceTour + priceAdult*quantityAdult + priceChildren*quantityChildren + priceBaby*quantityBaby)*discount/100;
	}

}
