package com.shop.server;
import com.shop.server.common.Context;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.File;
import java.io.IOException;

@EnableWebMvc
//@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients
@SpringCloudApplication
@EnableZuulProxy
//@EnableEurekaClient
@MapperScan(basePackages = "com.shop.server.mapper")
public class ShoppingApplication extends SpringBootServletInitializer {

	@Value("${https.port}")
	private Integer port;

	@Value("${https.ssl.key-store-password}")
	private String key_store_password;

	@Value("${https.classname}")
	private String classname;

	@Value("${https.password}")
	private String password;

	@Value("${https.ssl.key-password}")
	private String key_password;
	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
//		new SpringApplicationBuilder(ShoppingApplication.class).web(true).run(args);
	}

//	@Bean
//	public EmbeddedServletContainerFactory servletContainer() {
//		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();
//		tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http
//		return tomcat;
//	}

	@Bean
	public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
		TomcatServletWebServerFactory tomcat=new TomcatServletWebServerFactory();
//		{
//			@Override
//			protected void postProcessContext(org.apache.catalina.Context context) {
//				SecurityConstraint securityConstraint=new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection=new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
		tomcat.addAdditionalTomcatConnectors(createSslConnector());
		return tomcat;
	}

	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
//			File keystore = new ClassPathResource("mykeys.jks").getFile();
			File keystore = Context.getHttpsFile("edu.ccmmxj.club.jks");
            /*File truststore = new ClassPathResource("sample.jks").getFile();*/
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(port);
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(keystore.getAbsolutePath());
			protocol.setKeystorePass(key_store_password);
			protocol.setKeyPass(key_password);
			return connector;
		}
		catch (IOException ex) {
			throw new IllegalStateException("can't access keystore: [" + "keystore"
					+ "] or truststore: [" + "keystore" + "]", ex);
		}
	}
}
