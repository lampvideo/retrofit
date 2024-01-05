package com.example.myapplication;
import com.google.gson.annotations.SerializedName;
public class SystemSettings {
    private String id;
    private String server_revision;
    private String musicbox_revision;
    public String getId()
    {
        return id;
    }
    public String getMusicboxRevision()
    {
        return musicbox_revision;
    }
    public String getServerRevision() { return server_revision; }
}
