package interfaces;

import com.sirma.finalexam.model.Employee;

import java.util.List;

public interface Reader {
    List<Employee> read(List<Employee> items, String path);
}
