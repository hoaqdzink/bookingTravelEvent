package booking.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.Region;

public interface RegionDAO extends JpaRepository<Region, String>{

}
