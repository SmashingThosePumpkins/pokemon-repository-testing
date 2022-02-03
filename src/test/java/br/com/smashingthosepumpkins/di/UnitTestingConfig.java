package br.com.smashingthosepumpkins.di;

import br.com.smashingthosepumpkins.di.env.ApplicationSettings;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.io.IOException;

/**
 * @author Pablo A. G. Silva Jr. on 28/01/2022
 * @project pokemon-repository-testing
 */
@Configuration
public class UnitTestingConfig extends SpringConfiguration {
    @Primary
    @Override
    public ApplicationSettings getApplicationSettings() {
        ApplicationSettings applicationSettings = null;
        try {
            String env = "{\"databaseSettings\":{\"username\":\"admin\",\"password\":\"admin\",\"databaseName\":\"pokebase\",\"showSql\":false,\"host\":\"localhost\",\"port\":3306}}";
            applicationSettings = getObjectMapper().readValue(env, ApplicationSettings.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return applicationSettings;
    }
}
