package br.com.smashingthosepumpkins.di.env;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplicationSettings {
    @JsonProperty("databaseSettings")
    private DatabaseSettings databaseSettings;

    public DatabaseSettings getDatabaseSettings() {
        return databaseSettings;
    }

    @Override
    public String toString() {
        return "ApplicationSettings{" +
                "databaseSettings=" + databaseSettings +
                '}';
    }
}
