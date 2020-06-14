import java.time.LocalDate;

public class Transactions {
    String cardName;
    LocalDate date;
    PaymentInfo payment;

    public Transactions(String name, LocalDate date, PaymentInfo payment){
        this.cardName = name;
        this.date = date;
        this.payment = payment;
    }
}
