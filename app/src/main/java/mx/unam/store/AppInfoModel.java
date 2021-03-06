package mx.unam.store;

import java.io.Serializable;

public class AppInfoModel implements Serializable
{
    private String name_app;
    private String name_dev;
    private String details;

    private int app_info_id;
    private int app_status;

    public int getApp_info_id() {
        return app_info_id;
    }

    public void setApp_info_id(int app_info_id) {
        this.app_info_id = app_info_id;
    }

    public String getName_app() {
        return name_app;
    }

    public void setName_app(String name_app) {
        this.name_app = name_app;
    }

    public String getName_dev() {
        return name_dev;
    }

    public void setName_dev(String name_dev) {
        this.name_dev = name_dev;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getApp_status() {
        return app_status;
    }

    public void setApp_status(int app_status) {
        this.app_status = app_status;
    }
}
