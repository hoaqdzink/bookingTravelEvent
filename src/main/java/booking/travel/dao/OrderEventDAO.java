package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.OrderEvent;

public interface OrderEventDAO extends JpaRepository<OrderEvent, String>{

	///------------------------------PHẦN DÀNH CHO DOANH NGHIỆP-----------------------------------////
	////ĐỔ CÁC ĐƠN CHƯA DUYỆT VÀ SẮP XẾP THEO MỚI TỚI CŨ
	@Query("SELECT o FROM OrderEvent o "
			+ "WHERE (o.eventPost.account.username = ?1) AND (o.status = 'CHUA') "
			+ "ORDER BY o.createDate DESC")
	List<OrderEvent> findOrderEventByWait(String username);

	///ĐỔ CÁC ĐƠN ĐÃ DUYỆT HOẶC HỦY VÀ SẮP XẾP MỚI TỚI CŨ
	@Query("SELECT o FROM OrderEvent o "
			+ "WHERE (o.eventPost.account.username = ?1) AND (o.status = 'HUY' OR o.status = 'DUYET') "
			+ "ORDER BY o.createDate DESC")
	List<OrderEvent> findOrderEventByDuyet(String username);
	
	///TÌM KIẾM CÁC ĐƠN EVENT
	@Query("SELECT o FROM OrderEvent o "
			+ "WHERE (o.eventPost.account.username = ?1) AND CONCAT(o.account.phone, o.account.username, o.account.fullname, o.eventPost.name, o.orderEventId) "
			+ "LIKE %?2%")
	List<OrderEvent> serchOrderEvent(String username, String keyword);

	///ĐỔ CÁC ĐƠN CHƯA DUYỆT THEO TÀI KHOẢN VÀ SẮP XẾP MỚI TỚI CŨ
	@Query("SELECT o FROM OrderEvent o "
			+ "WHERE o.status = 'CHUA' AND o.account.username = ?1 "
			+ "ORDER BY o.createDate DESC")
	List<OrderEvent> findAllByUsernameWithStatusWait(String username);

	////ĐỔ CÁC ĐƠN ĐÃ DUYỆT THEO TÀI KHOẢN VÀ SẮP XẾP TỪ MỚI TỚI CŨ
	@Query("SELECT o FROM OrderEvent o "
			+ "WHERE o.status = 'DUYET' AND o.account.username = ?1 "
			+ "ORDER BY o.createDate DESC")
	List<OrderEvent> findAllByUsernameWithStatusFinal(String username);

}
