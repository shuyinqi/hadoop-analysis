package com.mapbar.analyzelog.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HttpUtil
{
  public static byte[] doHttpRequest(String strURL, String[] args, String encode)
  {
    byte[] responseByte = new byte[0];
    if ((strURL != null) && (strURL.trim().length() > 0)) {
      try
      {
        URL url = new URL(strURL);

        URLConnection uc = url.openConnection();

        if (!(uc instanceof HttpURLConnection)) {
          responseByte = null;
        }
        else {
          uc.setDoOutput(true);

          uc.setUseCaches(false);

          uc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

          uc.setReadTimeout(500000);

          uc.setConnectTimeout(500000);

          String content = "";
          if ((args != null) && (args.length > 1)) {
            if ((encode == null) || (encode.trim().length() == 0))
              encode = "GBK";
            StringBuffer strParameter = new StringBuffer();
            int intArgs = args.length;
            for (int i = 0; i < intArgs; i++)
            {
              String encodedItem = "";
              try {
                encodedItem = URLEncoder.encode(args[i], encode);
              }
              catch (Exception e) {
                e.printStackTrace();
              }
              strParameter.append(encodedItem);
              if (i % 2 == 0)
                strParameter.append("=");
              else {
                strParameter.append("&");
              }
            }
            strParameter.setLength(strParameter.length() - 1);
            content = strParameter.toString();
            System.out.println("content=" + content);

            uc.setRequestProperty("Content-Length", "" + content.length());
          }
          else {
            uc.setRequestProperty("Content-Length", "0");
          }

          HttpURLConnection hc = (HttpURLConnection)uc;

          hc.setRequestMethod("POST");

          if (args.length > 1) {
            OutputStream os = uc.getOutputStream();
            DataOutputStream dos = new DataOutputStream(os);
            dos.writeBytes(content);
            dos.flush();
            dos.close();
          }

          ByteArrayOutputStream baos = new ByteArrayOutputStream();
          BufferedInputStream bis = new BufferedInputStream(hc.getInputStream());

          byte[] bContent = new byte[102400];
          int iLen;
          while ((iLen = bis.read(bContent, 0, bContent.length)) != -1) {
            baos.write(bContent, 0, iLen);
          }
          responseByte = baos.toByteArray();
          bis.close();
        }
      } catch (MalformedURLException e) {
        responseByte = null;
        e.printStackTrace();
      } catch (IOException ex) {
        responseByte = null;
        ex.printStackTrace();
      } catch (Exception e) {
        responseByte = null;
        e.printStackTrace();
      }
    }
    return responseByte;
  }

  public static String httpGetText(Object url, Object... parameter) {
    String code = parameter.length >= 3 ? (String)parameter[1] : "UTF-8";
    BufferedReader reader = null;
    try {
      reader = new BufferedReader(new InputStreamReader(httpGetInputStream(url, parameter), code));

      StringBuffer buf = new StringBuffer();String line = null;
      for (line = null; (line = reader.readLine()) != null; ) {
        buf.append(line).append("\n");
      }
      line = buf.toString();
      return line;
    }
    catch (Exception e)
    {
      System.out.println(e.toString());
    } finally {
      if (reader != null)
        try {
          reader.close();
        } catch (Exception ce) {
        }
    }
    return "";
  }

  public static InputStream httpGetInputStream(Object name, Object[] parameter) {
    int retry = parameter.length >= 1 ? ((Integer)parameter[0]).intValue() : 1;
    String alt = parameter.length >= 2 ? (String)parameter[1] : "";

    for (int i = retry; i >= 0; i--) {
      try {
        URL url = new URL(i > 0 ? name.toString() : alt);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1)");

        conn.setReadTimeout(5000);
        conn.connect();
        return conn.getInputStream();
      } catch (Exception e) {
        System.out.println("   httpGetInputStream 请求异常:   " + e.toString());
      }
    }
    return null;
  }

  public static Object getObject(String strUrl, Object obj)
  {
    Object object = null;
    ObjectOutputStream oos = null;
    ObjectInputStream ois = null;
    if ((strUrl != null) && (strUrl.trim().length() > 0)) {
      try
      {
        URL url = new URL(strUrl);

        HttpURLConnection uc = (HttpURLConnection)url.openConnection();

        uc.setDoOutput(true);
        uc.setDoInput(true);

        uc.setUseCaches(false);
        uc.setRequestProperty("Content-Type", "application/octet-stream");

        uc.setReadTimeout(500000);

        uc.setConnectTimeout(500000);
        uc.setRequestMethod("POST");

        OutputStream os = uc.getOutputStream();
        oos = new ObjectOutputStream(os);
        oos.writeObject(obj);
        oos.flush();
        oos.close();

        ois = new ObjectInputStream(uc.getInputStream());
        object = ois.readObject();
        ois.close();
      }
      catch (MalformedURLException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        if (oos != null)
          try {
            oos.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
        if (ois != null)
          try {
            ois.close();
          } catch (IOException e) {
            e.printStackTrace();
          }
      }
    }
    return object;
  }
}
