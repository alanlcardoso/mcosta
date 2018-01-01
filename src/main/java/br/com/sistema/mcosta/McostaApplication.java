package br.com.sistema.mcosta;

import java.util.Locale;

import org.apache.coyote.http2.Http2Protocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

import br.com.sistema.mcosta.entidade.Servico;
import br.com.sistema.mcosta.repositorio.IServicoRepositorio;

@SpringBootApplication
@EntityScan(basePackageClasses = Servico.class)
@EnableJpaRepositories(basePackageClasses = IServicoRepositorio.class)
@EnableTransactionManagement
public class McostaApplication {

	public static void main(String[] args) {
		SpringApplication.run(McostaApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver(new Locale("pt", "BR"));
	}

	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer() {
		return (container) -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				((TomcatEmbeddedServletContainerFactory) container)
						.addConnectorCustomizers((connector) -> connector.addUpgradeProtocol(new Http2Protocol()));
			}
		};
	}
}