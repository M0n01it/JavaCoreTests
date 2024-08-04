package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


public class MainTest {

    @Test
    public void testParseCSV_validData_success() throws IOException {
        // given:
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";

        // when:
        List<Employee> employees = Main.parseCSV(columnMapping, fileName);

        // then:
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());

        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)
        );

        Assertions.assertEquals(expectedEmployees, employees);
    }

    @Test
    public void testParseXML_validData_success() {
        // given:
        String fileName = "data.xml";

        // when:
        List<Employee> employees = Main.parseXML(fileName);

        // then:
        Assertions.assertNotNull(employees);
        Assertions.assertEquals(2, employees.size());

        List<Employee> expectedEmployees = Arrays.asList(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)
        );

        Assertions.assertEquals(expectedEmployees, employees);
    }

    @Test
    public void testListToJson_validList_success() {
        // given:
        List<Employee> employees = Arrays.asList(
                new Employee(1, "John", "Smith", "USA", 25),
                new Employee(2, "Ivan", "Petrov", "RU", 23)
        );

        // when:
        String json = Main.listToJson(employees);

        // then:
        String expectedJson = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";
        Assertions.assertEquals(expectedJson, json);
    }

    @Test
    public void testWriteString_validJson_success() throws IOException {
        // given:
        String jsonString = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Smith\",\"country\":\"USA\",\"age\":25}";
        String fileName = "test_output.json";

        // when:
        Main.writeString(jsonString, fileName);

        // then:
        File file = new File(fileName);
        Assertions.assertTrue(file.exists());

        // читаем содержимое файла
        String fileContent = new String(Files.readAllBytes(Paths.get(fileName)));

        Assertions.assertEquals(jsonString, fileContent);

        // clean up:
        file.delete();
    }
}