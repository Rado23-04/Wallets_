package Main.entity;

public class Transaction {

    private int id;
    private int idAccount;
    private int rising;
    private String transactionType;
    private int currencyId;

    public Transaction(int id, int idAccount, int rising, String transactionType, int currencyId) {
        this.id = id;
        this.idAccount = idAccount;
        this.rising = rising;
        this.transactionType = transactionType;
        this.currencyId = currencyId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public int getRising() {
        return rising;
    }

    public void setRising(int rising) {
        this.rising = rising;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idAccount=" + idAccount +
                ", rising=" + rising +
                ", transactionType='" + transactionType + '\'' +
                ", currencyId=" + currencyId +
                '}';
    }
}
