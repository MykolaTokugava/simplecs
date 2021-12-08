package com.citi.gsp.ds.snl.dowloader;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;


import java.io.*;
import java.io.File;
import java.util.stream.Collectors;

@Slf4j
public class Loader {

    public static void main(String[] args) {
        testLoader();
    }

    public static void testLoader() {
        log.info("testLoader started...");
        String content;

        try {
            ClassPathResource resource = null;

            try{
                 resource = new ClassPathResource("test/f.csv");
            } catch (IOException e) {
                log.info("ZOPA ed");
            }

            log.info("resource.getPath(): " + resource.getPath());

            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));


            CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withHeader());


            for (CSVRecord item : csvParser) {
                log.info("record: " +item.toString());
            }


            content = reader.lines().collect(Collectors.joining("\n"));
            log.info("<<>>" + content);
            reader.close();
        } catch (IOException ex) {
            throw new RuntimeException("No read-> ClassPathResource",ex);
        }



    }

}
