import java.util.ArrayList;
import java.util.Comparator;

public class CarStore {
    ArrayList<Car> Cars = new ArrayList<>();

    private static class CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car o1, Car o2) {
            return o1.compareTo(o2);
        }
    }

    /**
     * Adds the specified car in the store.
     * @return true if the car was added successfully to the store
     */
    public boolean add(Car car) {
        return Cars.add(car);
    }

    /**
     * Adds all of the elements of the specified collection in the store.
     * @return true if the store cars are changed after the execution
     * (i.e. at least one new car is added to the store)
     */
    public boolean addAll(ArrayList<Car> cars) {
        return Cars.addAll(cars);
    }

    /**
     * Removes the specified car from the store.
     * @return true if the car is successfully removed from the store
     */
    public boolean remove(Car car) {
        return Cars.remove(car);
    }

    /**
     * Finds a car from the store by its registration number.
     * @throws CarNotFoundException if a car with this
     * registration number is not found in the store
     **/
    public Car getCarByRegistrationNumber(String registrationNumber) throws CarNotFoundException {
        for(int i = 0; i < Cars.size(); i++){
            if(Cars.get(i).getRegistrationNumber().equals(registrationNumber)){
                return Cars.get(i);
            }
        }
        throw new CarNotFoundException();
    }

    /**
     * Returns all cars of a given model.
     * The cars need to be sorted by year of manufacture (in ascending order).
     */
    public ArrayList<Car> getCarsByModel(Model model) {
        ArrayList<Car> carsWithModel = new ArrayList<>();
        for(int i = 0; i < Cars.size(); i++){
            if(Cars.get(i).getModel() == model){
                carsWithModel.add(Cars.get(i));
            }
        }
        carsWithModel.sort(new CarComparator());
        return carsWithModel;
    }

    /**
     * Returns all cars sorted by their natural ordering
     * (according to the implementation of the Comparable<Car> interface).
     **/
    public ArrayList<Car> getCars() {
        ArrayList<Car> sorted = Cars;
        sorted.sort(new CarComparator());
        return sorted;
    }

    /**
     * Returns all cars sorted according to the
     * order induced by the specified comparator.
     */
    public ArrayList<Car> getCars(Comparator<Car> comparator) {
        ArrayList<Car> sorted = Cars;
        sorted.sort(comparator);
        return sorted;
    }

    /**
     * Returns all cars sorted according to the
     * given comparator and boolean flag for order.
     * @param isReversed if true, the cars should be returned in reverse order
     */
    public ArrayList<Car> getCars(Comparator<Car> comparator, boolean isReversed) {
        ArrayList<Car> sorted = Cars;
        sorted.sort(comparator);
        if(isReversed){
            return reverseArrayList(sorted);
        }
        return sorted;
    }

    public ArrayList<Car> reverseArrayList(ArrayList<Car> alist)
    {
        ArrayList<Car> revArrayList = new ArrayList<>();
        for (int i = alist.size() - 1; i >= 0; i--) {

            revArrayList.add(alist.get(i));
        }

        return revArrayList;
    }

    /**
     * Returns the total number of cars in the store.
     */
    public int getNumberOfCars() {
        return Cars.size();
    }

    /**
     * Returns the total price of all cars in the store.
     */
    public int getTotalPriceOfCars() {
        int total = 0;
        for (int i = 0; i < Cars.size(); i++){
            total+=Cars.get(i).getPrice();
        }
        return total;
    }


}
