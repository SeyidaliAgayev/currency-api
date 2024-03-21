package com.div.currencyapi.dto;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.ToString;

import java.util.List;

@ToString
@XmlRootElement(name = "ValCurs")
public class ValCurs {
    private String date;
    private String name;
    private String description;
    private List<ValType> valTypes;

    @XmlAttribute(name = "Date")
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @XmlAttribute(name = "Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlAttribute(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "ValType")
    public List<ValType> getValTypes() {
        return valTypes;
    }

    public void setValTypes(List<ValType> valTypes) {
        this.valTypes = valTypes;
    }
}
