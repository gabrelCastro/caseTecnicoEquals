package com.gabriel.equalscase.model.visamaster;

import com.gabriel.equalscase.model.base.Header;

/**
 * Representa o cabeçalho (header) de um arquivo da bandeira Master/Visa.
 * 
 * Esta classe estende {@link Header} sem adicionar novos campos,
 * servindo para especializar o tipo de header e permitir o uso
 * de generics e mapeamentos específicos para a bandeira.
 * 
 * Útil em cenários onde o parser ou o mapper depende da identificação
 * do tipo de arquivo sendo processado (ex: header de Visa/Master ≠ Elo).
 */
public class HeaderMasterVisa extends Header {
    // Nenhum campo adicional — reutiliza totalmente a estrutura de Header
}
