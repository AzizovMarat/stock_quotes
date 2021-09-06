package ru.alfabank.stock_quotes.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("openexchangerates")
public class OpenExchangeRatesConfiguration {

    private String url;
    private String appID;
    private String latest;
    private String historical;
    private String currencies;
    private String timeSeries;
    private String convert;
    private String ohlc;
    private String usage;

    public OpenExchangeRatesConfiguration() {

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppID() {
        return appID;
    }

    public void setAppID(String appID) {
        this.appID = appID;
    }

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getHistorical() {
        return historical;
    }

    public void setHistorical(String historical) {
        this.historical = historical;
    }

    public String getCurrencies() {
        return currencies;
    }

    public void setCurrencies(String currencies) {
        this.currencies = currencies;
    }

    public String getTimeSeries() {
        return timeSeries;
    }

    public void setTimeSeries(String timeSeries) {
        this.timeSeries = timeSeries;
    }

    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }

    public String getOhlc() {
        return ohlc;
    }

    public void setOhlc(String ohlc) {
        this.ohlc = ohlc;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }
}
