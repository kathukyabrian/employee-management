package tech.kitucode.employee.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class AttendanceReport {
    private String name;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private LocalDate date;
    private String workingHours;
    private String overtimeHours;
    private List<AttendanceMiniDTO> attendances;
}
