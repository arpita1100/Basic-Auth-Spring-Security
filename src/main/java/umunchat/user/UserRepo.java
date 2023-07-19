package umunchat.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<CustomUser,Long> {
    CustomUser findByUsername(String username);
}
