package org.tatajavaaccounts.service;

import org.tatajavaaccounts.dto.MovimientoDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseSimpleDTO;

public interface MovimientoService {
    BaseResponseDTO crearMovimiento(MovimientoDTO Movimiento);
    BaseResponseSimpleDTO obtenerMovimiento(Long idMovimiento);
    BaseResponseDTO obtenerTodosLosMovimientos();
    BaseResponseDTO actualizarMovimiento(MovimientoDTO Movimiento, Long idMovimiento);
    BaseResponseDTO eliminarMovimiento(Long idMovimiento);
}
