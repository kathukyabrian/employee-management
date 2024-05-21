package tech.kitucode.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tech.kitucode.employee.model.Attendance;
import tech.kitucode.employee.model.enumerations.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {
    Optional<Attendance> findOneByUserIdAndDate(Long userId, LocalDate date);

    List<Attendance> findByUserIdAndDate(Long userId, LocalDate date);
    List<Attendance> findByUserId(Long userId);
    List<Attendance> findByUserIdAndStatus(Long userId, Status status);
    List<Attendance> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
