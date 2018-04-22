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
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(String name,
                 BigDecimal previousValue, BigDecimal presentValue, BigDecimal tariff,
                 LocalDateTime dateTime) {
        this.name = name;
        this.previousValue = previousValue;
        this.presentValue = presentValue;
        this.tariff = tariff;
        this.dateTime = dateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPreviousValue() {
        return previousValue;
    }

    public void setPreviousValue(BigDecimal previousValue) {
        this.previousValue = previousValue;
    }

    public BigDecimal getPresentValue() {
        return presentValue;
    }

    public void setPresentValue(BigDecimal presentValue) {
        this.presentValue = presentValue;
    }

    public BigDecimal getTariff() {
        return tariff;
    }

    public void setTariff(BigDecimal tariff) {
        this.tariff = tariff;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", previousValue=" + previousValue +
                ", presentValue=" + presentValue +
                ", tariff=" + tariff +
                ", dateTime=" + dateTime +
                '}';
    }
}
