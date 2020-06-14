public class GoldenCard extends Card {
    public GoldenCard(String name){
        super(name);
    }

    @Override
    public boolean executePayment(double cost) {
        if(cost < 0 || cost > amount){
            return false;
        }

        amount -= cost;
        amount += 15.0/100.0 * cost;
        return true;
    }
}
