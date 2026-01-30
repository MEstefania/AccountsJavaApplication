package org.tatajavaaccounts.service;

import org.tatajavaaccounts.dto.CuentaDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseSimpleDTO;

public interface CuentaService {
    BaseResponseDTO crearCuenta(CuentaDTO Cuenta);
    BaseResponseSimpleDTO obtenerCuenta(Long idCuenta);
    BaseResponseDTO obtenerTodasLasCuentas();
    BaseResponseDTO actualizarCuenta(CuentaDTO Cuenta, Long idCuenta);
    BaseResponseDTO eliminarCuenta(Long idCuenta);
}
