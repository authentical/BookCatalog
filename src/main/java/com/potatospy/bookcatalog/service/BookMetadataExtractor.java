package com.potatospy.bookcatalog.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import java.io.*;


// BookMetadataExtractor extracts these from book before the database saves the book:
// creation-date publishedDate
// title bookTitle
// Author author


@Slf4j
@Service
public class BookMetadataExtractor {

    // == Fields ==


    // == Constructor ==

    public BookMetadataExtractor() {

    }


    // == Public methods ==

    public static Metadata extractMetaData(File file) {

        log.info("extractMetaData called");

        Metadata metadata = new Metadata();

        try{

            // Apache Tika boilerplate code to parse each file and extract metadata
            InputStream inputStream = new FileInputStream(file);
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(new StringWriter());
            ParseContext context = new ParseContext();

            parser.parse(inputStream, handler, metadata, context);

            //log.info(handler.toString()); // Dump everything the parser found


//            String[] metadataNames = metadata.names();  // Get array of keys
//            for (String name : metadataNames) {
//                log.info(name + ": " + metadata.get(name));
//            }

//            System.out.println(
//            metadata.get("title")+
//            metadata.get("creator")+
//            metadata.get("date")
//            );



        } catch (SAXException e) {
            System.out.println("#########\n\n\nSAX Exception\n\n\n##########");
            e.printStackTrace();
        } catch (TikaException e) {
            System.out.println("#########\n\n\nTika Exception\n\n\n##########");
            e.printStackTrace();
        }catch(FileNotFoundException e){
            System.out.println("#########\n\n\nFileNotFound Exception\n\n\n##########");
            e.printStackTrace();
        }catch(IOException e){
            System.out.println("#########\n\n\nIO Exception\n\n\n##########");
            e.printStackTrace();
        }


        return metadata;
    }
}
