@Service
public class BankService {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void transfer(int fromAcc, int toAcc, double amount) {
        Session session = sessionFactory.getCurrentSession();

        Account sender = session.get(Account.class, fromAcc);
        Account receiver = session.get(Account.class, toAcc);

        if (sender.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance!");
        }

        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        Transaction txn = new Transaction();
        txn.setFromAcc(fromAcc);
        txn.setToAcc(toAcc);
        txn.setAmount(amount);
        txn.setStatus("SUCCESS");

        session.save(txn);
    }
}
