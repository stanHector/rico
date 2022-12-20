package service.ricotunes.giftcards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.ricotunes.giftcards.enums.RoleName;
import service.ricotunes.giftcards.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

//	Role findByName(RoleName name);
	Optional<Role> findByRoleName(RoleName name);
}
