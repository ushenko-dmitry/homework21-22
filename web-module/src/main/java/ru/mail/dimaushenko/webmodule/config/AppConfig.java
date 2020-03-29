package ru.mail.dimaushenko.webmodule.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"ru.mail.dimaushenko.service", "ru.mail.dimaushenko.repository"})
public class AppConfig {

}
