package com.devx.demo.clients;

import com.devx.demo.mongodocs.Rule;
import com.devx.demo.mongorepos.DataParser;
import com.devx.demo.mongorepos.RulesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.ws.rs.POST;

@RestController
@RequestMapping("/api")
public class RulesRestClient {


    @Autowired
    RulesRepository rulesRepository;

    @RequestMapping("/trigger")
    @POST
    public void trigger(@RequestParam("filename") String filename) throws IOException{
    	URL url = new URL("http://devx-mockserver.devx:18080/static/"+filename);
		DataParser dataParser = new DataParser();
		List<Rule> rules = dataParser.parse(url);
		addRules(rules);
    }

    @RequestMapping("/add/samplerule")
    public void addRules(List<Rule> rules){
    	for (Rule rule : rules) {
    		rulesRepository.save(rule);
		}
    }

}
