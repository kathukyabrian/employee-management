package tech.kitucode.employee.service.dto;

public class WorkingHoursDTO {
    private long workingHours;
    private long overtimeHours;

    public WorkingHoursDTO() {
    }

    public WorkingHoursDTO(long workingHours, long overtimeHours) {
        this.workingHours = workingHours;
        this.overtimeHours = overtimeHours;
    }

    public long getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(long workingHours) {
        this.workingHours = workingHours;
    }

    public long getOvertimeHours() {
        return overtimeHours;
    }

    public void setOvertimeHours(long overtimeHours) {
        this.overtimeHours = overtimeHours;
    }

    @Override
    public String toString() {
        return "WorkingHoursDTO{" +
                "workingHours=" + workingHours +
                ", overtimeHours=" + overtimeHours +
                '}';
    }
}
