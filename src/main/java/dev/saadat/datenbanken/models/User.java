package dev.saadat.datenbanken.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Our model of a user.
 *
 * Comes with either constructor or builder approach.
 */
@Entity
@Table(name = "Benutzer")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "address")
    public Long address;


    public User() { }

    public User(String name, Long address) {
        this.name = name;
        this.address = address;
    }


    public static class Builder {

        private User user;


        public Builder() {
            this.user = new User();
        }

        public Builder name(String name) {
            this.user.name = name;
            return this;
        }

        public Builder address(Long address) {
            this.user.address = address;
            return this;
        }

        public User build() {
            return this.user;
        }


        /* End Inner Class */
    }


    /* End */
}