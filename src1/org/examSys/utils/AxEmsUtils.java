package org.examSys.utils;
  
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class AxEmsUtils {
	
	public static void htmlToWord2(String url,String filename,String path) throws Exception {
		URLConnection connection = new URL(url).openConnection();
        InputStream bodyIs = connection.getInputStream();
        String body = getContent(bodyIs);
        String css = "";
        //拼一个标准的HTML格式文档
        String content = "<html><head><style>" + css + "</style></head><body>" + body + "</body></html>";
        InputStream is = new ByteArrayInputStream(content.getBytes("UTF-8"));
        OutputStream os = new FileOutputStream(path + File.separator + filename);
        inputStreamToWord(is, os);
     }
	
	/**
     * 把输入流里面的内容以UTF-8编码当文本取出。
     * 不考虑异常，直接抛出
     * @param ises
     * @return
     * @throws IOException
     */
    private static String getContent(InputStream... ises) throws IOException {
       if (ises != null) {
          StringBuilder result = new StringBuilder();
          BufferedReader br;
          String line;
          for (InputStream is : ises) {
             br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             while ((line=br.readLine()) != null) {
                 result.append(line);
             }
          }
          return result.toString();
       }
       return null;
    }
     
     /**
      * 把is写入到对应的word输出流os中
      * 不考虑异常的捕获，直接抛出
      * @param is
      * @param os
      * @throws IOException
      */
     private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
        POIFSFileSystem fs = new POIFSFileSystem();
        //对应于org.apache.poi.hdf.extractor.WordDocument
        fs.createDocument(is, "WordDocument");
        fs.writeFilesystem(os);
        os.close();
        is.close();
     }

	/**
	 * 随机生成文件名
	 * @param filename
	 * @return
	 */
	public static String generateFileName(String filename) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		
		String fileExt = filename.substring(filename.lastIndexOf("."));
		String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
		
		return newFileName;
	}
	
	/**
	 * 使用正则抽取html代码中的文本
	 * 
	 * @param str
	 * @return
	 */
	public static String getTextFromHTML(String str) {
		Pattern pattern = Pattern.compile("(</?[^<>]*>)|(\\s*)");
		Matcher matcher = pattern.matcher(str);
		String result = matcher.replaceAll("");
		pattern = Pattern.compile("(&nbsp;)*");
		matcher = pattern.matcher(result);
		result = matcher.replaceAll("");
		return result;
	}
}
