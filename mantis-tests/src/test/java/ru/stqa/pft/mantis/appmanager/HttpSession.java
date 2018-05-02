package ru.stqa.pft.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HttpSession {
  private CloseableHttpClient httpclient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app;
    // Создаем объект, который будет отправлять запросы на сервер
    httpclient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
  }

  public boolean login(String username, String password) throws IOException {
    // создаем пустой запрос типа post
    HttpPost post = new HttpPost(app.getProperty("web.baseUrl") + "/login.php");
    // формируем набор параметров
    List<NameValuePair> params = new ArrayList<>();
    params.add(new BasicNameValuePair("username", username));
    params.add(new BasicNameValuePair("password", password));
    params.add(new BasicNameValuePair("secure_session", "on"));
    params.add(new BasicNameValuePair("return", "index.php"));
    // упаковываем наборы и помещаем в ранее созданный запрос
    post.setEntity(new UrlEncodedFormEntity(params));
    // отправляем запрос и получаем ответ (response)
    CloseableHttpResponse response = httpclient.execute(post);
    // получаем ответ
    String body = getTextFrom(response);
    // проверяем, успешно ли пользователь вошел в систему, а именно, есть ли в исходном коде странице часть кода, соответствующая значению "<span class="italic">%s</span>"
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  // проверка, какой пользователь сейчас авторизован
  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet(app.getProperty("web.baseUrl") + "/index.php");
    CloseableHttpResponse response = httpclient.execute(get);
    String body = getTextFrom(response);
    return body.contains(String.format("<span class=\"italic\">%s</span>", username));
  }
}
