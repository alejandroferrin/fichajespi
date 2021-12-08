/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fichajespi.fichajespidestopapp.httpClient;

import java.io.IOException;
/*
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
 */

import com.fichajespi.fichajespidestopapp.entity.Fichaje;
import com.fichajespi.fichajespidestopapp.entity.NumeroEmpleado;
import com.google.gson.Gson;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/*
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
 */

/**
 *
 * @author alex
 */
public class RequestSender {

  //private static final String uri = "http://localhost:8080/fichaje/now";
  private static final String uri = "http://localhost:8080";

  /*
	 * private static final HttpClient httpClient = HttpClient.newBuilder()
	 * .version(HttpClient.Version.HTTP_2) .connectTimeout(Duration.ofSeconds(10))
	 * .build();
   */
  public Fichaje sendRequest(String numero) throws IOException, InterruptedException {

    Gson g = new Gson();

    NumeroEmpleado numEmpleado = new NumeroEmpleado(numero);

    //String json = g.toJson(numEmpleado);
    
    
    //For jdk 11
    /*
		HttpClient httpClient = HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.connectTimeout(Duration.ofSeconds(10))
				.build();

		HttpRequest request = HttpRequest
				.newBuilder()
				.POST(HttpRequest.BodyPublishers.ofString(json))
				.uri(URI.create(uri))
				.setHeader("User-Agent", "FichajesPi Desktop App") // add request header
				.header("Content-Type", "application/json")
				.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
		System.out.println(response.body());

		if (Integer.valueOf(response.statusCode()) == 201)
			return g.fromJson(response.body(), Fichaje.class);
    
     */
    
    
    //For JDK 1.8 with apache lib
    /*
    CloseableHttpClient client = HttpClients.createDefault();

    HttpPost httpPost = new HttpPost(uri);

    StringEntity entity = new StringEntity(json);
    httpPost.setEntity(entity);
    //httpPost.setHeader("Accept", "application/json");
    httpPost.setHeader("Content-type", "application/json");

    CloseableHttpResponse response = client.execute(httpPost);
    if (response.getCode() == 201) {
      try {
        return g.fromJson(EntityUtils.toString(response.getEntity()), Fichaje.class);
      } catch (ParseException ex) {
        return null;
      }
    }
    client.close();
     */
    
    
    //Feign client implementation
    FeignController httpClient = Feign.builder()
                                      .encoder (new GsonEncoder())
                                      .decoder(new GsonDecoder())
                                      .target(FeignController.class, uri);

    return httpClient.fichar(numEmpleado);

  }
}
