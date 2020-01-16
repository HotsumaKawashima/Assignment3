package com.derrick.park.assignment3_contacts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact {

    public Contact() {}
    public Contact(String name, String phone) {
        this.name = new Name(name);
        String[] arr = phone.split("");
        this.cell = arr[1] + arr[2] + arr[3] + "-" + arr[4] + arr[5] + arr[6] + "-" + arr[7] + arr[8] + arr[9] + arr[10];
        System.out.println(this.cell);
    }

    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cell")
    @Expose
    private String cell;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s", name, cell);
    }

    /**
     * Name {first: , last: }
     */
    public class Name {

        public Name() { }
        public Name(String name) {
            String[] arr = name.split("\\s");
            first = arr[0];
            last = arr[1];
        }

        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        @Override
        public String toString() {
            return first + " " + last;
        }
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    public class Location {
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getPostcode() {
            return postcode;
        }

        @Override
        public String toString() {
            return city + ", " + province + " Canada " + postcode;
        }
    }
}

