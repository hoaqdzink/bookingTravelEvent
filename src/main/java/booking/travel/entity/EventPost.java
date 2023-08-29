package booking.travel.entity;

import java.beans.Transient;
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

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "EventPosts")
public class EventPost implements Serializable{

	@Id
	@GeneratedValue(generator = "id_eventpost")
	@GenericGenerator(name = "id_eventpost",
	strategy = "booking.travel.generator.EventPostGenerateId")
	@Column(columnDefinition = "varchar(10)", name = "event_post_id")
	private String eventPostId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition = "datetime", name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@Column(columnDefinition = "nvarchar(100)")
	private String time;
	
	@Column(columnDefinition = "nvarchar(150)")
	private String name;
	
	@Column(columnDefinition = "float", name = "price_event")
	private Double priceEvent;
	
	@Column(name = "top_post")
	private Boolean topPost = false;
	
	@Column(columnDefinition = "float")
	private Double discount = 0.0;
	
	@Column(columnDefinition = "varchar(100)")
	private String status;
	
	@Column(name = "is_deleted")
	public Boolean isDeleted = false;
	
	@Column(columnDefinition = "float", name = "price_adult")
	private Double priceAdult;
	
	@Column(columnDefinition = "float", name = "price_children")
	private Double priceChildren;
	
	@Column(columnDefinition = "float", name = "price_baby")
	private Double priceBaby;
	
	@Column(columnDefinition = "nvarchar(max)", name = "description_event")
	private String descriptionEvent;
	
	@Column(columnDefinition = "nvarchar(250)", name = "primary_image")
	private String primaryImage;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image1")
	private String extraImage1;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image2")
	private String extraImage2;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image3")
	private String extraImage3;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image4")
	private String extraImage4;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image5")
	private String extraImage5;
	
	@Column(columnDefinition = "nvarchar(250)", name = "extra_image6")
	private String extraImage6;
	
	@ManyToOne
	@JoinColumn(name = "username")
	private Account account;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "to_city_id")
	private ToCity toCity;
	
	@ManyToOne
	@JoinColumn(name = "from_city_id")
	private FromCity fromCity;
	
	@ManyToOne
	@JoinColumn(name = "tranport_id")
	private Tranport tranport;
	
	@JsonIgnore
	@OneToMany(mappedBy="eventPost")
	private List<OrderEvent> orderEvent;
	
	@Transient
	public String getFolder() {
		if(this.getEventPostId() == null) {
			return "";
		}
		return this.getEventPostId();
	}
	
	public Double sumPriceEventDiscount() {
		return priceEvent - (priceEvent * discount / 100);
	}
}
