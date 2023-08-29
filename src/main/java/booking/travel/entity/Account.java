package booking.travel.entity;

import java.beans.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
@Table(name = "Account")
public class Account implements Serializable{

	@Id
	@Column(columnDefinition = "varchar(150)")
	private String username;
	
	@Column(columnDefinition = "nvarchar(150)")
	private String fullname;
	
	@Column(columnDefinition = "nvarchar(150)", name = "name_admin")
	private String nameAdmin;
	
	@Column(columnDefinition = "varchar(64)")
	private String password;
	
	@Column(columnDefinition = "nvarchar(150)")
	private String email;
	
	@Column(columnDefinition = "varchar(15)")
	private String phone;
	
	
	@Column(columnDefinition = "nvarchar(100)")
	private String image;
	
	@Column(columnDefinition = "nvarchar(30)")
	private String gender;
	
	@Column(columnDefinition = "nvarchar(100)")
	private String address;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(columnDefinition = "datetime", name = "create_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(columnDefinition = "date", name = "birth_day")
	private Date birthDay = new Date();
	
	@Column(columnDefinition = "varchar(15)", name = "tax_code")
	private String taxCode;
	private Boolean enabled;
	
	@Column(columnDefinition = "varchar(64)", name = "verification_code")
	private String verificationCode;
	
	@Column(columnDefinition = "varchar(50)", name = "reset_password_token")
	private String resetPasswordToken;
	
	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<TourPost> tourPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<EventPost> eventPosts;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<OrderTour> orderTour;
	
	@JsonIgnore
	@OneToMany(mappedBy = "account")
	private List<OrderEvent> orderEvent;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "authorities",
				joinColumns = @JoinColumn(name = "username"),
				inverseJoinColumns = @JoinColumn(name = "role_id")
				)
	private Set<Role> roles = new HashSet<>();
	
	//CÓ PHƯƠNG THỨC NÀY ĐỂ KÍCH HOẠT TÀI KHOẢN
	public boolean isEnabled() {
		return enabled;
	}
	
	//HÀM ĐỂ THÊM QUYỀN (ROLE) MẶC ĐỊNH
	public void addRole(Role role) {
		this.roles.add(role);
	}

	public Account(String username) {
		this.username = username;
	}
	
	@Transient
	public String getFolder() {
		if(this.getUsername() == null) {
			return "";
		}
		return Integer.toHexString(this.getUsername().hashCode());
	}

}
