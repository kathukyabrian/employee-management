package tech.kitucode.employee.service.dto;

import lombok.Data;
import tech.kitucode.employee.model.enumerations.Status;

import java.time.LocalTime;

@Data
public class AttendanceMiniDTO {
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private Status status;
}