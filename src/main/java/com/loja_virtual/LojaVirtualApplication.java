package com.loja_virtual;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication  // Isso escaneia automaticamente os pacotes abaixo de "com.loja_virtual"
@ComponentScan(basePackages = "com.loja_virtual")
public class LojaVirtualApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaVirtualApplication.class, args);
		System.out.println("-----------------------------------------");
	}

}
