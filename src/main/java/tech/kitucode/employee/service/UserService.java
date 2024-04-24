package tech.kitucode.employee.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import tech.kitucode.employee.model.User;
import tech.kitucode.employee.repository.UserRepository;
import tech.kitucode.employee.service.dto.UserDTO;
import tech.kitucode.employee.service.dto.WorkingHoursDTO;
import tech.kitucode.employee.util.TimeUtil;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AttendanceService attendanceService;

    public UserService(UserRepository userRepository, @Lazy AttendanceService attendanceService) {
        this.userRepository = userRepository;
        this.attendanceService = attendanceService;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByIdNumber(String idNumber) {
        return userRepository.findOneByIdNumber(idNumber).orElse(null);
    }

    public User findByID(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<UserDTO> findAll() {

        return userRepository.findAll().stream().map(user -> {
            WorkingHoursDTO workingHoursDTO = attendanceService.findWorkingAndOvertimeHours(user.getId());

            return new UserDTO(
                    user,
                    TimeUtil.convertMinutesToHourString(workingHoursDTO.getWorkingHours()),
                    TimeUtil.convertMinutesToHourString(workingHoursDTO.getOvertimeHours()));
        }).collect(Collectors.toList());
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public boolean delete(String idNumber) {
        User user = findByIdNumber(idNumber);

        if (user != null) {
            userRepository.delete(user);
            return true;
        }

        return false;
    }

}
