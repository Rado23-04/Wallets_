package Main.entity;

public class Currency {
    private int id;
    private String code;
    private String name;
    private int exchange_rate;

    public Currency(int id, String code, String name, int exchange_rate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.exchange_rate = exchange_rate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExchange_rate() {
        return exchange_rate;
    }

    public void setExchange_rate(int exchange_rate) {
        this.exchange_rate = exchange_rate;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", exchange_rate=" + exchange_rate +
                '}';
    }
}
