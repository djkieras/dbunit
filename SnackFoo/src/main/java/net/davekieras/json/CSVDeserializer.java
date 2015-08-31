package net.davekieras.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

public class CSVDeserializer extends JsonDeserializer<List<String>> {

	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		String csvString = jp.readValueAs(String.class);
		List<String> result = new ArrayList<>();
		CsvMapper mapper = new CsvMapper();
		MappingIterator<String> it = mapper.readerFor(String.class).readValues(csvString);
		while (it.hasNext()) {
			result.add(it.next().trim());
		}
		return result;
	}

}
