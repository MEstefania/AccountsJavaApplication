package org.tatajavaaccounts.service;

import org.tatajavaaccounts.dto.respuestaBase.BaseResponseDTO;

import java.util.Date;

public interface ReporteService {
    BaseResponseDTO obtenerTodasLasCuentasSegunClienteYFecha(Date fechaInicio, Date fechaFin, Long clienteId);
}
