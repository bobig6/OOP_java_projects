import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

public class SampleCarStoreTest {
    public static class CustomComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return Integer.compare(car1.getPrice(), car2.getPrice());
        }
    }

    private static final int CAR_NEW_YEAR = 2006;
    private static final int CAR_MID_YEAR = 2003;
    private static final int CAR_OLD_YEAR = 2001;

    private static final int CHEAP_CAR_PRICE = 300;
    private static final int EXPENSIVE_CAR_PRICE = 5000;
    private static final int VERY_EXPENSIVE_CAR_PRICE = 8000;

    private CarStore carStore;

    @BeforeEach
    public void initializeCarStore() {
        carStore = new CarStore();
    }

    @Test
    public void addReturnsTrueAndAddsCars() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_NEW_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        carStore.add(one);
        Car actual = null;
        try {
            actual = carStore.getCarByRegistrationNumber(one.getRegistrationNumber());
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }
        assertEquals(one, actual);
        carStore.remove(one);
    }

    @Test
    public void addAllReturnsTrueAndAddsCars() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_NEW_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_MID_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(one);
        cars.add(two);
        carStore.addAll(cars);

        Car actualOne = null;
        Car actualTwo = null;
        try {
            actualOne = carStore.getCarByRegistrationNumber(one.getRegistrationNumber());
            actualTwo = carStore.getCarByRegistrationNumber(two.getRegistrationNumber());
        } catch (CarNotFoundException e) {
            e.printStackTrace();
        }
        boolean b = (one == actualOne) && (two == actualTwo);

        assertTrue(b);
        cars.remove(one);
        cars.remove(two);
    }

    @Test
    public void removeReturnsTrueAndRemoveCar() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_NEW_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        carStore.add(one);
        carStore.remove(one);
        Car actual = null;


        try {
            actual = carStore.getCarByRegistrationNumber(one.getRegistrationNumber());
        } catch (CarNotFoundException e) {
            assertEquals(CarNotFoundException.class, e.getClass());
        }

    }


    @Test
    public void findByRegistrationNumberSucceeds() throws CarNotFoundException {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        carStore.add(one);
        carStore.add(two);

        assertEquals(
                one,
                carStore.getCarByRegistrationNumber(one.getRegistrationNumber())
        );

        carStore.remove(one);
        carStore.remove(two);
    }


    @Test
    public void getCarsByModelReturnsCorrectCars() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_NEW_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_MID_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.GASOLINE,
                Region.BURGAS
        );

        carStore.add(one);
        carStore.add(two);
        carStore.add(three);

        Collection<Car> cars = carStore.getCarsByModel(Model.AUDI);

        Car[] expected = { three, one };
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }

    @Test
    public void getCarsReturnsCarsOrdered() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        carStore.add(one);
        carStore.add(two);
        carStore.add(three);

        Car[] actual = {three, one, two};

        ArrayList<Car> cars = carStore.getCars();
//        for (var c : cars){
//            System.out.println(c.getModel() + " " + c.getYear());
//        }
        assertArrayEquals(cars.toArray(), actual);
        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }

    @Test
    public void getCarsWithComparatorReturnsCarsOrderedByComparator() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );

        carStore.add(one);
        carStore.add(two);
        carStore.add(three);
        Collection<Car> cars = carStore.getCars(
                new CustomComparator(),
                false
        );

        Car[] expected = { three, one, two };
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }


    @Test
    public void getCarsWithComparatorReverseReturnsCarsOrderedByComparatorReversed() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );

        carStore.add(one);
        carStore.add(two);
        carStore.add(three);
        Collection<Car> cars = carStore.getCars(
                new CustomComparator(),
                true
        );

        Car[] expected = { two, one, three };
        Car[] actual = cars.toArray(new Car[cars.size()]);
        assertArrayEquals(expected, actual);
        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }

    @Test
    public void getNumberOfCarsReturnsActualNumber() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );

        carStore.add(one);
        carStore.add(two);
        carStore.add(three);

        assertEquals(carStore.getNumberOfCars(), 3);

        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }

    @Test
    public void getTotalPriceReturnsActualNumber() {
        Car one = new OrdinaryCar(
                Model.AUDI,
                CAR_MID_YEAR,
                EXPENSIVE_CAR_PRICE,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.BMW,
                CAR_NEW_YEAR,
                VERY_EXPENSIVE_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                CAR_OLD_YEAR,
                CHEAP_CAR_PRICE,
                EngineType.ELECTRIC,
                Region.BURGAS
        );

        carStore.add(one);
        carStore.add(two);
        carStore.add(three);

        int actual = EXPENSIVE_CAR_PRICE + VERY_EXPENSIVE_CAR_PRICE + CHEAP_CAR_PRICE;
        assertEquals(carStore.getTotalPriceOfCars(), actual);

        carStore.remove(one);
        carStore.remove(two);
        carStore.remove(three);
    }

}
