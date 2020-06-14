import java.time.LocalDate;
import java.util.*;

public class VirtualWallet implements IVirtualWallet {
    ArrayList<Card> cards = new ArrayList<>();
    public ArrayList<Transactions> transactions = new ArrayList<>();

    @Override
    public boolean registerCard(Card card) {
        if(cards.contains(card) || card == null){
            return false;
        }
        cards.add(card);
        return true;
    }

    @Override
    public boolean unregisterCard(Card card) {
        if(!cards.contains(card) || card == null){
            return false;
        }
        cards.remove(card);
        return true;
    }

    @Override
    public boolean executePayment(Card card, PaymentInfo paymentInfo) {
        if(card == null || paymentInfo == null || !cards.contains(card)) return false;
        int index = cards.indexOf(card);
        Transactions t = new Transactions(card.name, LocalDate.now(), paymentInfo);
        transactions.add(t);
        return cards.get(index).executePayment(paymentInfo.getCost());
    }

    @Override
    public boolean feed(Card card, double amount) {
        if(card == null || !cards.contains(card)) return false;
        int index = cards.indexOf(card);
        return cards.get(index).feed(amount);
    }

    @Override
    public Card getCardByName(String name) {
        for(int i = 0; i < cards.size(); i++){
            if(cards.get(i).name.equals(name)){
                return cards.get(i);
            }
        }
        return null;
    }

    @Override
    public int getTotalNumberOfCards() {
        return cards.size();
    }

    @Override
    public Collection<Card> sortCardsByName() {
        Comparator<Card> com = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.name.compareTo(o2.name);
            }
        };

        ArrayList<Card> sorted = cards;
        Collections.sort(sorted, com);
        return sorted;

    }

    @Override
    public Collection<Card> sortCardsByAmount() {
        Comparator<Card> com = new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                if(o1.amount > o2.amount){
                    return 1;
                }
                return 0;
            }
        };

        ArrayList<Card> sorted = cards;
        Collections.sort(sorted, com);
        return sorted;
    }




}
