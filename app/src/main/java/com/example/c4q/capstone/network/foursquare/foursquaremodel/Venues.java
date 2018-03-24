package com.example.c4q.capstone.network.foursquare.foursquaremodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Venues {
    @SerializedName("hasPerk")
    private boolean hasPerk;
    @SerializedName("venueChains")
    private List<String> venueChains;
    @SerializedName("referralId")
    private String referralId;
    @SerializedName("hereNow")
    private HereNow hereNow;
    @SerializedName("venuePage")
    private VenuePage venuePage;
    @SerializedName("specials")
    private Specials specials;
    @SerializedName("beenHere")
    private BeenHere beenHere;
    @SerializedName("allowMenuUrlEdit")
    private boolean allowMenuUrlEdit;
    @SerializedName("menu")
    private Menu menu;
    @SerializedName("hasMenu")
    private boolean hasMenu;
    @SerializedName("url")
    private String url;
    @SerializedName("stats")
    private Stats stats;
    @SerializedName("verified")
    private boolean verified;
    @SerializedName("categories")
    private List<Categories> categories;
    @SerializedName("location")
    private Location location;
    @SerializedName("contact")
    private Contact contact;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public boolean getHasPerk() {
        return hasPerk;
    }

    public List<String> getVenueChains() {
        return venueChains;
    }

    public String getReferralId() {
        return referralId;
    }

    public HereNow getHereNow() {
        return hereNow;
    }

    public VenuePage getVenuePage() {
        return venuePage;
    }

    public Specials getSpecials() {
        return specials;
    }

    public BeenHere getBeenHere() {
        return beenHere;
    }

    public boolean getAllowMenuUrlEdit() {
        return allowMenuUrlEdit;
    }

    public Menu getMenu() {
        return menu;
    }

    public boolean getHasMenu() {
        return hasMenu;
    }

    public String getUrl() {
        return url;
    }

    public Stats getStats() {
        return stats;
    }

    public boolean getVerified() {
        return verified;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public Location getLocation() {
        return location;
    }

    public Contact getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
