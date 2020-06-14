
public class Main {
    public static void main(String[] args) {
        Car one = new OrdinaryCar(
                Model.AUDI,
                2006,
                5000,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car two = new OrdinaryCar(
                Model.AUDI,
                2006,
                5000,
                EngineType.DIESEL,
                Region.BURGAS
        );
        Car three = new OrdinaryCar(
                Model.AUDI,
                2006,
                5000,
                EngineType.DIESEL,
                Region.SOFIA
        );
        System.out.println(one.getRegistrationNumber());
        System.out.println(two.getRegistrationNumber());
        System.out.println(three.getRegistrationNumber());

    }
}
