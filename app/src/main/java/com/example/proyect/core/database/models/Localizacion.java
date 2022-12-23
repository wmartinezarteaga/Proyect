package com.example.proyect.core.database.models;

import com.fasterxml.jackson.annotation.*;

public class Localizacion {
    private String status;
    private String country;
    private String countryCode;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private double lat;
    private double lon;
    private String timezone;
    private String isp;
    private String org;
    private String as;
    private String query;

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }

    @JsonProperty("country")
    public String getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(String value) { this.country = value; }

    @JsonProperty("countryCode")
    public String getCountryCode() { return countryCode; }
    @JsonProperty("countryCode")
    public void setCountryCode(String value) { this.countryCode = value; }

    @JsonProperty("region")
    public String getRegion() { return region; }
    @JsonProperty("region")
    public void setRegion(String value) { this.region = value; }

    @JsonProperty("regionName")
    public String getRegionName() { return regionName; }
    @JsonProperty("regionName")
    public void setRegionName(String value) { this.regionName = value; }

    @JsonProperty("city")
    public String getCity() { return city; }
    @JsonProperty("city")
    public void setCity(String value) { this.city = value; }

    @JsonProperty("zip")
    public String getZip() { return zip; }
    @JsonProperty("zip")
    public void setZip(String value) { this.zip = value; }

    @JsonProperty("lat")
    public double getLat() { return lat; }
    @JsonProperty("lat")
    public void setLat(double value) { this.lat = value; }

    @JsonProperty("lon")
    public double getLon() { return lon; }
    @JsonProperty("lon")
    public void setLon(double value) { this.lon = value; }

    @JsonProperty("timezone")
    public String getTimezone() { return timezone; }
    @JsonProperty("timezone")
    public void setTimezone(String value) { this.timezone = value; }

    @JsonProperty("isp")
    public String getISP() { return isp; }
    @JsonProperty("isp")
    public void setISP(String value) { this.isp = value; }

    @JsonProperty("org")
    public String getOrg() { return org; }
    @JsonProperty("org")
    public void setOrg(String value) { this.org = value; }

    @JsonProperty("as")
    public String getAs() { return as; }
    @JsonProperty("as")
    public void setAs(String value) { this.as = value; }

    @JsonProperty("query")
    public String getQuery() { return query; }
    @JsonProperty("query")
    public void setQuery(String value) { this.query = value;}
}