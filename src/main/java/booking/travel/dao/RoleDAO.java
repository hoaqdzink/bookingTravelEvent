package booking.travel.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import booking.travel.entity.Role;

public interface RoleDAO extends JpaRepository<Role, String>{
}
