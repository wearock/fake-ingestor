package com.wearock.fakeingestor.services;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebParam;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import com.wearock.fakeingestor.models.uploadws.*;
import com.wearock.fakeingestor.Utils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

@WebService(targetNamespace = "http://upload.sesamecommunications.com", name = "Upload", serviceName = "Upload")
public class Upload {

    UploadStatusEnum uploadStatus;
    String serverAddress;

    @Resource
    private WebServiceContext context;

    public Upload(String server) {
        this.serverAddress = server;
    }

    @WebMethod(operationName = "getCommands")
    @WebResult(name = "command")
    public List<Command> getCommands(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                                     @WebParam(name="satelliteId") Long satelliteId) {
        return Collections.emptyList();
    }

    @WebMethod(operationName="uploadXML")
    public void uploadXML(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                          @WebParam(name="uploadId") String uploadId){

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

        File curDirectory = new File("");
        String pkgPath = String.format("%s\\data\\upload\\%s\\%s.xml.gz", curDirectory.getAbsolutePath(), authToken, df.format(new Date()));
        pkgPath = pkgPath.replace("\\", File.separator);
        String unzipPath = String.format("%s\\data\\upload\\%s\\%s.xml", curDirectory.getAbsolutePath(), authToken, df.format(new Date()));
        unzipPath = unzipPath.replace("\\", File.separator);
        Utils.mkDirs(pkgPath);

        receiveFile(pkgPath);

        // Unzip the package to retrieve uploadId
        Utils.gzipDecompress(pkgPath, unzipPath);
        saveToCache(unzipPath);

        try {
            FileUtils.forceDelete(new File(unzipPath));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    @WebMethod(operationName="getUploadXMLStatus")
    @WebResult(name="uploadStatus")
    public UploadStatus getUploadXMLStatus(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                                           @WebParam(name="partnerId") String partnerId){
        if(uploadStatus == null){
            uploadStatus = UploadStatusEnum.FAILED;
        }

        UploadStatus status = new UploadStatus();
        status.setStatus(uploadStatus);
        status.setErrorMessage("New upload");
        return status;
    }

    @WebMethod(operationName="getUploadXMLAcceptance")
    @WebResult(name="waitTime")
    public Long getUploadXMLAcceptance(@XmlElement(required = true) @WebParam(name="authToken") String authToken){
        return (long) 0;
    }

    @WebMethod(operationName="getConfiguration")
    public byte[] getConfiguration(@XmlElement(required = true) @WebParam(name="authToken") String authToken){
        Configuration config = new Configuration();
        config.setServerUrl(this.serverAddress);
        config.setDbCompatibility(new Configuration.Db_Compatibility());
        config.setModules(new Configuration.Modules());
        return Utils.getJAXMarshalString(config).getBytes();
    }

    @WebMethod(action = "getLastSuccessfulUploadId")
    @WebResult(name = "uploadId")
    public String getLastSuccessfulUploadId(@XmlElement(required = true) @WebParam(name="authToken") String authToken) {
        File curDirectory = new File("");
        String cachePath = (curDirectory.getAbsolutePath() + "\\data\\cache\\" + authToken).replace("\\", File.separator);
        String uploadId = "";
        try {
            File cacheFile = new File(cachePath);
            if (cacheFile.exists()) {
                uploadId = FileUtils.readFileToString(cacheFile);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return uploadId;
    }

    @WebMethod(operationName="updateConfiguration")
    @WebResult(name="configuration")
    public void updateConfiguration(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                                    @WebParam(name="satelliteId") int satelliteId,
                                    @XmlElement(required = true) @WebParam(name="settings") byte[] settings,
                                    @XmlElement(required = true) @WebParam(name="settingsDisplay") byte[] settingsDisplay){
    }

    @WebMethod(operationName="getWritebackXML")
    @WebResult(name="udbf")
    public DataHandler getWritebackXML(@XmlElement(required = true) @WebParam(name="authToken") String authToken){
        File curDirectory = new File("");
        String dataPath = (curDirectory.getAbsolutePath() + "\\data\\writeback\\" + authToken + ".xml").replace("\\", File.separator);
        File xmlFile = new File(dataPath);
        if (!xmlFile.exists()) {
            Utils.mkDirs(dataPath);
            OutputStream writer = null;
            try {
                writer = new FileOutputStream(xmlFile);
                String content = "<udbf-3><service_information/><update><Appointments/></update></udbf-3>";
                writer.write(content.getBytes(), 0, content.length());
            } catch (FileNotFoundException e) {
                System.err.println("abc");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        String xml = "";
        try {
            xml = FileUtils.readFileToString(new File(dataPath));
            return new DataHandler(new ByteArrayDataSource(xml, "application/xml"));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @WebMethod(operationName="uploadModules")
    public void uploadModules(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                              @XmlElement(required = true) @WebParam(name="satelliteId") long satelliteId,
                              @XmlElement(required = true) @WebParam(name="modules") List<Module> modules){
    }

    @WebMethod(operationName="uploadLog")
    public void uploadLog(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                          @XmlElement(required = true) @WebParam(name="satelliteId") long satelliteId,
                          @XmlElement(required = true) @WebParam(name="logEntries") List<LogEntry> logEntries){
    }

    @WebMethod(operationName="uploadMailList")
    public void uploadMailList(@XmlElement(required = true) @WebParam(name="authToken") String authToken,
                               @XmlElement(required = true) @WebParam(name="satelliteId") long satelliteId){
    }

    @WebMethod(action = "getPatientResponsibleLinks")
    @WebResult(name = "patientResponsibleLink")
    public List<PatientResponsibleLink> getPatientResponsibleLinks(@XmlElement(required = true) @WebParam(name="authToken") String authToken) {
        return Collections.emptyList();
    }

    @WebMethod(action = "getPatients")
    @WebResult(name = "patient")
    public List<Patient> getPatients(@XmlElement(required = true) @WebParam(name="authToken") String authToken) {
        return Collections.emptyList();
    }

    @WebMethod(action = "getResponsibles")
    @WebResult(name = "responsible")
    public List<Responsible> getResponsibles(@XmlElement(required = true) @WebParam(name="authToken") String authToken) {
        return Collections.emptyList();
    }

    private void receiveFile(String pkgPath){
        uploadStatus = UploadStatusEnum.SUCCESSFUL;
        System.out.println("Receive file...");
        InputStream dataStream = null;
        OutputStream outputStream = null;
        try {
            final DataHandler dataHandler = getDataHandler();
            dataStream = dataHandler.getInputStream();
            outputStream = new FileOutputStream(pkgPath);
            IOUtils.copy(dataStream, outputStream);
            outputStream.flush();
            System.out.println("Output file: " + pkgPath);
        } catch (Exception e) {
            uploadStatus = UploadStatusEnum.FAILED;
            System.out.println("Couldn't store attached file.");
        } finally {
            IOUtils.closeQuietly(dataStream);
            IOUtils.closeQuietly(outputStream);
        }
    }

    private DataHandler getDataHandler() throws Exception{
        final Map<String, DataHandler> attachments = (Map<String, DataHandler>) context
                .getMessageContext().get(MessageContext.INBOUND_MESSAGE_ATTACHMENTS);

        if (attachments == null || attachments.keySet().size() != 1) {
            System.out.println("Attachment with file not found.");
            throw new Exception("Attachment with file not found.");
        }
        return attachments.get(attachments.keySet().iterator().next());
    }

    private void saveToCache(String fileName) {
        try {
            SAXReader saxReader = new SAXReader();
            Document doc = saxReader.read(new File(fileName));

            Element ePortionDate = (Element) doc.selectSingleNode("//service_information/portion_date");
            String portionDate = ePortionDate.getStringValue();
            portionDate = portionDate.replace('T', '_');
            portionDate = portionDate.replace(':', '_');
            portionDate = portionDate.replace('.', '_');

            Element eUserName = (Element) doc.selectSingleNode("//service_information/username");
            String userName = eUserName.getStringValue();

            String uploadId = String.format("%s_%s", userName, portionDate);
            System.out.println("Save to cache: " + uploadId);

            File curDirectory = new File("");
            String cachePath = (curDirectory.getAbsolutePath() + "\\data\\cache\\" + userName).replace("\\", File.separator);
            Utils.mkDirs(cachePath);

            File cacheFile = new File(cachePath);
            cacheFile.createNewFile();
            FileWriter writer = new FileWriter(cacheFile);
            writer.write(uploadId);
            writer.flush();
            writer.close();
        } catch (Exception de) {
            throw new RuntimeException(de.getMessage(), de);
        }
    }
}
