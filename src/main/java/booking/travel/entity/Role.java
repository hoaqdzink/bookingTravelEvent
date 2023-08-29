package booking.travel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Data
@Table(name = "Roles")
public class Role implements Serializable{

	@Id
	@Column(columnDefinition = "varchar(10)", name="role_id")
	private String roleId;

	@Column(columnDefinition = "nvarchar(100)")
	private String name;

	///CÓ PHƯƠNG THỨC NÀY THÌ KHI ĐỔ LIST ACCOUNT LÊN TABLE CHỖ QUYỀN HẠN SẼ HIỂN THỊ RÕ RÀNG
	@Override
	public String toString() {
		return this.name;
	}
}
