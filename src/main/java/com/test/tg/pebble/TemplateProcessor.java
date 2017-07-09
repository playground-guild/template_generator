package com.test.tg.pebble;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

public class TemplateProcessor {

	private static String GENERATED_DIR = "webapps/generated";
	
	public void setupTemplate(String page, String body) {
		
		PebbleEngine engine = new PebbleEngine.Builder().build();
		PebbleTemplate compiledTemplate;
		Writer writer = new StringWriter();
		
		String output = "";
		
		try {
			
			compiledTemplate = engine.getTemplate("templates/" + page + ".html");
			
			System.err.println("Compiled template: " + compiledTemplate.getName());
			
//			Map<String, Object> context = new HashMap<>();
//			context.put("name", "Mitchell");

			compiledTemplate.evaluate(writer, buildTemplateContext(page, body));

			output = writer.toString();
			
		} catch (PebbleException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String outDir = System.getProperty("catalina.home") + "/" + GENERATED_DIR;
		String outFile = outDir + "/" + page + ".html";
		
		System.err.println(outFile);
		
		if (new File(outFile).exists()) {
			new File(outFile).delete();
		}
		
		try {
			
			if (! new File(outDir).exists()) {
				new File(outDir).mkdirs();
			}
			
			new File(outFile).createNewFile();
			try (PrintWriter out = new PrintWriter(outFile)) {
			    out.println(output);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	private Map<String, Object> buildTemplateContext(String template, String body) {
		
		Map<String, Object> context = new HashMap<>();
		
		System.err.println(template + " ~ " + template.toUpperCase());
		
		switch(template.toUpperCase()) {
			
			case "HEADER_TAB":
			case "HEADER_NESTED_TAB":
				
				JsonObject json = new Gson().fromJson(body, JsonObject.class);
				
				System.err.println(json.get("title"));
				System.err.println(json.get("tabs"));
				
				context.put("title", json.get("title").toString().replaceAll("\"", ""));
				context.put("name", json.get("name").toString().replaceAll("\"", ""));
				
				ArrayList<String> tabs = new ArrayList<String>();
				
				for (JsonElement item : json.get("tabs").getAsJsonArray()) {
					tabs.add(item.toString().replaceAll("\"", ""));
				}
				
				context.put("tabs", tabs);
				
				break;
				
			case "HEADER_TAB_CAROUSEL":
				
				json = new Gson().fromJson(body, JsonObject.class);
				
				System.err.println(json.get("title"));
				System.err.println(json.get("tabs"));
				
				context.put("title", json.get("title").toString().replaceAll("\"", ""));
				context.put("name", json.get("name").toString().replaceAll("\"", ""));
				
				tabs = new ArrayList<String>();
				
				for (JsonElement item : json.get("tabs").getAsJsonArray()) {
					tabs.add(item.toString().replaceAll("\"", ""));
				}
				
				context.put("tabs", tabs);
				
				ArrayList<HashMap<String, String>> carousel = new ArrayList<>();
				
				for (JsonElement item : json.get("carousel").getAsJsonArray()) {
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					HashMap<String, String> itemObj = new HashMap<>();
					
					
					itemObj.put("img_src", itemJSON.get("img_src").toString().replaceAll("\"", ""));
					itemObj.put("img_alt", itemJSON.get("img_alt").toString().replaceAll("\"", ""));
					itemObj.put("img_caption", itemJSON.get("img_caption").toString().replaceAll("\"", ""));
					
					carousel.add(itemObj);
				}
				
				context.put("carousel", carousel);
				
				break;
				
			case "HEADER_TAB_CAROUSEL_SPEAKERS":
				
				json = new Gson().fromJson(body, JsonObject.class);
				
				System.err.println(json.get("title"));
				System.err.println(json.get("tabs"));
				
				context.put("title", json.get("title").toString().replaceAll("\"", ""));
				context.put("name", json.get("name").toString().replaceAll("\"", ""));
				
				tabs = new ArrayList<String>();
				
				for (JsonElement item : json.get("tabs").getAsJsonArray()) {
					tabs.add(item.toString().replaceAll("\"", ""));
				}
				
				context.put("tabs", tabs);
				
				carousel = new ArrayList<>();
				
				for (JsonElement item : json.get("carousel").getAsJsonArray()) {
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					HashMap<String, String> itemObj = new HashMap<>();
					
					
					itemObj.put("img_src", itemJSON.get("img_src").toString().replaceAll("\"", ""));
					itemObj.put("img_alt", itemJSON.get("img_alt").toString().replaceAll("\"", ""));
					itemObj.put("img_caption", itemJSON.get("img_caption").toString().replaceAll("\"", ""));
					
					carousel.add(itemObj);
				}
				
				context.put("carousel", carousel);
				
				ArrayList<HashMap<String, String>> speakers = new ArrayList<>();
				
				for (JsonElement item : json.get("speakers").getAsJsonArray()) {
					
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					HashMap<String, String> itemObj = new HashMap<>();
					
					
					itemObj.put("img_src", itemJSON.get("img_src").toString().replaceAll("\"", ""));
					itemObj.put("img_alt", itemJSON.get("img_alt").toString().replaceAll("\"", ""));
					itemObj.put("company", itemJSON.get("company").toString().replaceAll("\"", ""));
					itemObj.put("designation", itemJSON.get("designation").toString().replaceAll("\"", ""));
					
					speakers.add(itemObj);
				}
				
				context.put("speakers", speakers);
				break;
			
				
			case "HEADER_TAB_CAROUSEL_SPEAKERS_AGENDA":
				
				json = new Gson().fromJson(body, JsonObject.class);
				
				System.err.println(json.get("title"));
				System.err.println(json.get("tabs"));
				
				context.put("title", json.get("title").toString().replaceAll("\"", ""));
				context.put("name", json.get("name").toString().replaceAll("\"", ""));
				
				tabs = new ArrayList<String>();
				
				for (JsonElement item : json.get("tabs").getAsJsonArray()) {
					tabs.add(item.toString().replaceAll("\"", ""));
				}
				
				context.put("tabs", tabs);
				
				carousel = new ArrayList<>();
				
				for (JsonElement item : json.get("carousel").getAsJsonArray()) {
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					HashMap<String, String> itemObj = new HashMap<>();
					
					
					itemObj.put("img_src", itemJSON.get("img_src").toString().replaceAll("\"", ""));
					itemObj.put("img_alt", itemJSON.get("img_alt").toString().replaceAll("\"", ""));
					itemObj.put("img_caption", itemJSON.get("img_caption").toString().replaceAll("\"", ""));
					
					carousel.add(itemObj);
				}
				
				context.put("carousel", carousel);
				
				speakers = new ArrayList<>();
				
				for (JsonElement item : json.get("speakers").getAsJsonArray()) {
					
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					HashMap<String, String> itemObj = new HashMap<>();
					
					
					itemObj.put("img_src", itemJSON.get("img_src").toString().replaceAll("\"", ""));
					itemObj.put("img_alt", itemJSON.get("img_alt").toString().replaceAll("\"", ""));
					itemObj.put("company", itemJSON.get("company").toString().replaceAll("\"", ""));
					itemObj.put("designation", itemJSON.get("designation").toString().replaceAll("\"", ""));
					
					speakers.add(itemObj);
				}
				
				context.put("speakers", speakers);
				
				HashMap<String, HashMap<String, String>> agenda = new LinkedHashMap<>();
				
				for (JsonElement item : json.get("agenda").getAsJsonArray()) {
					
					System.err.println(item);
					JsonObject itemJSON = item.getAsJsonObject();
					
					String date = itemJSON.get("date").toString().replaceAll("\"", "");
					LinkedHashMap<String, String> events = new LinkedHashMap<String, String>();
					
					for (JsonElement eventJSON : itemJSON.getAsJsonArray("events")) {
						JsonObject eventJsonObject = eventJSON.getAsJsonObject();
						
						events.put(eventJsonObject.get("time").toString().replaceAll("\"", ""),
								eventJsonObject.get("label").toString().replaceAll("\"", ""));
					}
					
					agenda.put(date, events);
				}
				
				context.put("agenda", agenda);
				
				break;
		}
		
		return context;
	}
}
