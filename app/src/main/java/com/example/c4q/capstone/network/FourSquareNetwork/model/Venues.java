package com.example.c4q.capstone.network.FourSquareNetwork.model;

import java.util.List;

public class Venues {
    private boolean hasPerk;
    private List<String> venueChains;
    private String referralId;
    private HereNow hereNow;
    private String storeId;
    private VenuePage venuePage;
    private Specials specials;
    private BeenHere beenHere;
    private boolean allowMenuUrlEdit;
    private Menu menu;
    private boolean hasMenu;
    private String url;
    private Stats stats;
    private boolean verified;
    private List<Categories> categories;
    private Location location;
    private Contact contact;
    private String name;
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

    public String getStoreId() {
        return storeId;
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
