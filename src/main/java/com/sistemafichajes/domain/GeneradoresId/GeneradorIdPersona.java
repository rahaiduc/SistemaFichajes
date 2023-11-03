package com.sistemafichajes.domain.GeneradoresId;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class GeneradorIdPersona implements IdentifierGenerator {

    private int currentId = 0;
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Generar un ID único basado en UUID
        currentId++;
        String customId =Integer.toString(currentId);
        return customId;
    }
}
