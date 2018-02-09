package br.com.sistema.mcosta.controlador;

import java.util.Base64;

public abstract class AbstractControlador {

	public String codificarBase64(Long valor) {
		return Base64.getEncoder().encodeToString((valor.toString()).getBytes());
	}
	
	public Long decodificarBase64Long(String string) {
		if (string == null) {
			return null;
		}

		String decodificado = new String(Base64.getDecoder().decode(string));
		if ("null".equals(string)) {
			return null;
		}
	
		return Long.valueOf(decodificado);
	}
}
