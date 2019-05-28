package com.wearock.fakeingestor.models.uploadws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for responsible complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="responsible"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "responsible", propOrder = {
        "id",
        "firstName",
        "lastName",
        "birthDate"
})
public class Responsible {

    protected String id;
    protected String firstName;
    protected String lastName;
    protected String birthDate;

    /**
     * Gets the value of the id property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the firstName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the birthDate property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBirthDate(String value) {
        this.birthDate = value;
    }

}
