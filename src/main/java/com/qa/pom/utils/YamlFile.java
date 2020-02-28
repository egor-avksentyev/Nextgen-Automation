package com.qa.pom.utils;

import java.util.Map;

public class YamlFile {

    private String url;
    private String login;
    private String hcn;
    private String userName;
    private String passwordEmail;
    private String password;
    private String messageSubject;
    private String messageRecipients;
    private String messageFrom;
    private String smtpPort;
    private String smtpHost;
    private String logSrcClear;
    private String smtpAuth;
    private String chromeDriverSrc;
    private String screenshotSrcClear;
    private String smtpSocketFactoryPort;
    private String screenshotSrc;
    private String logSrc;
    private Map<String, String> test;
    private String[] anotherTestKeys;

    public YamlFile() {}

    /**
     * Get URL from yaml file
     *
     * @return String represents URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get email from yaml file
     *
     * @return String represents email
     */
    public String getLogin() {
        return login;
    }

    public String getHcn() {
        return hcn;
    }
    /**
     * Get Message Recipients from yaml file
     */
    public String getMessageRecipients()  {
        return messageRecipients;
    }
    /**
     * getMessageFrom from yaml file
     */
    public String getMessageFrom() {
        return messageFrom;
    }
    /**
     * getUserName from yaml file
     */
    public String getUserName() {return userName;}
    /**
     * getMessageSubject from yaml file
     */
    public String getMessageSubject() {return messageSubject;}
    /**
     *  getLogsSrc from yaml file
     */
    public String getLogSrc() {return logSrc;}
    /**
     * getScreenshotSrc from yaml file
     */
    public String getScreenshotSrc() {return screenshotSrc;}
    /**
     * getPasswordEmail from yaml file
     */
    public String getPasswordEmail() {return passwordEmail;}
    /**
     * getSmtpHost from yaml file
     */
    public String getSmtpHost () {return smtpHost;}
    /**
     * getSmtpSocketFactoryPort from yaml file
     */
    public String getSmtpSocketFactoryPort () {return smtpSocketFactoryPort;}
    /**
     * getSmtpAuth from yaml file
     */
    public String getSmtpAuth () {return smtpAuth;}
    /**
     * getSmtpPort from yaml file
     */
    public String getSmtpPort () {return smtpPort;}
    /**
     * getChromeDriverSrc from yaml file
     */
    public String getScreenshotSrcClear () {return screenshotSrcClear;}

    public String getLogSrcClear () {return logSrcClear;}

    public String getChromeDriverSrc () {return chromeDriverSrc;}

    /**
     * Get password from yaml file
     *
     * @return String represents password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Get test from yaml file
     *
     * @return Map represents test
     */
    public Map<String, String> getTest() {
        return test;
    }

    /**
     * Get anotherTestKeys from yaml file
     *
     * @return array that contains string values
     */
    public String[] getAnotherTestKeys() {
        return anotherTestKeys;
    }
}
