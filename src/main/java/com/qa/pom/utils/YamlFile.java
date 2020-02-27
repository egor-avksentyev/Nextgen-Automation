package com.qa.pom.utils;

import java.util.Map;

public class YamlFile {

    private String url;
    private String login;
    private String hcn;
    private String username;
    private String passwordemail;
    private String password;
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

    public String getUsername() {return username;}

    public String getPasswordemail() {return passwordemail;}

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
