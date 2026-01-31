package org.tatajavaaccounts.service.impl;

import org.tatajavaaccounts.client.ClienteClient;
import org.tatajavaaccounts.dto.ClienteDTO;
import org.tatajavaaccounts.dto.CuentaDTO;
import org.tatajavaaccounts.dto.MovimientoDTO;
import org.tatajavaaccounts.dto.MovimientoPorCuentaDTO;
import org.tatajavaaccounts.dto.respuestaBase.BaseResponseDTO;
import org.tatajavaaccounts.dto.respuestaBase.ResponseBaseMapper;
import org.tatajavaaccounts.exception.CuentaException;
import org.tatajavaaccounts.model.Cuenta;
import org.tatajavaaccounts.model.Movimiento;
import org.tatajavaaccounts.repository.CuentaRepository;
import org.tatajavaaccounts.repository.MovimientoRepository;
import org.tatajavaaccounts.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
   private ClienteClient clienteClient;
    // private ClienteRepository clienteRepository;
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Override
    public BaseResponseDTO obtenerTodasLasCuentasSegunClienteYFecha(Date fechaInicio, Date fechaFin, Long clienteId) {
        //Buscar cuentas del cliente
        Optional<ClienteDTO> clienteOpt = clienteClient.getClientById(clienteId);
        if(clienteOpt.isEmpty()){
            clienteOpt = Optional.of(new ClienteDTO("0", "SERVICIO DE CLIENTES NO DISPONIBLE"));
        }
        List<Cuenta> cuentas = cuentaRepository.findByIdCliente(clienteId).orElseThrow(() -> new CuentaException(CuentaException.NO_TIENE_CUENTA));
        List<MovimientoPorCuentaDTO> movimientosPorCuenta = new ArrayList<>();
        for (Cuenta cuenta : cuentas) {
            MovimientoPorCuentaDTO cuentaDetallada = new MovimientoPorCuentaDTO();
            CuentaDTO miCuenta = new CuentaDTO();
            miCuenta.setIdCuenta(cuenta.getId().toString());
            miCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
            miCuenta.setNombreCliente("");
            miCuenta.setSaldoInicial(cuenta.getSaldoInicial());
            miCuenta.setTipo(cuenta.getTipo());
            miCuenta.setEstado(cuenta.getEstado());
            miCuenta.setNombreCliente(clienteOpt.map(ClienteDTO::getNombre).orElse("Nada"));
            cuentaDetallada.setCuenta(miCuenta);
            cuentaDetallada.setSaldo(cuenta.getSaldoInicial());
            List<Movimiento> movimientos = movimientoRepository.findByIdCuentaAndFechaBetweenOrderByFechaDesc(cuenta, new Timestamp(fechaInicio.getTime()), new Timestamp(fechaFin.getTime())).orElse(null);
            List<MovimientoDTO> movimientoDTOS = new ArrayList<>();
            if (movimientos != null && !movimientos.isEmpty()) {
                cuentaDetallada.setSaldo(movimientos.get(0).getSaldoMovimiento());
                for (Movimiento mov : movimientos) {
                    MovimientoDTO movimientoDTO = new MovimientoDTO(mov.getId(), mov.getFecha(), mov.getTipo(), mov.getValorMovimiento(), mov.getSaldoMovimiento());
                    movimientoDTOS.add(movimientoDTO);
                }
            }
            cuentaDetallada.setMovimientos(movimientoDTOS);
            movimientosPorCuenta.add(cuentaDetallada);
        }
        return ResponseBaseMapper.generateOkResponse(new ArrayList<>(movimientosPorCuenta));
    }

}
