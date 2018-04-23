package core;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private BigDecimal previousValue;
    private BigDecimal presentValue;
    private BigDecimal tariff;
    private BigDecimal volume;
    private BigDecimal price;
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(String name,
                 BigDecimal previousValue, BigDecimal presentValue, BigDecimal tariff,
                 BigDecimal volume, BigDecimal price, LocalDateTime dateTime) {
        this.name = name;
        this.previousValue = previousValue;
        this.presentValue = presentValue;
        this.tariff = tariff;
        this.volume = volume;
        this.price = price;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPreviousValue() {
        return previousValue;
    }

    public BigDecimal getPresentValue() {
        return presentValue;
    }

    public BigDecimal getTariff() {
        return tariff;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
