package com.wearock.fakeingestor.models.uploadws;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for uploadStatusEnum.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="uploadStatusEnum"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="successful"/&gt;
 *     &lt;enumeration value="failed"/&gt;
 *     &lt;enumeration value="in_progress"/&gt;
 *     &lt;enumeration value="DELAYED"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 *
 */
@XmlType(name = "uploadStatusEnum")
@XmlEnum
public enum UploadStatusEnum {

    @XmlEnumValue("successful")
    SUCCESSFUL("successful"),
    @XmlEnumValue("failed")
    FAILED("failed"),
    @XmlEnumValue("in_progress")
    IN_PROGRESS("in_progress"),
    DELAYED("DELAYED");
    private final String value;

    UploadStatusEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static UploadStatusEnum fromValue(String v) {
        for (UploadStatusEnum c: UploadStatusEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
