package example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class DeviceAssignmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employee;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceEntity device;

    @Column(name = "request_code", nullable = false)
    private String requestCode;

    @CreationTimestamp
    @Column(name = "issue_date", nullable = false)
    private LocalDate issueDate;

    @Column(name = "return_date")
    private LocalDate returnDate;
}