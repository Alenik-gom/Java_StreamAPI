package by.clevertec;

import by.clevertec.model.Animal;
import by.clevertec.model.Car;
import by.clevertec.model.Examination;
import by.clevertec.model.Flower;
import by.clevertec.model.House;
import by.clevertec.model.Person;
import by.clevertec.model.Student;
import by.clevertec.util.Util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Main {
    private static final String BUILDING_TYPE_HOSPITAL = "Hospital";
    private final static int AGE_10 = 10;
    private final static int AGE_20 = 20;
    private final static int AGE_YOUNG = 18;
    private final static int AGE_MAX_CADETS = 27;
    private final static int AGE_PENSION = 65;
    private final static String ECHELON_1 = "1.Туркменистан";
    private final static String ECHELON_2 = "2.Узбекистан";
    private final static String ECHELON_3 = "3.Казахстан";
    private final static String ECHELON_4 = "4.Кыргызстан";
    private final static String ECHELON_5 = "5.Россия";
    private final static String ECHELON_6 = "6.Монголия";
    private final static String ECHELON_7 = "7.Выброс";


    public static void main(String[] args) {
        List<Animal> animals = Util.getAnimals();
        //tasks
        List<Animal> animalsThirdZoo = task1(animals);
        task2();
        List<String> countiesA = task3(animals);
        long countFemales = task4(animals);
        task5();
        boolean allMalesFemales = task6(animals);
        task7();
        task8();
        task9();
        int result10 = task10(animals);
        double result11 = task11(animals);
        task12();
        task13(Util.getHouses());
        task14();
        int budget = task15(Util.getFlowers());
        task16(Util.getStudents());
        List<String> groups = task17(Util.getStudents());
        task18();
        List<String> students = task19(Util.getStudents(), Util.getExaminations(), "P-1");
        task20();
        task21();
        task22();
    }

    public static List<Animal> task1(List<Animal> animals) {
        //Из представленных животных отобрать все молодые особи от 10 до 20 лет и
        // отсортировать по возрасту (по возрастанию) далее - распределить по 7 на каждый
        // зоопарк. Зоопарков неограниченное кол-во а вы - директор 3-го по счёту зоопарка.
        // Полученных животных вывести в консоль.
        List<List<Animal>> zoos = Stream.iterate(0, n -> n + 1).limit((animals.size() + 6) / 7)  // определение количества необходимых зоопарков
                .map(n -> animals.stream()
                        .filter(animal -> animal.getAge() >= AGE_10
                                && animal.getAge() <= AGE_20)
                        .sorted((a1, a2) -> a1.getAge() - a2.getAge())
                        .skip(n * 7).limit(7)
                        .collect(Collectors.toList()))
                .filter(l -> !l.isEmpty())
                .collect(Collectors.toList());

        // Вывод животных из третьего зоопарка
        List<Animal> thirdZooAnimals = new ArrayList<>();
        System.out.println("Task_1");
        if (zoos.size() >= 3) {
            thirdZooAnimals = zoos.get(2); // Индекс 2, потому что индексация начинается с нуля
            thirdZooAnimals.forEach(System.out::println);
        } else {
            System.out.println("В третьем зоопарке нет животных.");
        }
        return thirdZooAnimals;
    }

    public static void task2() {
        //Отобрать всех животных из Японии (Japanese) и записать породу UPPER_CASE в
        // если пол Female преобразовать к строкам породы животных и вывести в консоль
        List<Animal> animals = Util.getAnimals();
        List<String> breeds = animals.stream()
                .filter(animal -> "Japanese".equals(animal.getOrigin()))
                .map(animal -> {
                    // Преобразуем породу в UPPER CASE, если пол - Female
                    if ("Female".equals(animal.getGender())) {
                        return animal.getBread().toUpperCase();
                    }
                    return animal.getBread();
                }).toList();
        System.out.println("Task_2");
        // Вывод результатов
        breeds.forEach(System.out::println);
    }

    public static List<String> task3(List<Animal> animals) {
        //Отобрать всех животных старше 30 лет и вывести все страны происхождения
        // без дубликатов начинающиеся на "A"
        List<String> uniqueCountries = animals.stream()
                .filter(animal -> animal.getAge() >= 30)
                .map(Animal::getOrigin)
                .filter(country -> country.startsWith("A"))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Task_3");
        uniqueCountries.forEach(System.out::println);
        return uniqueCountries;
    }

    public static long task4(List<Animal> animals) {
        //Подсчитать количество всех животных пола = Female. Вывести в консоль
        long countFemales = animals.stream()
                .filter(animal -> "Female".equals(animal.getGender()))
                .count();
        System.out.println("Task_4");
        System.out.println("The number of Females: " + countFemales);
        return countFemales;
    }

    public static void task5() {
        //Взять всех животных возрастом 20 - 30 лет. Есть ли среди нах хоть один
        // из страны Венгрия (Hungarian)? Ответ вывести в консоль
        List<Animal> animals = Util.getAnimals();
        boolean anyHungarian = animals.stream()
                .filter(animal -> animal.getAge() >= AGE_10 && animal.getAge() <= AGE_20)
                .anyMatch(animal -> "Hungarian".equals(animal.getOrigin()));
        System.out.println("Task_5");
        if (anyHungarian) {
            System.out.println("Среди животных 20-30 лет есть хотя бы одно животное из Венгрии.");
        } else {
            System.out.println("Среди животных 20-30 лет нет животных из Венгрии.");
        }
    }

    public static boolean task6(List<Animal> animals) {
        // Взять всех животных. Все ли они пола Male и Female ? Ответ вывести в консоль
        boolean allMatch = animals.stream()
                .allMatch(animal -> "Male".equals(animal.getGender()) || "Female".equals(animal.getGender()));
        System.out.println("\n Task_6");
        if (allMatch) {
            System.out.println("Все животные имеют пол Male или Female.");
        } else {
            System.out.println("Не все животные имеют пол Male или Female.");
        }
        return allMatch;
    }

    public static void task7() {
        // Взять всех животных. Узнать что ни одно из них не имеет страну происхождения
        // Oceania. Ответ вывести в консоль
        List<Animal> animals = Util.getAnimals();
        boolean noneMatch = animals
                .stream()
                .noneMatch(animal -> "Oceania".equals(animal.getOrigin()));
        System.out.println("\n Task_7");
        if (noneMatch) {
            System.out.println("Ни одно животное не происходит из Oceania.");
        } else {
            System.out.println("Есть животные, которые происходят из Oceania.");
        }
    }

    public static void task8() {
        // Взять всех животных. Отсортировать их породу в стандартном порядке и
        // взять первые 100. Вывести в консоль возраст самого старого животного
        List<Animal> animals = Util.getAnimals();
        Optional<Integer> maxAge = animals.stream()
                .sorted(Comparator.comparing(Animal::getBread))
                .limit(100)
                .max(Comparator.comparing(Animal::getAge))
                .map(Animal::getAge);
        System.out.println("\n Task_8");
        maxAge.ifPresent(age -> System.out.println("Возраст самого старого животного из первых 100 по породе: " + age));
    }

    public static void task9() {
        //Взять всех животных. Преобразовать их в породы, а породы в []char
        // Вывести в консоль длину самого короткого массива
        List<Animal> animals = Util.getAnimals();
        OptionalInt minLength = animals.stream()
                .map(animal -> animal.getBread().toCharArray())
                .mapToInt(chars -> chars.length)
                .min();
        System.out.println("\n Task_9");
        minLength.ifPresent(min -> System.out.println("Длина самого короткого массива символов из пород: " + min));
    }

    public static int task10(List<Animal> animals) {
        //Взять всех животных. Подсчитать суммарный возраст всех животных.
        // Вывести результат в консоль
        int sum = animals.stream().mapToInt(Animal::getAge).sum();
        System.out.println("\n Task_10");
        System.out.println("Суммарный возраст всех животных: " + sum);
        return sum;
    }

    public static double task11(List<Animal> animals) {
        //Взять всех животных. Подсчитать средний возраст всех животных из
        // индонезии (Indonesian). Вывести результат в консоль
        System.out.println("\n Task_11");
        double average = animals.stream()
                .filter(animal -> "Indonesian".equals(animal.getOrigin()))
                .mapToInt(Animal::getAge)
                .average()
                .orElseThrow(() -> new IllegalStateException("Нет данных для расчета среднего возраста"));
        System.out.println("Средний возраст животных из Индонезии: " + average);
        return average;
    }

    public static void task12() {
        // Во Французский легион принимают людей со всего света, но есть отбор по полу (только мужчины)
        // возраст от 18 до 27 лет. Преимущество отдаётся людям военной категории 1, на втором месте -
        // военная категория 2, и на третьем месте военная категория 3.
        // Отсортировать всех подходящих кандидатов в порядке их приоритета по военной категории.
        // Однако взять на обучение академия может только 200 человек. Вывести их в консоль.
        List<Person> persons = Util.getPersons();
        List<Person> personLegion = persons.stream()
                .filter(person -> "Male".equals(person.getGender())
                        && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() >= AGE_YOUNG
                        && Period.between(person.getDateOfBirth(), LocalDate.now()).getYears() <= AGE_MAX_CADETS)
                .sorted(Comparator.comparingInt(Person::getRecruitmentGroup))
                .limit(200)
                .toList();
        System.out.println("\n Task_12");
        personLegion.forEach(System.out::println);
    }

    public static List<Person> task13(List<House> houses) {
        //Надвигается цунами и в районе эвакуации требуется в первую очередь обойти дома и
        // эвакуировать больных и раненых (из Hospital), во вторую очередь детей и стариков
        // (до 18 и пенсионного возраста) а после всех остальных. В первый этап эвакуации
        // мест в эвакуационном транспорте только 500. Вывести всех людей попадающих в
        // первый этап эвакуации в порядке приоритета (в консоль).

        List<Person> evacuationList = houses.stream()
                .flatMap(house -> house.getPersonList().stream()
                        .map(person -> new AbstractMap.SimpleEntry<>(person,
                                calculatePriority(house.getBuildingType(), person.getDateOfBirth()))))
                .sorted(Comparator.comparingInt(AbstractMap.SimpleEntry::getValue))
                .map(AbstractMap.SimpleEntry::getKey)
                .limit(500)
                .toList();
        System.out.println("\n Task_13");
        evacuationList.forEach(System.out::println);
        return evacuationList;
    }

    public static void task14() {
        //1.Туркменистан - Все автомобили марки "Jaguar" а так же все авто цвета White
        // 2.Узбекистан - Из оставшихся все автомобили с массой до 1500 кг и марок BMW, Lexus, Chrysler и Toyota
        // 3.Казахстан - Из оставшихся все автомобили Черного цвета с массой более 4000 кг и все GMC и Dodge
        // 4.Кыргызстан - з оставшихся все автомобили года выпуска до 1982 или все модели "Civic" и "Cherokee"
        // 5.Россия - Из оставшихся все автомобили цветов НЕ Yellow, Red, Green и Blue или же по стоимости дороже 40000
        // 6.Монголия - Из оставшиеся все автомобили в vin номере которых есть цифра "59"
        // Оставшиеся автомобили отбрасываем, они никуда не идут.
        // Измерить суммарные массы автомобилей всех эшелонов для каждой из стран и подсчитать
        // сколько для каждой страны будет стоить транспортные расходы если учесть что на 1
        // тонну транспорта будет потрачено 7.14 $. Вывести суммарные стоимости в консоль.
        // Вывести общую выручку логистической кампании.
        List<Car> cars = Util.getCars();
        Map<String, Integer> echelons = cars.stream()
                .collect(Collectors.groupingBy(
                        Main::determineEchelon, Collectors.summingInt(Car::getPrice)
                ))
                .entrySet()
                .stream()
                .filter(c -> !Objects.equals(c.getKey(), ECHELON_7))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> calculateTransportCosts(entry.getValue()) // Вычисляем стоимость транспортировки
                ));
        // echelons.forEach((key, value) ->
        //       System.out.printf("\n" + key + ", Стоимость транспортировки: " + toMoney(value)));
        int revenue = echelons.entrySet().stream().mapToInt(Map.Entry::getValue).sum();
        System.out.printf("Общая выручка от транспортировки: " + toMoney(revenue));
    }

    public static int task15(List<Flower> flowers) {
        //Отсортировать их по странам происхождения в обратном порядке После по стоимости и
        // еще по водопотреблению в обратном порядке. Из этого списка взять растения название
        // которых от буквы "S" до буквы "C". Если растения тенелюбивые и им подходит горшок
        // из стекла, алюминия или стали - то выбираем их. Далее на каждое растение надо
        // рассчитать стоимость растения + стоимость потребления воды за 5 лет c учётом
        // того что кубометр воды стоит 1.39 $. Суммировать общую стоимость обслуживания
        // всех растений.

        int budget = flowers.stream()
                .sorted(Comparator.comparing(Flower::getOrigin).reversed()
                        .thenComparing(Flower::getPrice)
                        .thenComparing(Comparator.comparing(Flower::getWaterConsumptionPerDay).reversed()))
                .filter(f -> f.getCommonName().compareTo("C") >= 0 && f.getCommonName().compareTo("S") <= 0)
                .filter(f -> f.isShadePreferred() && f.getFlowerVaseMaterial().stream()
                        .anyMatch(material -> material.equalsIgnoreCase("glass") ||
                                material.equalsIgnoreCase("aluminum") ||
                                material.equalsIgnoreCase("steel")))
                .mapToInt(Main::calculateFlowerCost)
                .sum();
        System.out.println("\n Task_15");
        System.out.println("Общая стоимость обслуживания всех растений составляет: "
                + toMoney(budget));
        return budget;
    }

    public static List<String> task16(List<Student> students) {
        //Вывод списка студентов младше 18 лет в алфавитном порядке с указанием возраста
        List<String> youngStudents = students.stream()
                .filter(student -> student.getAge() < AGE_YOUNG)
                .sorted()
                .map(student -> student.getSurname() + " (" + student.getAge() + " years)")
                .toList();
        System.out.println("\n Task_16");
        youngStudents.forEach(System.out::println);
        return youngStudents;
    }

    public static List<String> task17(List<Student> students) {
        //Вывод списка групп (без повторений).
        List<String> groups = students.stream()
                .map(Student::getGroup)
                .distinct()
                .sorted()
                .toList();
        System.out.println("\n Task_17");
        groups.forEach(System.out::println);
        return groups;
    }

    public static void task18() {
        //Определение среднего возраста студентов для каждого факультета.
        // Выводить название факультета и средний возраст в порядке убывания возраста.
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        List<String> facultets = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty, // Группируем студентов по факультету
                        Collectors.averagingInt(Student::getAge)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .map(entry -> "факультет " + entry.getKey() + " (" + String.format("%.2f", entry.getValue()) + " )")
                .toList();
        System.out.println("\n Task_18");
        facultets.forEach(System.out::println);
    }

    public static List<String> task19(List<Student> students, List<Examination> examinations, String groupName) {
        //Вывод списка студентов заданной группы, у которых сдан 3 экзамен (>4).
        List<String> passedStudents = students.stream()
                .filter(s -> s.getGroup().equals(groupName)) // Фильтр по группе
                .filter(s -> examinations.stream()
                        .anyMatch(e -> e.getStudentId() == s.getId() && e.getExam3() > 4))  // Проверяем, что такая запись оценок после фильтрации существует
                .map(Student::getSurname)
                .toList();
        System.out.println("\n Task_19");
        passedStudents.forEach(System.out::println); // Выводим результат
        return passedStudents;
    }

    public static void task20() {
        //Определение факультета с максимальной средней оценкой по первому экзамену.
        List<Student> students = Util.getStudents();
        List<Examination> examinations = Util.getExaminations();
        Map.Entry<String, Double> maxFaculty = students.stream()
                .flatMap(s -> examinations.stream()
                        .filter(e -> e.getStudentId() == s.getId()) // Для каждого студента фильтруем его экзамены
                        .map(e -> Map.entry(s.getFaculty(), e.getExam1())))
                .collect(Collectors.groupingBy(
                        Map.Entry::getKey,
                        Collectors.averagingInt(Map.Entry::getValue)))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        System.out.println("\n Task_20");
        if (maxFaculty != null) {
            System.out.println("Факультет с максимальной средней оценкой по первому " +
                    "экзамену: " + maxFaculty.getKey() + ", Средняя оценка: "
                    + maxFaculty.getValue());
        } else {
            System.out.println("Не найдено данных для расчета.");
        }
    }

    public static void task21() {
        //Определение количества студентов в каждой группе.
        List<Student> students = Util.getStudents();
        Map<String, Long> groups = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getGroup,
                        Collectors.counting()));
        System.out.println("\n Task_21");
        groups.forEach((group, count) ->
                System.out.println("Группа: " + group + ", Количество студентов: " + count));

    }

    public static void task22() {
        //Определение минимального возраста для каждого факультета.
        List<Student> students = Util.getStudents();

        Map<String, Integer> minAgePerFaculty = students.stream()
                .collect(Collectors.groupingBy(
                        Student::getFaculty,
                        Collectors.collectingAndThen(
                                Collectors.mapping(
                                        Student::getAge,
                                        Collectors.minBy(Comparator.naturalOrder())
                                ),
                                optional -> optional.orElse(0) // Извлекаем Integer из Optional<Integer> 0 - если пусто
                        )
                ));
        System.out.println("\n Task_22");
        minAgePerFaculty.forEach((faculty, age) ->
                System.out.println("Факультет: " + faculty + ", Минимальный возраст: " + age));
    }

    public static int calculatePriority(String buildingType, LocalDate birthDate) {
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        int priority;
        if (buildingType.equals(BUILDING_TYPE_HOSPITAL))
            priority = 1;
        else if (age < AGE_YOUNG || age > AGE_PENSION) {
            priority = 2;
        } else {
            priority = 3;
        }
        return priority;
    }

    public static String determineEchelon(Car car) {
        // 1 - Все автомобили марки "Jaguar" а так же все авто цвета White
        // 2 - Из оставшихся все автомобили с массой до 1500 кг и марок BMW, Lexus, Chrysler и Toyota
        // 3 - Из оставшихся все автомобили Черного цвета с массой более 4000 кг и все GMC и Dodge
        // 4 - з оставшихся все автомобили года выпуска до 1982 или все модели "Civic" и "Cherokee"
        // 5 - Из оставшихся все автомобили цветов НЕ Yellow, Red, Green и Blue или же по стоимости дороже 40000
        // 6 - Из оставшиеся все автомобили в vin номере которых есть цифра "59"
        if ("Jaguar".equals(car.getCarMake()) || "White".equalsIgnoreCase(car.getColor())) {
            return ECHELON_1;
        } else if ((car.getMass() <= 1500) &&
                Arrays.asList("BMW", "Lexus", "Chrysler", "Toyota").contains(car.getCarMake())) {
            return ECHELON_2;
        } else if (("Black".equalsIgnoreCase(car.getColor()) && car.getMass() > 4000) ||
                Arrays.asList("GMC", "Dodge").contains(car.getCarMake())) {
            return ECHELON_3;
        } else if (car.getReleaseYear() <= 1982 ||
                Arrays.asList("Civic", "Cherokee").contains(car.getCarModel())) {
            return ECHELON_4;
        } else if (!Arrays.asList("Yellow", "Red", "Green", "Blue").contains(car.getColor()) ||
                car.getPrice() > 40000) {
            return ECHELON_5;
        } else if (car.getVin().contains("59")) {
            return ECHELON_6;
        } else {
            return ECHELON_7;
        }
    }

    public static int calculateTransportCosts(Integer weight_kg) {
        final int COST_PER_TON = 714;
        return (int) Math.round(weight_kg / 1000.0 * COST_PER_TON);
    }

    public static int calculateFlowerCost(Flower flower) {
        final int waterPricePerCubicMeter = 139; // Цена за кубический метр воды
        final int daysInYear = 365;
        final int years = 5;
        int waterCostFor5Years = (int) Math.round(flower.getWaterConsumptionPerDay() * daysInYear * years * waterPricePerCubicMeter / 1000.0);
        return flower.getPrice() + waterCostFor5Years;
    }

    public static String toMoney(int money) {
        final String dollar = "$";
        double moneyDouble = money / 100.0;
        return moneyDouble + " " + dollar;
    }
}
