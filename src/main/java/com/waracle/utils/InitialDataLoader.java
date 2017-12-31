package com.waracle.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.waracle.model.Cake;
import com.waracle.repository.CakeRepository;

@Service
public class InitialDataLoader {

	@Autowired
	CakeRepository repository;
	
	public  void loadsJsonCakeDataFromURL(String url) throws Exception {

        try (InputStream inputStream = new URL("https://gist.githubusercontent.com/hart88/198f29ec5114a3ec3460/raw/8dd19a88f9b8d24c23d9960f3300d0c917a4f07c/cake.json").openStream()) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuffer buffer = new StringBuffer();
            String line = reader.readLine();
            while (line != null) {
                buffer.append(line);
                line = reader.readLine();
            }

            System.out.println("parsing cake json");
            JsonParser parser = new JsonFactory().createParser(buffer.toString());
            if (JsonToken.START_ARRAY != parser.nextToken()) {
                throw new Exception("bad token");
            }

            JsonToken nextToken = parser.nextToken();
            while(nextToken == JsonToken.START_OBJECT) {
                System.out.println("creating cake entity");

                Cake cake = new Cake();
                System.out.println(parser.nextFieldName());
                cake.setName(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cake.setDescription(parser.nextTextValue());

                System.out.println(parser.nextFieldName());
                cake.setImage(parser.nextTextValue());
                
                nextToken = parser.nextToken();
                System.out.println(nextToken);

                nextToken = parser.nextToken();
                System.out.println(nextToken);
                
                try {
                	repository.save(cake); }
                catch (DataIntegrityViolationException ex) {                	
                	System.out.println("IntegrityConstraint voilated" +  ex);
                }
            }

        } catch (Exception ex) {
            throw ex;
        }
	}

}
