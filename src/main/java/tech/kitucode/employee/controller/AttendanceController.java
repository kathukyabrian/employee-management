package tech.kitucode.employee.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.kitucode.employee.model.Attendance;
import tech.kitucode.employee.model.enumerations.Status;
import tech.kitucode.employee.service.AttendanceService;
import tech.kitucode.employee.service.dto.AttendanceDTO;
import tech.kitucode.employee.service.dto.AttendanceReportDTO;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AttendanceController {
    private final AttendanceService attendanceService;

    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @PostMapping("/report")
    public ResponseEntity<AttendanceDTO> report(@RequestBody AttendanceReportDTO attendanceReportDTO) {
        AttendanceDTO attendance = attendanceService.report(attendanceReportDTO.getIdNumber());

        return ResponseEntity.ok(attendance);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<AttendanceDTO>> filter(@RequestParam(required = false, name = "idNumber") String idNumber,
                                                   @RequestParam(required = false, name = "status") String status,
                                                   @RequestParam(required = false, name = "date") LocalDate date,
                                                   Pageable pageable) {

        Status statusEnumValue = null;
        try{
            statusEnumValue = Status.valueOf(status);
        }catch (Exception igore){

        }

        Page<AttendanceDTO> attendanceList = attendanceService.filter(idNumber, statusEnumValue,date, pageable);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Total-Count", Long.toString(attendanceList.getTotalElements()));

        return ResponseEntity.ok().headers(headers).body(attendanceList.getContent());
    }
}
