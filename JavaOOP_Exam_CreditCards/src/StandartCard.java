public class StandartCard extends Card {
    public StandartCard(String name) {
        super(name);
    }

    @Override
    public boolean executePayment(double cost) {
        if(cost < 0 || cost > amount){
            return false;
        }

        amount -= cost;

        return true;
    }
}
