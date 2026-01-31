package org.tatajavaaccounts.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.tatajavaaccounts.model.Cuenta;

import java.math.BigDecimal;
import java.util.List;

public class MovimientoPorCuentaDTO {
    @JsonProperty("cuenta")
    private CuentaDTO cuenta;

    @JsonProperty("saldo")
    private BigDecimal saldo;

    @JsonProperty("movimientos")
    private List<MovimientoDTO> movimientos;

    public List<MovimientoDTO> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<MovimientoDTO> movimientos) {
        this.movimientos = movimientos;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public CuentaDTO getCuenta() {
        return cuenta;
    }

    public void setCuenta(CuentaDTO cuenta) {
        this.cuenta = cuenta;
    }
}
