import java.util.Comparator;
import java.util.HashMap;
import java.util.Random;

public abstract class Car implements Comparable{
    private static HashMap<Region, Integer> uniqueNumbers = new HashMap<>();
    static {
        for(var region : Region.values()){
            uniqueNumbers.put(region, 1000);
        }
    }


    private Model model;
    private int year;
    private int price;
    private EngineType engineType;
    private String registrationNumber;
    private Region region;


    public Car(Model model, int year, int price, EngineType engineType, Region region){
        this.model = model;
        this.year = year;
        this.price = price;
        this.engineType = engineType;
        this.region = region;
        var uniqueNumber = uniqueNumbers.get(region);
        registrationNumber = String.format("%s%d%c%c", region.getPrefix(), uniqueNumber, getRandomLetter(), getRandomLetter());
        uniqueNumbers.put(region, uniqueNumber + 1);
    }


    /**
     * Returns the model of the car.
     */
    public Model getModel(){
        return model;
    }

    /**
     * Returns the year of manufacture of the car.
     */
    public int getYear(){
        return year;
    }

    /**
     * Returns the price of the car.
     */
    public int getPrice(){
        return price;
    }

    /**
     * Returns the engine type of the car.
     */
    public EngineType getEngineType(){
        return engineType;
    }

    /**
     * Returns the unique registration number of the car.
     */
    public String getRegistrationNumber(){
        return registrationNumber;
    }


    private char getRandomLetter(){
        Random r = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return alphabet.charAt(r.nextInt(alphabet.length()));
    }

}
