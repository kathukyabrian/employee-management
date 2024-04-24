package tech.kitucode.employee.service.dto;

public class AttendanceReportDTO {
    private String idNumber;

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public String toString() {
        return "AttendanceReportDTO{" +
                "idNumber='" + idNumber + '\'' +
                '}';
    }
}
