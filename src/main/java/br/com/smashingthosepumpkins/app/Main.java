package br.com.smashingthosepumpkins.app;

import br.com.smashingthosepumpkins.di.SpringConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static ApplicationContext context;

    public static void main(String[] args) {
        context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        ApplicationService appService = context.getBean(ApplicationService.class);
        appService.start(args);
    }
}
