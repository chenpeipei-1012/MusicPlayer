package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.util.Streams;

public class FileUtil {

	// 文件上传到资源目录
	public static String uploadFileToProject(FileItemIterator iter,String savePath,String prefix){
		String filePath = "";
		
		// int i = 0;
        try {
            
            while(iter.hasNext()){
                // i++;
                // 获取文件输入流
                FileItemStream fileItemStream = iter.next();
                InputStream in = null;
                OutputStream out = null;

                try {
                    String fileName = fileItemStream.getName();
                    System.out.println("浏览器中的文件名：" + fileName);
                    
                    // 更改文件名： 用户名_事件戳
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    fileName = prefix + "_" + System.currentTimeMillis() + suffix;
                    
                    System.out.println("更改后的的文件名：" + fileName);
                    // fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + i + fileName.substring(fileName.lastIndexOf("."));
                    File file = new File(savePath + "\\" + fileName);
                    
                    // 打开输入流
                    in = fileItemStream.openStream();
                    ByteArrayOutputStream bstream = new ByteArrayOutputStream();
                    
                    // 复制流
                    Streams.copy(in, bstream, true);
                    // 新建文件输出流
                    out = new FileOutputStream(file);
                    bstream.writeTo(out);
                    
                    filePath = savePath.substring(savePath.indexOf("musicCloud")) + "\\" + fileName;
                    System.out.println("浏览器中的文件名：" + filePath);
                } catch (IOException e) {
                    throw new RuntimeException("file copy error!",e);
                } finally{
                	// 关闭流对象
                    if(in != null){
                        try {
                            in.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    if(out != null){
                        try {
                            out.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        } catch (Exception e) {
            throw new RuntimeException("upload file error.",e);
        }
		
		return filePath;
	}
}
