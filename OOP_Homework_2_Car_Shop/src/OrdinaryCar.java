import java.util.Comparator;

public class OrdinaryCar extends Car {
    public OrdinaryCar(Model model, int year, int price, EngineType engineType, Region region) {
        super(model, year, price, engineType, region);
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() == OrdinaryCar.class){
            int i = getModel().compareTo(((Car)o).getModel());
            if (i != 0) return i;
            return Integer.compare(getYear(), ((Car)o).getYear());
        }else {
            throw new ClassCastException();
        }
    }


}
