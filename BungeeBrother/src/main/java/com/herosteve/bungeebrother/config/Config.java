package com.herosteve.bungeebrother.config;

public class Config {

    private String host;

    private Integer port;

    private Integer maxTotal;

    private Integer maxIdle;

    private Integer minIdle;

    private Long maxWaitMillis;

    private Long timeBetweenEvictionRunsMillis;

    private Boolean testWhileIdle;

    private Integer numTestsPerEvictionRun;

    public Config(){}

    public Config(String host, Integer port) {
        this.host = host;
        this.port = port;
    }

    public Config(String host, Integer port, Integer maxTotal, Integer maxIdle, Integer minIdle, Long maxWaitMillis, Long timeBetweenEvictionRunsMillis, Boolean testWhileIdle, Integer numTestsPerEvictionRun) {
        this.host = host;
        this.port = port;
        this.maxTotal = maxTotal;
        this.maxIdle = maxIdle;
        this.minIdle = minIdle;
        this.maxWaitMillis = maxWaitMillis;
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
        this.testWhileIdle = testWhileIdle;
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public Long getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(Long maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public Long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(Long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public Boolean getTestWhileIdle() {
        return testWhileIdle;
    }

    public void setTestWhileIdle(Boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public Integer getNumTestsPerEvictionRun() {
        return numTestsPerEvictionRun;
    }

    public void setNumTestsPerEvictionRun(Integer numTestsPerEvictionRun) {
        this.numTestsPerEvictionRun = numTestsPerEvictionRun;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
