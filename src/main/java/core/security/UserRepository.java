package core.security;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<CustomUser, Long> {
    Optional<CustomUser> findByLogin(String login);
}
