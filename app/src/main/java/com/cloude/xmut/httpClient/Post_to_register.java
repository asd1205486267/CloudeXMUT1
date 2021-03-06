package com.cloude.xmut.httpClient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class Post_to_register {

    private String url = "http://47.97.160.39:8080/Server_of_Client/RegisterServlet";
    //服务器返回的结果
    String result = "";

    /**
     * 使用Post方式向服务器发送请求并返回响应
     * @param username 传递给服务器的username
     * @param password 传递给服务器的password
     * @return
     */
    public String doPost(String username, String password) throws IOException {
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        //将username与password参数装入List中
        NameValuePair param1 = new BasicNameValuePair("username", username);
        NameValuePair param2 = new BasicNameValuePair("password", password);
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(param1);
        params.add(param2);
        //将参数包装如HttpEntity中并放入HttpPost的请求体中
        HttpEntity httpEntity = new UrlEncodedFormEntity(params, "GBK");
        httpPost.setEntity(httpEntity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        //如果响应成功
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
            //得到信息体
            HttpEntity entity = httpResponse.getEntity(); //获取响应对象，对象包含响应内容
            InputStream inputStream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));//缓冲
            String readLine = null;
            while((readLine = br.readLine()) != null){
                result += readLine;
            }
            inputStream.close();
            return result;
        }
        //响应失败
        else{
            return "Error";
        }
    }
}
