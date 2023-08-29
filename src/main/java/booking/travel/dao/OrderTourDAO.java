package booking.travel.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import booking.travel.entity.OrderTour;

public interface OrderTourDAO extends JpaRepository<OrderTour, String>{
	
	////ĐỔ CÁC ĐƠN ĐÃ DUYỆT THEO TÀI KHOẢN VÀ SẮP XẾP TỪ MỚI TỚI CŨ
	@Query("SELECT o FROM OrderTour o "
			+ "WHERE o.status = 'DUYET' AND o.account.username = ?1 "
			+ "ORDER BY o.createDate DESC")
	List<OrderTour> findAllByUsernameWithStatusFinal(String username);
	
	///ĐỔ CÁC ĐƠN CHƯA DUYỆT THEO TÀI KHOẢN VÀ SẮP XẾP MỚI TỚI CŨ
	@Query("SELECT o FROM OrderTour o "
			+ "WHERE o.status = 'CHUA' AND o.account.username = ?1 "
			+ "ORDER BY o.createDate DESC")
	List<OrderTour> findAllByUsernameWithStatusWait(String username);
	
	///------------------------------PHẦN DÀNH CHO DOANH NGHIỆP-----------------------------------////
	////ĐỔ CÁC ĐƠN CHƯA DUYỆT VÀ SẮP XẾP THEO MỚI TỚI CŨ
	@Query("SELECT o FROM OrderTour o "
			+ "WHERE (o.tourPost.account.username = ?1) AND (o.status = 'CHUA') "
			+ "ORDER BY o.createDate DESC")
	List<OrderTour> findOrderWait(String username);
	
	///ĐỔ CÁC ĐƠN ĐÃ DUYỆT HOẶC HỦY VÀ SẮP XẾP MỚI TỚI CŨ
	@Query("SELECT o FROM OrderTour o "
			+ "WHERE (o.tourPost.account.username = ?1) AND (o.status = 'HUY' OR o.status = 'DUYET') "
			+ "ORDER BY o.createDate DESC")
	List<OrderTour> findOrderDuyet(String username);
	
	///TÌM KIẾM CÁC ĐƠN TOUR
	@Query("SELECT o FROM OrderTour o "
			+ "WHERE (o.tourPost.account.username = ?1) AND CONCAT(o.account.phone, o.account.username, o.account.fullname, o.tourPost.name, o.orderTourId) "
			+ "LIKE %?2%")
	List<OrderTour> searchAllOrderTour(String username, String keyword);
	
	
	
}
