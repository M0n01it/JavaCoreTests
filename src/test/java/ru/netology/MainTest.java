package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.io.File;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    public void testParseCSV_validData_success() throws IOException {
        // given:
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv"; //

        // when:
        List<Employee> employees = Main.parseCSV(columnMapping, fileName);

        // then:
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());

        Employee employee1 = employees.get(0);
        Assertions.assertEquals(1, employee1.id);
        Assertions.assertEquals("John", employee1.firstName);
        Assertions.assertEquals("Smith", employee1.lastName);
        Assertions.assertEquals("USA", employee1.country);
        Assertions.assertEquals(25, employee1.age);
    }

    @Test
    public void testParseXML_validData_success() {
        // given:
        String fileName = "data.xml"; // Этот файл должен существовать в корне проекта для тестирования

        // when:
        List<Employee> employees = Main.parseXML(fileName);

        // then:
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());

        Employee employee1 = employees.get(0);
        Assertions.assertEquals(1, employee1.id);
        Assertions.assertEquals("John", employee1.firstName);
        Assertions.assertEquals("Smith", employee1.lastName);
        Assertions.assertEquals("USA", employee1.country);
        Assertions.assertEquals(25, employee1.age);
    }

    @Test
    public void testListToJson_validList_success() {
        // given:
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "Russia", 30)
        );

        // when:
        String json = Main.listToJson(employees);

        // then:
        Assertions.assertNotNull(json);
        Assertions.assertTrue(json.contains("\"id\": 1"));
        Assertions.assertTrue(json.contains("\"firstName\": \"John\""));
        Assertions.assertTrue(json.contains("\"lastName\": \"Smith\""));
        Assertions.assertTrue(json.contains("\"country\": \"USA\""));
        Assertions.assertTrue(json.contains("\"age\": 25"));
    }

    @Test
    public void testWriteString_validJson_success() throws IOException {
        // given:
        String jsonString = "{\"id\": 1, \"firstName\": \"John\", \"lastName\": \"Smith\", \"country\": \"USA\", \"age\": 25}";
        String fileName = "test_output.json";

        // when:
        Main.writeString(jsonString, fileName);

        // then:
        File file = new File(fileName);
        Assertions.assertTrue(file.exists());
        Assertions.assertTrue(file.length() > 0);

        // clean up:
        file.delete();
    }
}