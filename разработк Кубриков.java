import java.util.*;

class Notebook {
    private String model; // Модель ноутбука
    private int ram; // ОЗУ в ГБ
    private int storage; // Объем ЖД в ГБ
    private String os; // Операционная система
    private String color; // Цвет

    // Конструктор класса Notebook
    public Notebook(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    // Геттеры для доступа к полям класса Notebook
    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    // Переопределенный метод toString() для вывода информации о ноутбуке
    @Override
    public String toString() {
        return "Notebook{" +
                "model='" + model + '\'' +
                ", ram=" + ram +
                ", storage=" + storage +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

public class NotebookFilter {

    public static void main(String[] args) {
        // Создаем множество ноутбуков
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Acer Nitro 5", 16, 512, "Windows", "Черный"));
        notebooks.add(new Notebook("Lenovo IdeaPad 3", 8, 256, "Windows", "Серый"));
        notebooks.add(new Notebook("Asus ROG Zephyrus", 32, 1024, "Windows", "Серебристый"));
        notebooks.add(new Notebook("HP Pavilion", 8, 256, "Windows", "Белый"));
        notebooks.add(new Notebook("Dell XPS 13", 16, 512, "Windows", "Золотой"));

        // Запрашиваем у пользователя критерии фильтрации
        Map<Integer, String> criteriaMap = getFilterCriteria(); // Получаем критерии от пользователя

        // Запрашиваем минимальные значения для указанных критериев
        Map<String, Integer> minValuesMap = getMinValues(criteriaMap); // Получаем минимальные значения для критериев

        // Фильтруем ноутбуки
        Set<Notebook> filteredNotebooks = filterNotebooks(notebooks, minValuesMap, criteriaMap); // Фильтруем ноутбуки по критериям

        // Выводим отфильтрованные ноутбуки
        System.out.println("Отфильтрованные ноутбуки:");
        filteredNotebooks.forEach(System.out::println); // Выводим отфильтрованные ноутбуки
    }

    // Метод для получения критериев фильтрации от пользователя
    private static Map<Integer, String> getFilterCriteria() {
        Map<Integer, String> criteriaMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");
        while (true) {
            System.out.print("Введите номер критерия (или 0 для завершения): ");
            int criteriaNumber = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки
            if (criteriaNumber == 0) {
                break;
            }
            switch (criteriaNumber) {
                case 1:
                    criteriaMap.put(criteriaNumber, "ram");
                    break;
                case 2:
                    criteriaMap.put(criteriaNumber, "storage");
                    break;
                case 3:
                    criteriaMap.put(criteriaNumber, "os");
                    break;
                case 4:
                    criteriaMap.put(criteriaNumber, "color");
                    break;
                default:
                    System.out.println("Некорректный номер критерия. Попробуйте снова.");
            }
        }
        return criteriaMap;
    }

    // Метод для получения минимальных значений для выбранных критериев
    private static Map<String, Integer> getMinValues(Map<Integer, String> criteriaMap) {
        Map<String, Integer> minValuesMap = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        for (Integer criteriaNumber : criteriaMap.keySet()) {
            System.out.print("Введите минимальное значение для " + criteriaMap.get(criteriaNumber) + ": ");
            int minValue = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки
            minValuesMap.put(criteriaMap.get(criteriaNumber), minValue);
        }
        return minValuesMap;
    }

    // Метод для фильтрации ноутбуков
    private static Set<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<String, Integer> minValuesMap, Map<Integer, String> criteriaMap) {
        Set<Notebook> filteredNotebooks = new HashSet<>();
        for (Notebook notebook : notebooks) {
            boolean matches = true;
            for (String criteria : minValuesMap.keySet()) {
                // Проверяем, соответствует ли ноутбук заданным минимальным значениям
                switch (criteria) {
                    case "ram":
                        if (notebook.getRam() < minValuesMap.get(criteria)) {
                            matches = false;
                        }
                        break;
                    case "storage":
                        if (notebook.getStorage() < minValuesMap.get(criteria)) {
                            matches = false;
                        }
                        break;
                    case "os":
                        if (!notebook.getOs().equals(criteriaMap.get(criteria))) {
                            matches = false;
                        }
                        break;
                    case "color":
                        if (!notebook.getColor().equals(criteriaMap.get(criteria))) {
                            matches = false;
                        }
                        break;
                }
            }
            if (matches) {
                filteredNotebooks.add(notebook);
            }
        }
        return filteredNotebooks;
    }