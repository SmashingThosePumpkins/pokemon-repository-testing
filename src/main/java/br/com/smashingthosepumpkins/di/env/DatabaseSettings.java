package br.com.smashingthosepumpkins.di.env;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * @author Pablo A. G. Silva Jr. on 27/01/2022
 * @project pokemon-repository-testing
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DatabaseSettings {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    @JsonProperty("databaseName")
    private String databaseName;

    @JsonProperty("showSql")
    private Boolean showSql;

    @JsonProperty("host")
    private String host;

    @JsonProperty("port")
    private Integer port;

    public DatabaseSettings(){}
    public DatabaseSettings(String username, String password, String databaseName, Boolean showSql, String host, Integer port) {
        this.username = username;
        this.password = password;
        this.databaseName = databaseName;
        this.showSql = showSql;
        this.host = host;
        this.port = port;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public boolean isShowSql() {
        return showSql;
    }
    public String getHost() {
        return host;
    }
    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "DatabaseSettings{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", showSql=" + showSql +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}

