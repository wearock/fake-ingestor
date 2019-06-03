package com.wearock.fakeingestor;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

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

    public static void gzipDecompress(String gzipFile, String newFile) {
        try {
            FileInputStream fis = new FileInputStream(gzipFile);
            GZIPInputStream gis = new GZIPInputStream(fis);
            FileOutputStream fos = new FileOutputStream(newFile);
            byte[] buffer = new byte[1024];
            int len;
            while((len = gis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            gis.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
