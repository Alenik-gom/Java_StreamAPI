package by.clevertec;

import by.clevertec.model.*;
import by.clevertec.util.Util;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class TestMain {
    @Test
    void task1() {
        List<Animal> animals = Util.getAnimals().stream().limit(10).collect(Collectors.toList());
        int expectedSum = 0;
        int actualSum = Main.task1(animals).size();
        assertEquals(expectedSum, actualSum, "В третьем зоопарке не должно быть животных ");
    }
    @Test
    void task3() {
        List<Animal> animals = Util.getAnimals();
        int expectedNumber = 1;
        long actualNumber  = Main.task3(animals).stream()
                .filter(s -> s.equals("Arabic"))
                .count();
        assertEquals(expectedNumber, actualNumber, "Страна Arabic должна встречаться только 1 раз");
    }
    @Test
    void task4() {
        List<Animal> animals = Util.getAnimals();
        long expectedNumber = 476;
        long actualNumber = Main.task4(animals);
        assertEquals(expectedNumber, actualNumber, "Количество особей пола Female: "+expectedNumber);
    }
    @Test
    void task6() {
        List<Animal> animals = Util.getAnimals();
        boolean actaulParametr = Main.task6(animals);
        assertEquals(false, actaulParametr, "Не все особи только Male и Female ");
    }
    @Test
    void task10() {
        List<Animal> animals = Util.getAnimals().stream().limit(10).collect(Collectors.toList());
        int expectedSum = 299;
        int actualSum = Main.task10(animals);
        assertEquals(expectedSum, actualSum, "Суммарный возраст всех животных должен быть равен " + expectedSum);
    }

    @Test
    void task11() {
        List<Animal> animals = new ArrayList<>();
        // передаем пустой список
        IllegalStateException thrown = assertThrows(
                IllegalStateException.class,
                () -> Main.task11(animals),
                "Должно выкидывать исключение IllegalStateException, если нет данных для расчета среднего возраста"
        );
        // Проверяем сообщение исключения
        assertEquals("Нет данных для расчета среднего возраста", thrown.getMessage());
    }
    @Test
    void task13() {
        List<House> houses = Util.getHouses();
        List<Person> personsHospital = houses.stream()
                .filter(house -> house.getBuildingType().equals("Hospital"))
                .map(house -> house.getPersonList())
                .flatMap(List::stream)
                .collect(Collectors.toList());
        List<Person> personsEvacuation = Main.task13(houses);
        // assumption: в госпиталях < 500 для корректной отработки теста
        // проверяем, все ли в госпитале в списке
        boolean allHospitalInEvacuation = personsEvacuation.containsAll(personsHospital);
        assertEquals(true, allHospitalInEvacuation, "В эвакуационном списке должны быть все больные: "
                + personsHospital.size());
    }
    @Test
    void task14() {
        // Перенаправление стандартного потока вывода для захвата вывода консоли
        final java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));
        Main.task14();
        String expectedOutput = "Общая выручка от транспортировки: 207290.71 $";
        assertEquals(expectedOutput, outContent.toString());
    }
    @Test
    void task15() {
        List<Flower> flowers = Util.getFlowers();
        int expectedSum = 575642;
        int actualSum = Main.task15(flowers);
        assertEquals(expectedSum, actualSum, "Общая стоимость обслуживания всех растений составляет: " + expectedSum);
    }

    @Test
    void task16() {
        List<Student> students = Util.getStudents();
        String expectedName = "Miller (17 years)";
        boolean contains = Main.task16(students).contains(expectedName);
        assertEquals(true, contains, "В списке младше 18 должен быть " + expectedName);
    }

    @Test
    void task17() {
        List<Student> students = Util.getStudents();
        int expectedNumber = 11;
        int actualNumber = Main.task17(students).size();
        assertEquals(expectedNumber, actualNumber, "Количество групп " + expectedNumber);
    }

    @Test
    void task19() {
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        String groupName = "P-10"; // Пример названия группы
        int expectedNumber = 0;
        int actualNumber = Main.task19(students, examinations, groupName).size();
        assertEquals(expectedNumber, actualNumber, "Группы P-10 не сущесвует, количество должно быть " + expectedNumber);
    }
}