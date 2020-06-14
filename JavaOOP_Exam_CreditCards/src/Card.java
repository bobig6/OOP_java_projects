public abstract class Card {
    String name;
    double amount = 0.0;


    public Card(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public boolean feed(double amount){
        if(amount < 0){
            return false;
        }
        this.amount += amount;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Card)){
            return false;
        }
        if(name.equals(((Card) o).name)){
            return true;
        }
        return false;
    }

    public abstract boolean executePayment(double cost);
}
