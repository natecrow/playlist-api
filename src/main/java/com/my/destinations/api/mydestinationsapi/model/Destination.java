package com.my.destinations.api.mydestinationsapi.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Valid
    private Address address;

    @NotEmpty
    private String name;

    @NumberFormat(style = Style.CURRENCY)
    @DecimalMin("00.00")
    private BigDecimal cost;

    private LocalDate dateToVisit;
    private LocalTime timeToVisit;
    private String linkToWebsite;
    private String phoneNumber;
    private String notes;

    @ManyToMany(targetEntity = DestinationsList.class,
                cascade = { CascadeType.DETACH, CascadeType.MERGE,
                            CascadeType.PERSIST, CascadeType.REFRESH }
    )
    @JoinTable(
        name = "DESTINATIONS_LIST_X_DESTINATIONS",
        joinColumns = @JoinColumn(name = "DESTINATION_ID",
                nullable = false,
                updatable = false),
        inverseJoinColumns = @JoinColumn(name = "DESTINATIONS_LIST_ID",
                nullable = false,
                updatable = false),
        foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
        inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT)
    )
    private List<DestinationsList> destinationsLists;

    public Destination() {
    }

    public Destination(final Address address, final String name, final BigDecimal cost, final LocalDate dateToVisit,
            final String linkToWebsite, final String phoneNumber, final String notes) {
        this.address = address;
        this.name = name;
        this.cost = cost;
        this.dateToVisit = dateToVisit;
        this.linkToWebsite = linkToWebsite;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return this.cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDate getDateToVisit() {
        return this.dateToVisit;
    }

    public void setDateToVisit(LocalDate dateToVisit) {
        this.dateToVisit = dateToVisit;
    }

    public LocalTime getTimeToVisit() {
        return this.timeToVisit;
    }

    public void setTimeToVisit(LocalTime timeToVisit) {
        this.timeToVisit = timeToVisit;
    }

    public String getLinkToWebsite() {
        return this.linkToWebsite;
    }

    public void setLinkToWebsite(String linkToWebsite) {
        this.linkToWebsite = linkToWebsite;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<DestinationsList> getDestinationsLists() {
        return this.destinationsLists;
    }

    public void setDestinationsLists(List<DestinationsList> destinationsLists) {
        this.destinationsLists = destinationsLists;
    }

    @Override
    public String toString() {
        return "Destination{" + "id=" + id + ", name='" + name + '\'' + ", cost='" + cost + '\'' + ", dateToVisit='"
                + dateToVisit + '\'' + ", timeToVisit='" + timeToVisit + '\'' + ", linkToWebsite='" + linkToWebsite
                + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", notes='" + notes + '\'' + '}';
    }

    @Override
    public boolean equals(Object other) {

        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Destination otherDestination = (Destination) other;
        if (otherDestination.getId() == this.id && otherDestination.getName().equals(this.name)
                && otherDestination.getCost().equals(this.cost)
                && otherDestination.getDateToVisit().equals(this.dateToVisit)
                && otherDestination.getTimeToVisit().equals(this.timeToVisit)
                && otherDestination.getLinkToWebsite().equals(this.linkToWebsite)
                && otherDestination.getPhoneNumber().equals(this.phoneNumber)
                && otherDestination.getNotes().equals(this.notes)) {
            return true;
        }
        return false;
    }
}
