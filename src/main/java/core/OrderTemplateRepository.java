package core;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderTemplateRepository extends CrudRepository<OrderTemplate, Long> {
}
