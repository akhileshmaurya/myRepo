
package com.example.data.es;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

public class ElasticSearchClient {
  private static ElasticSearchClient es = new ElasticSearchClient();
  private RestClient restClient = null;

  public static ElasticSearchClient getElasticSearchClient() {

    return es;
  }

  private ElasticSearchClient() {
    restClient = RestClient.builder(new HttpHost("localhost", 9200)).build();
  }

  public void disconnect() throws IOException {
    if (es != null && restClient != null)
      restClient.close();
  }

  public Response runQuery(String jsonString) throws IOException {
    Map<String, String> params = Collections.emptyMap();
    HttpEntity entity = new NStringEntity(jsonString, ContentType.APPLICATION_JSON);
    Response response = restClient.performRequest("PUT", "/posts/doc/1", params, entity);
    return response;
  }

}
