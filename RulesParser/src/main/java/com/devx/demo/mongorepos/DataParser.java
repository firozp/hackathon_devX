package com.devx.demo.mongorepos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import com.devx.demo.mongodocs.Rule;

/**
 * This class parses the file into java objects
 *
 */
public class DataParser
{
	public List<Rule> parse(URL url) throws IOException {
		URLConnection urlConnection = url.openConnection();
		try (CSVParser csvParser = new CSVParser(new InputStreamReader(
				urlConnection.getInputStream()),
				CSVFormat.DEFAULT.withDelimiter(','))) {
			return parseRecords(csvParser.getRecords());
		}
	}

	private List<Rule> parseRecords(List<CSVRecord> records) {
		List<Rule> rules = new ArrayList<>();
		Date timestamp = new Date();
		for (CSVRecord csvRecord : records) {
			Rule rule = new Rule(Integer.parseInt(csvRecord.get(0)),
					csvRecord.get(1), csvRecord.get(2), csvRecord.get(3),
					csvRecord.get(4), timestamp);
			rules.add(rule);
		}
		return rules;
	}
}
