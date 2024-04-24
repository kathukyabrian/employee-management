package tech.kitucode.employee.service.dto;

import tech.kitucode.employee.model.User;
import tech.kitucode.employee.model.enumerations.Gender;
import tech.kitucode.employee.model.enumerations.Role;

public class UserDTO {
    private Long id;

    private String name;

    private String idNumber;

    private String phoneNumber;

    private String email;

    private Role role;

    private Gender gender;

    private String password;

    private String department;

    private String totalWorkingHours;

    private String totalOvertimeHours;

    public UserDTO() {
    }

    public UserDTO(User user, String totalWorkingHours, String totalOvertimeHours) {
        this.id = user.getId();
        this.name = user.getName();
        this.idNumber = user.getIdNumber();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.gender = user.getGender();
        this.password = user.getPassword();
        this.department = user.getDepartment();
        this.totalWorkingHours = totalWorkingHours;
        this.totalOvertimeHours = totalOvertimeHours;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", gender=" + gender +
                ", password='" + password + '\'' +
                ", department='" + department + '\'' +
                ", totalWorkingHours=" + totalWorkingHours +
                ", totalOvertimeHours=" + totalOvertimeHours +
                '}';
    }
}
