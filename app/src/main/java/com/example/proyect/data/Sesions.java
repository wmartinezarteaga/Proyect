package models;

public class Sesions {

private String date,name,id,client,access;

    public Sesions(String date, String name, String id, String client,String access) {
        this.date = date;
        this.name = name;
        this.id = id;
        this.client = client;
        this.access =access;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
