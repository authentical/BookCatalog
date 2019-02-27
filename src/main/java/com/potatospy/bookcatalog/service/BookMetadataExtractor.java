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


// BookMetadataExtractor extracts these from book before the app stores the book in book list and
// database saves the book:
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

        InputStream inputStream=null;

        try{

            // Apache Tika boilerplate code to parse each file and extract metadata
            inputStream = new FileInputStream(file);
            Parser parser = new AutoDetectParser();
            BodyContentHandler handler = new BodyContentHandler(new StringWriter());
            ParseContext context = new ParseContext();

            parser.parse(inputStream, handler, metadata, context);

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
        } finally{
            if(inputStream!=null){
                try {
                    inputStream.close();
                }catch(IOException e){
                    log.info("Could not close inputStream");
                    e.printStackTrace();
                }
            }
        }


        return metadata;
    }
}
