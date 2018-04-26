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
    private BigDecimal tariff1;
    private BigDecimal tariff2;
    private BigDecimal volume;
    private BigDecimal price;
    private LocalDateTime dateTime;

    public Order() {
    }

    public Order(String name, BigDecimal previousValue, BigDecimal presentValue,
                 BigDecimal tariff1, BigDecimal tariff2, BigDecimal volume,
                 BigDecimal price, LocalDateTime dateTime) {
        this.name = name;
        this.previousValue = previousValue;
        this.presentValue = presentValue;
        this.tariff1 = tariff1;
        this.tariff2 = tariff2;
        this.volume = volume;
        this.price = price;
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

    public BigDecimal getTariff1() {
        return tariff1;
    }

    public void setTariff1(BigDecimal tariff1) {
        this.tariff1 = tariff1;
    }

    public BigDecimal getTariff2() {
        return tariff2;
    }

    public void setTariff2(BigDecimal tariff2) {
        this.tariff2 = tariff2;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
