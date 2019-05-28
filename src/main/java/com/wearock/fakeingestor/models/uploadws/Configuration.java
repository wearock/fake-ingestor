package com.wearock.fakeingestor.models.uploadws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "configuration")
public class Configuration {

    @XmlElement(name = "server_url", required = true)
    protected String serverUrl;
    @XmlElement(name = "db_compatibility", required = true)
    protected Configuration.Db_Compatibility dbCompatibility;
    @XmlElement(required = true)
    protected Configuration.Modules modules;


    public static class Db_Compatibility {
    }
    public static class Modules {
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Db_Compatibility getDbCompatibility() {
        return dbCompatibility;
    }

    public void setDbCompatibility(Db_Compatibility dbCompatibility) {
        this.dbCompatibility = dbCompatibility;
    }

    public Modules getModules() {
        return modules;
    }

    public void setModules(Modules modules) {
        this.modules = modules;
    }
}
