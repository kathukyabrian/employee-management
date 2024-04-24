package tech.kitucode.employee.service.dto;

import tech.kitucode.employee.model.Attendance;
import tech.kitucode.employee.model.User;
import tech.kitucode.employee.model.enumerations.Status;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceDTO {
    private Long id;

    private User user;

    private Status status;

    private LocalTime checkInTime;

    private LocalTime checkOutTime;

    private LocalDate date;
    private String totalWorkingHours;
    private String totalOvertimeHours;

    public AttendanceDTO() {
    }

    public AttendanceDTO(Attendance attendance) {
        this.id = attendance.getId();
        this.status = attendance.getStatus();
        this.checkInTime = attendance.getCheckInTime();
        this.checkOutTime = attendance.getCheckOutTime();
        this.date = attendance.getDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalTime getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(LocalTime checkInTime) {
        this.checkInTime = checkInTime;
    }

    public LocalTime getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(LocalTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTotalWorkingHours() {
        return totalWorkingHours;
    }

    public void setTotalWorkingHours(String totalWorkingHours) {
        this.totalWorkingHours = totalWorkingHours;
    }

    public String getTotalOvertimeHours() {
        return totalOvertimeHours;
    }

    public void setTotalOvertimeHours(String totalOvertimeHours) {
        this.totalOvertimeHours = totalOvertimeHours;
    }

    @Override
    public String toString() {
        return "AttendanceDTO{" +
                "id=" + id +
                ", user=" + user +
                ", status=" + status +
                ", checkInTime=" + checkInTime +
                ", checkOutTime=" + checkOutTime +
                ", date=" + date +
                ", totalWorkingHours='" + totalWorkingHours + '\'' +
                ", totalOvertimeHours='" + totalOvertimeHours + '\'' +
                '}';
    }
}
