package rocketseat.com.pass_in.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.com.pass_in.domain.checkin.CheckIn;

import java.util.Optional;

public interface CheckinRepository extends JpaRepository<CheckIn, Integer> {
    Optional<CheckIn> findByAttendeeId(String attendeeId);
}
