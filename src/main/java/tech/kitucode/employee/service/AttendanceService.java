package tech.kitucode.employee.service;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.kitucode.employee.model.Attendance;
import tech.kitucode.employee.model.User;
import tech.kitucode.employee.model.enumerations.Status;
import tech.kitucode.employee.repository.AttendanceRepository;
import tech.kitucode.employee.service.dto.AttendanceDTO;
import tech.kitucode.employee.service.dto.WorkingHoursDTO;
import tech.kitucode.employee.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class AttendanceService {
    private final AttendanceRepository attendanceRepository;
    private final UserService userService;

    public AttendanceService(AttendanceRepository attendanceRepository, UserService userService) {
        this.attendanceRepository = attendanceRepository;
        this.userService = userService;
    }

    public AttendanceDTO report(String idNumber) {
        User user = userService.findByIdNumber(idNumber);

        Attendance attendance = null;
        if (user != null) {
            List<Attendance> attendanceList = attendanceRepository.findByUserIdAndDate(user.getId(), LocalDate.now());
            if (attendanceList.isEmpty()) {
                attendance = new Attendance();
                attendance.setStatus(Status.CHECK_IN);
                attendance.setUserId(user.getId());
                attendance.setCheckInTime(LocalTime.now());
                attendance.setDate(LocalDate.now());
            } else {
                Attendance lastAttendance = attendanceList.getLast();
                Status status = Status.CHECK_IN;
                if (lastAttendance.getStatus() == Status.CHECK_IN) {
                    status = Status.CHECK_OUT;
                }

                attendance = new Attendance();
                attendance.setStatus(status);
                attendance.setUserId(user.getId());
                if (status == Status.CHECK_IN) {
                    attendance.setCheckInTime(LocalTime.now());
                } else {
                    attendance.setCheckOutTime(LocalTime.now());
                }
                attendance.setDate(LocalDate.now());
            }
        }

        attendance = attendanceRepository.save(attendance);

        AttendanceDTO attendanceDTO = new AttendanceDTO(attendance);
        attendanceDTO.setUser(user);

        return attendanceDTO;
    }

    public Page<AttendanceDTO> filter(String idNumber, Status status, LocalDate date, Pageable pageable) {

        Long userId = null;
        if (idNumber != null) {
            User user = userService.findByIdNumber(idNumber);
            if (user != null) {
                userId = user.getId();
            }
        }

        Attendance probe = createProbe(userId, status, date);

        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreNullValues();
        Example<Attendance> example = Example.of(probe, exampleMatcher);

        return attendanceRepository.findAll(example, pageable).map(attendance -> {
            AttendanceDTO attendanceDTO = new AttendanceDTO(attendance);
            User user = userService.findByID(attendance.getUserId());
            attendanceDTO.setUser(user);
            WorkingHoursDTO workingHoursDTO = resolveWorkingHours(attendance);
            attendanceDTO.setTotalOvertimeHours(TimeUtil.convertMinutesToHourString(workingHoursDTO.getOvertimeHours()));
            attendanceDTO.setTotalWorkingHours(TimeUtil.convertMinutesToHourString(workingHoursDTO.getWorkingHours()));
            return attendanceDTO;
        });
    }

    private Attendance createProbe(Long userId, Status status, LocalDate date) {
        Attendance attendance = new Attendance();

        if (userId != null) {
            attendance.setUserId(userId);
        }

        if (status != null) {
            attendance.setStatus(status);
        }

        if (date != null) {
            attendance.setDate(date);
        }

        return attendance;
    }

    public WorkingHoursDTO findWorkingAndOvertimeHours(Long userId) {
        long workingMinutes = 0L;
        long overtimeMinutes = 0L;

        List<Attendance> attendancesByUser = attendanceRepository.findByUserIdAndStatus(userId, Status.CHECK_OUT);
        for (Attendance attendance : attendancesByUser) {
            WorkingHoursDTO workingHoursDTO = resolveWorkingHours(attendance);
            workingMinutes += workingHoursDTO.getWorkingHours();
            overtimeMinutes += workingHoursDTO.getOvertimeHours();
        }

        return new WorkingHoursDTO(workingMinutes, overtimeMinutes);
    }

    public WorkingHoursDTO resolveWorkingHours(Attendance attendance) {
        WorkingHoursDTO workingHoursDTO = new WorkingHoursDTO();
        LocalTime checkOutTime = attendance.getCheckOutTime();
        LocalTime checkInTime = attendance.getCheckInTime();
        LocalTime expectedCheckOutTime = LocalTime.of(17, 0, 0);
        if (checkInTime != null && checkOutTime != null) {
            long timeDiff = ChronoUnit.MINUTES.between(checkInTime, checkOutTime);
            workingHoursDTO.setWorkingHours(timeDiff);
        }

        if (checkOutTime != null) {
            if (checkOutTime.isAfter(expectedCheckOutTime)) {
                long timeDiff = ChronoUnit.MINUTES.between(expectedCheckOutTime, checkOutTime);
                workingHoursDTO.setOvertimeHours(timeDiff);
            }
        }

        return workingHoursDTO;
    }
}
