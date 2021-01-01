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

	// �ļ��ϴ�����ԴĿ¼
	public static String uploadFileToProject(FileItemIterator iter,String savePath,String prefix){
		String filePath = "";
		
		// int i = 0;
        try {
            
            while(iter.hasNext()){
                // i++;
                // ��ȡ�ļ�������
                FileItemStream fileItemStream = iter.next();
                InputStream in = null;
                OutputStream out = null;

                try {
                    String fileName = fileItemStream.getName();
                    System.out.println("������е��ļ�����" + fileName);
                    
                    // �����ļ����� �û���_�¼���
                    String suffix = fileName.substring(fileName.lastIndexOf("."));
                    fileName = prefix + "_" + System.currentTimeMillis() + suffix;
                    
                    System.out.println("���ĺ�ĵ��ļ�����" + fileName);
                    // fileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_" + i + fileName.substring(fileName.lastIndexOf("."));
                    File file = new File(savePath + "\\" + fileName);
                    
                    // ��������
                    in = fileItemStream.openStream();
                    ByteArrayOutputStream bstream = new ByteArrayOutputStream();
                    
                    // ������
                    Streams.copy(in, bstream, true);
                    // �½��ļ������
                    out = new FileOutputStream(file);
                    bstream.writeTo(out);
                    
                    filePath = savePath.substring(savePath.indexOf("musicCloud")) + "\\" + fileName;
                    System.out.println("������е��ļ�����" + filePath);
                } catch (IOException e) {
                    throw new RuntimeException("file copy error!",e);
                } finally{
                	// �ر�������
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
