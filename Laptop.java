import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public class Laptop {
    private String model;
    private int ramSize;
    private int storageSize;
    private String operatingSystem;

    public Laptop(String model, int ramSize, int storageSize, String operatingSystem) {
        this.model = model;
        this.ramSize = ramSize;
        this.storageSize = storageSize;
        this.operatingSystem = operatingSystem;
    }

    public String getModel() {
        return model;
    }

    public int getRamSize() {
        return ramSize;
    }

    public int getStorageSize() {
        return storageSize;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, Object> criteria) {
        Set<Laptop> result = new HashSet<>(laptops);

        for (Map.Entry<String, Object> entry : criteria.entrySet()) {
            String criterion = entry.getKey();
            Object value = entry.getValue();

            Iterator<Laptop> iterator = result.iterator();
            while (iterator.hasNext()) {
                Laptop laptop = iterator.next();
                if (!laptop.matchesCriterion(criterion, value)) {
                    iterator.remove();
                }
            }
        }

        return result;
    }

    private boolean matchesCriterion(String criterion, Object value) {
        switch (criterion) {
            case "RAM":
                return ramSize >= (int) value;
            case "StorageSize":
                return storageSize >= (int) value;
            case "OperatingSystem":
                return operatingSystem.equals(value);
            case "Model":
                return model.equals(value);
            default:
                return false;
        }
    }

    public static void main(String[] args) {

        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Asus", 8, 512, "Windows"));
        laptops.add(new Laptop("HP", 16, 256, "Linux"));
        laptops.add(new Laptop("Dell", 12, 1024, "Windows"));
        laptops.add(new Laptop("Lenovo", 8, 512, "Linux"));
        laptops.add(new Laptop("Apple", 16, 512, "macOS"));

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("RAM", 8);
        criteria.put("OperatingSystem", "Windows");

        Set<Laptop> result = Laptop.filterLaptops(laptops, criteria);

        for (Laptop laptop : result) {
            System.out.println("Model: " + laptop.getModel() + ", RAM: " + laptop.getRamSize() +
                    ", Storage Size: " + laptop.getStorageSize() + ", OS: " + laptop.getOperatingSystem());
        }
    }
}