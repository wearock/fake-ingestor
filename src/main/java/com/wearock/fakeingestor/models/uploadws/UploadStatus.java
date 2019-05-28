package com.wearock.fakeingestor.models.uploadws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.Serializable;


/**
 * <p>Java class for uploadStatus complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="uploadStatus"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="status" type="{http://upload.sesamecommunications.com}uploadStatusEnum"/&gt;
 *         &lt;element name="errorMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "uploadStatus", propOrder = {
        "status",
        "errorMessage",
        "time"
})
public class UploadStatus implements Serializable {

    protected UploadStatusEnum status;
    protected String errorMessage;
    protected XMLGregorianCalendar time;

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link UploadStatusEnum }
     *
     */
    public UploadStatusEnum getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link UploadStatusEnum }
     *
     */
    public void setStatus(UploadStatusEnum value) {
        this.status = value;
    }

    /**
     * Gets the value of the errorMessage property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the value of the errorMessage property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setErrorMessage(String value) {
        this.errorMessage = value;
    }

    /**
     * Gets the value of the time property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setTime(XMLGregorianCalendar value) {
        this.time = value;
    }

}
