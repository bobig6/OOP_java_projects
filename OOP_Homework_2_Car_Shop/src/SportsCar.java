public class SportsCar extends Car {
    public SportsCar(Model model, int year, int price, EngineType engineType, Region region) {
        super(model, year, price, engineType, region);
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() == Car.class){
            int i = getModel().compareTo(((Car)o).getModel());
            if (i != 0) return i;
            return Integer.compare(getYear(), ((Car)o).getYear());
        }else {
            throw new ClassCastException();
        }
    }
}
