-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS bdbancobg2 CHARACTER SET utf8 COLLATE utf8_general_ci;
USE bdbancobg2;

-- Tabla administrador
CREATE TABLE administrador (
  id_admin VARCHAR(10) NOT NULL,
  usuario_admin VARCHAR(45) NOT NULL,
  contraseña_admin VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_admin)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla cliente
CREATE TABLE cliente (
  id_cliente VARCHAR(10) NOT NULL,
  dni_cliente INT(8),
  cuil_cliente VARCHAR(15),
  nombre_cliente VARCHAR(45),
  apellido_cliente VARCHAR(45),
  sexo_cliente VARCHAR(15),
  nacionalidad_cliente VARCHAR(25),
  fechaNac_cliente DATE,
  direccion_cliente VARCHAR(45),
  localidad_cliente VARCHAR(45),
  provincia_cliente VARCHAR(45),
  correo_cliente VARCHAR(45),
  telefono_cliente VARCHAR(20),
  usuario_cliente VARCHAR(45),
  contraseña_cliente VARCHAR(25),
  estado_cliente BOOLEAN DEFAULT TRUE,
  PRIMARY KEY (id_cliente)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla tipocuenta
CREATE TABLE tipocuenta (
  idtipoCuenta INT NOT NULL,
  descripcion_tipoCuenta VARCHAR(45),
  PRIMARY KEY (idtipoCuenta)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla cuenta
CREATE TABLE cuenta (
  id_cuenta VARCHAR(10) NOT NULL,
  idCliente_cuenta VARCHAR(10),
  idTipoCuenta_cuenta INT,
  fechaCreacion_cuenta DATE,
  numero_cuenta VARCHAR(45),
  cbu_cuenta VARCHAR(45),
  saldo_cuenta DECIMAL(12,2),
  PRIMARY KEY (id_cuenta),
  KEY fk_idCliente_idx (idCliente_cuenta),
  KEY fk_tipoCuenta_idx (idTipoCuenta_cuenta),
  CONSTRAINT fk_idCliente FOREIGN KEY (idCliente_cuenta) REFERENCES cliente (id_cliente),
  CONSTRAINT fk_tipoCuenta FOREIGN KEY (idTipoCuenta_cuenta) REFERENCES tipocuenta (idtipoCuenta),
  estado_cuentas BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla estadoprestamos
CREATE TABLE estadoprestamos (
  id_estadoPrestamos INT NOT NULL,
  desc_estadoPrestamo VARCHAR(45),
  PRIMARY KEY (id_estadoPrestamos)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla prestamo
CREATE TABLE prestamo (
  id_prestamo VARCHAR(10) NOT NULL,
  idCliente_pres VARCHAR(10),
  fechaSolicitud_pres DATE,
  importeSolicitado_pres DECIMAL(12,2),
  importeTotal_pres DECIMAL(12,2),
  plazoMeses_pres INT,
  montoCuota_pres DECIMAL(12,2),
  estado_pres INT,
  idCuentaDeposito_pres VARCHAR(10),
  PRIMARY KEY (id_prestamo),
  KEY fk_estado_idx (estado_pres),
  KEY fk_idCuentaDeposito_idx (idCuentaDeposito_pres),
  KEY fk_idClienPress_idx (idCliente_pres),
  CONSTRAINT fk_estado FOREIGN KEY (estado_pres) REFERENCES estadoprestamos (id_estadoPrestamos),
  CONSTRAINT fk_idCuentaDeposito FOREIGN KEY (idCuentaDeposito_pres) REFERENCES cuenta (id_cuenta),
  CONSTRAINT fk_idClienPress FOREIGN KEY (idCliente_pres) REFERENCES cliente (id_cliente),
  estado_prestamo BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla estadocuotas
CREATE TABLE estadocuotas (
  id_estadoCuotas INT NOT NULL,
  desc_estadoCuotas VARCHAR(20),
  PRIMARY KEY (id_estadoCuotas)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla cuota
CREATE TABLE cuota (
  id_cuota INT NOT NULL,
  idPrestamo_cuota VARCHAR(45),
  numero_cuota INT,
  importe_cuota DECIMAL(12,2),
  fechaVenc_cuota DATE,
  fechaPago_cuota DATE,
  estado_cuota INT,
  PRIMARY KEY (id_cuota),
  KEY fk_idPrestamo_idx (idPrestamo_cuota),
  KEY fk_idEstadoCuota_idx (estado_cuota),
  CONSTRAINT fk_idPrestamo FOREIGN KEY (idPrestamo_cuota) REFERENCES prestamo (id_prestamo),
  CONSTRAINT fk_idEstadoCuota FOREIGN KEY (estado_cuota) REFERENCES estadocuotas (id_estadoCuotas)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla tipomovimiento
CREATE TABLE tipomovimiento (
  idTipoMovimiento INT NOT NULL,
  descripcion_tipoMov VARCHAR(45),
  PRIMARY KEY (idTipoMovimiento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Tabla movimiento
CREATE TABLE movimiento (
  id_mov VARCHAR(10) NOT NULL,
  idCuenta_mov VARCHAR(10),
  fecha_mov DATE,
  detalle_mov VARCHAR(45),
  importe_mov DECIMAL(12,2),
  idTipoMov_mov INT,
  idCuentaDestino_mov VARCHAR(10),
  PRIMARY KEY (id_mov),
  KEY fk_idCuenta_idx (idCuenta_mov),
  KEY fk_idTipoMov_idx (idTipoMov_mov),
  KEY fk_idCuentaDestino_idx (idCuentaDestino_mov),
  CONSTRAINT fk_idCuenta FOREIGN KEY (idCuenta_mov) REFERENCES cuenta (id_cuenta),
  CONSTRAINT fk_idCuentaDestino FOREIGN KEY (idCuentaDestino_mov) REFERENCES cuenta (id_cuenta),
  CONSTRAINT fk_idTipoMov FOREIGN KEY (idTipoMov_mov) REFERENCES tipomovimiento (idTipoMovimiento)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
