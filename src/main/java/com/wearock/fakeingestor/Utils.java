package com.wearock.fakeingestor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Utils {
    private static final Map<Class<?>, JAXBContext> contextCache = Collections
            .synchronizedMap(new HashMap<Class<?>, JAXBContext>());

    public static void mkDirs(String filePath) {
        File current = new File(filePath);
        if (current.isDirectory()) {
            current.mkdirs();
        } else {
            current.getParentFile().mkdirs();
        }
    }

    public static JAXBContext getJAXBContext(Class<?> clazz) {
        if (!contextCache.containsKey(clazz)) {
            try {
                contextCache.put(clazz, JAXBContext.newInstance(clazz));
            } catch (JAXBException e) {
                throw new RuntimeException(e.getMessage(), e);
            }
        }
        return contextCache.get(clazz);
    }

    public static <T> String getJAXMarshalString(T object) {
        try {
            final Marshaller m = getJAXBContext(object.getClass())
                    .createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            final StringWriter writer = new StringWriter();
            m.marshal(object, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
