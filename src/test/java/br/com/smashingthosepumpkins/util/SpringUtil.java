package br.com.smashingthosepumpkins.util;

import br.com.smashingthosepumpkins.app.ApplicationService;
import br.com.smashingthosepumpkins.di.SpringConfiguration;
import br.com.smashingthosepumpkins.di.UnitTestingConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Pablo A. G. Silva Jr. on 31/01/2022
 * @project pokemon-repository-testing
 */
public class SpringUtil {
    public static ApplicationContext getContext() {
        return new AnnotationConfigApplicationContext(UnitTestingConfig.class);
    }
}
