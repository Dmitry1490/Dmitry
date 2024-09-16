import java.util.*;

public class Telefonbook {

    private Map<String, List<Double>> telefonBook = new HashMap<>();

    public void add(String name, double number){
        if(telefonBook.containsKey(name)) {
            telefonBook.get(name).add(number);
        } else {
            ArrayList<Double> listNumber = new ArrayList<>();
            listNumber.add(number);
            telefonBook.put(name, listNumber);
        }
    }

    public void get(String name){
        if(telefonBook.containsKey(name)){
            telefonBook.get(name);
            System.out.printf(name + ": ");
            for(Iterator<Double> iterator = telefonBook.get(name).iterator(); iterator.hasNext();){
                System.out.printf("%.0f", iterator.next());
                System.out.printf(", ");
            }
        } else {
            System.out.println(name + " нет в телефонной книге");
        }
    }

}
