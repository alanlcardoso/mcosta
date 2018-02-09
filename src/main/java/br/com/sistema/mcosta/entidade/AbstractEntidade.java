package br.com.sistema.mcosta.entidade;

import java.util.Base64;

public abstract class AbstractEntidade {

	public abstract Long getId();

	public String getIdBase64() {
		if (getId() == null) {
			return "null";
		}
		return Base64.getEncoder().encodeToString((getId().toString()).getBytes());
	}
}
