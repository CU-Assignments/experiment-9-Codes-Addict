@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private int txnId;
    private int fromAcc;
    private int toAcc;
    private double amount;
    private String status;

    // Getters and Setters
}
