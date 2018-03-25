package com.geocoder;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestServiceClient {
        @Test
        public void testGoogleApiJSON() throws Exception {

            Client client = ClientBuilder.newClient();
            WebTarget target = client.target("http://maps.googleapis.com/maps/api/geocode/json?" +
                            "address=1600+Amphitheatre+Parkway,+Mountain+View,+CA&sensor=false");

            String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
            ObjectMapper obj = new ObjectMapper();
            Response results = obj.readValue(response, Response.class);
            ResultsItem resultItem = (ResultsItem) results.getResults().get(0);
            assertEquals(37.4224082, resultItem.getGeometry().getLocation().getLat());
        }
}
