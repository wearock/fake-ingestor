package com.wearock.fakeingestor.models.uploadws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for patientResponsibleLink complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="patientResponsibleLink"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="patientId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="responsibleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "patientResponsibleLink", propOrder = {
        "id",
        "patientId",
        "responsibleId"
})
public class PatientResponsibleLink {

    protected String id;
    protected String patientId;
    protected String responsibleId;

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
     * Gets the value of the patientId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Sets the value of the patientId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPatientId(String value) {
        this.patientId = value;
    }

    /**
     * Gets the value of the responsibleId property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getResponsibleId() {
        return responsibleId;
    }

    /**
     * Sets the value of the responsibleId property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setResponsibleId(String value) {
        this.responsibleId = value;
    }

}
