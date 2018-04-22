package core;

import java.math.BigDecimal;

public class VolumeCounter {

    private BigDecimal previousValue;
    private BigDecimal presentValue;

    public VolumeCounter(BigDecimal previousValue, BigDecimal presentValue) {
        this.previousValue = previousValue;
        this.presentValue = presentValue;
    }

    public BigDecimal getVolume() {
        return presentValue.subtract(previousValue);
    }

    @Override
    public String toString() {
        return "VolumeCounter{" +
                "previousValue=" + previousValue +
                ", presentValue=" + presentValue +
                '}';
    }
}
