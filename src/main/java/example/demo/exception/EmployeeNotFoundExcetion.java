package example.demo.exception;

public class EmployeeNotFoundExcetion extends RuntimeException {
    public EmployeeNotFoundExcetion(String message) {
        super(message);
    }
}
