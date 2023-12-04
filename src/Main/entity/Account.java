package Main.entity;

import java.util.Date;

public class Account {
//     "id": 1,
//             "pay": "Kevin",
//             "account_type": "Savings",
//             "currency_id": "Ariary",
//             "create_date": "2023-01-01"
    private int id;
    private String pay;
    private String accountType;
    private int currencyId;
    private String createDate;

    public Account(int id, String pay, String accountType, int currencyId, String createDate) {
        this.id = id;
        this.pay = pay;
        this.accountType = accountType;
        this.currencyId = currencyId;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", pay='" + pay + '\'' +
                ", accountType='" + accountType + '\'' +
                ", currencyId=" + currencyId +
                ", createDate=" + createDate +
                '}';
    }
}
