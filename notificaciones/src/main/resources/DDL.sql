CREATE TABLE NOTIFICACION_ENTITY (
                                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                                     fecha_notificacion DATETIME,
                                     mensaje TEXT
);


CREATE TABLE NOTIFICACION_PROMOCION (
                                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                                        codigo_promocion TEXT,
                                        fecha_expiracion DATE,
                                        FOREIGN KEY (id) REFERENCES NOTIFICACION_ENTITY(id)
);

CREATE TABLE NOTIFICACION_RADIO_EXCEDIDO (
                                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                                             lat_actual REAL,
                                             lon_actual REAL,
                                             id_vehiculo INTEGER,
                                             FOREIGN KEY (id) REFERENCES NOTIFICACION_ENTITY(id)
);

CREATE TABLE NOTIFICACION_ZONA_PELIGROSA (
                                             id INTEGER PRIMARY KEY AUTOINCREMENT,
                                             lat_actual REAL,
                                             lon_actual REAL,
                                             nivel_peligro TEXT,
                                             id_vehiculo INTEGER,
                                             FOREIGN KEY (id) REFERENCES NOTIFICACION_ENTITY(id)
);
