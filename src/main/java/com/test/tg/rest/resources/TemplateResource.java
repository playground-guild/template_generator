package com.test.tg.rest.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.tg.pebble.TemplateProcessor;

@Path("/template/{template}")
public class TemplateResource {

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateTemplate(
		@PathParam("template") String template, String body) 
	{
		System.err.println("Template: " + template);
		System.err.println(body);

		TemplateProcessor templateProcessor = new TemplateProcessor();
		templateProcessor.setupTemplate(template, body);

		return Response.status(200).build();
	}
}
