package example.demo.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DeviceAssignment {
    private Long id;
    private Employee employee;
    private Device device;
    private String requestCode;
    private LocalDate issueDate;
    private LocalDate returnDate;
}