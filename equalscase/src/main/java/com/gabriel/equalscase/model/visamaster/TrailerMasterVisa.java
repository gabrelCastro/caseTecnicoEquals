package com.gabriel.equalscase.model.visamaster;

import com.gabriel.equalscase.model.base.Trailer;

/**
 * Representa o trailer de um arquivo da bandeira Master/Visa.
 * 
 * Esta classe estende {@link Trailer} sem adicionar novos campos,
 * servindo como uma especialização que permite o uso de generics
 * e mapeamentos específicos para essa bandeira.
 * 
 * É útil em cenários onde a lógica de leitura, validação ou persistência
 * depende da identificação da origem do arquivo (ex: Visa/Master, Elo, etc).
 */
public class TrailerMasterVisa extends Trailer {
    // Nenhum campo adicional — reutiliza totalmente a estrutura de Trailer
}
