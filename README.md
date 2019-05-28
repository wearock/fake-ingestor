# fake-ingestor
A mock version of IBEX Ingestor services, which would benefit for extractor tests by:
1. Save time from server side filling
2. Provide ability to customize writeback XML

Below document will show you how to build the application, and run it for your test.

## build
After you've made sure JDK and Maven are installed, run command below:
> mvn clean package -DskipTests

If everything goes well, you should see ***target/fakeingestor-1.0-jar-with-dependencies.jar*** generated.

## start services
After you've built out the jar package, you can run:
> java -jar target/fakeingestor-1.0-jar-with-dependencies.jar

And this will start the services that intented to run from local (127.0.0.1:9999). You can also specify a customized server address by attaching it to command above, like:
> java -jar target/fakeingestor-1.0-jar-with-dependencies.jar 10.20.0.101:8090

## connect to services
Pretend you're on a Windows VM now, with PMS and IBEX installed. You will need to follow the steps below to start uploading to this mock server:
1. Change the server address from Management Console -> Settings -> Upload
2. Modify the IBEX config.xml by adding node below:
'''
<module name="Maintenance">
    <property name="UseHTTP" value="1" />
</module>
'''

During you test, you can find all uploaded data under _./data/upload/{username}/_, and the writeback xml to return will read from _./data/writeback/{username}.xml_ 
