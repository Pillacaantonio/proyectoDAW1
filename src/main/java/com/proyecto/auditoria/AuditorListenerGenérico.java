
package com.proyecto.auditoria;

import com.proyecto.entity.Departamento;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@Component
public class AuditorListenerGenérico {

    @Autowired
    private HttpServletRequest request; // Para obtener la dirección IP

    @PrePersist
    public void prePersist(Object entity) throws UnknownHostException {
        if (entity instanceof Auditable) {
            Auditable auditableEntity = (Auditable) entity;

            // Asignar fecha y hora de creación
            LocalDateTime ahora = LocalDateTime.now();
            auditableEntity.setFechaCreacion(ahora);
            auditableEntity.setFechaModifica(ahora); // Asignar fecha de modificación al momento de creación

            // Obtener el nombre de la máquina o IP
            String hostName = InetAddress.getLocalHost().getHostName();
            auditableEntity.setUsuarioCreacion(hostName);
            auditableEntity.setUsuarioModifica(hostName); // IP del usuario también para la modificación
        }
    }

    @PreUpdate
    public void preUpdate(Object entity) throws UnknownHostException {
        if (entity instanceof Auditable) {
            Auditable auditableEntity = (Auditable) entity;

            // Asignar fecha de modificación
            LocalDateTime ahora = LocalDateTime.now();
            auditableEntity.setFechaModifica(ahora);

            // Obtener el nombre de la máquina o IP
            String hostName = InetAddress.getLocalHost().getHostName();
            auditableEntity.setUsuarioModifica(hostName);
        }
    }
}