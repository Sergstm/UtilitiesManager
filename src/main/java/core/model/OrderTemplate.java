package core.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_templates")
public class OrderTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    @Column(precision = 19, scale = 3)
    private BigDecimal tariff1;
    @Column(precision = 19, scale = 3)
    private BigDecimal tariff2;

    public OrderTemplate() {
    }

    public OrderTemplate(String name, BigDecimal tariff1, BigDecimal tariff2) {
        this.name = name;
        this.tariff1 = tariff1;
        this.tariff2 = tariff2;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getTariff1() {
        return tariff1;
    }

    public BigDecimal getTariff2() {
        return tariff2;
    }
}
