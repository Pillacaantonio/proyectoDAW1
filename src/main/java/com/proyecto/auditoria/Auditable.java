package com.proyecto.auditoria;

import java.time.LocalDateTime;

public interface Auditable {
    void setFechaCreacion(LocalDateTime fechaCreacion);
    void setFechaModifica(LocalDateTime fechaModifica);
    void setUsuarioCreacion(String usuarioCreacion);
    void setUsuarioModifica(String usuarioModifica);
}
