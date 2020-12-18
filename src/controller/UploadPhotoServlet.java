package controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

import util.FileUtil;

/**
 * ͼ������
 * @author ��ΪMateBook 13
 *
 */
@WebServlet("/uploadPhoto")
public class UploadPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       
    public UploadPhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String picPath = "";
		if(isMultipart){
			//�õ��ϴ��ļ��ı���Ŀ¼�����ϴ����ļ������WEB-INFĿ¼�£����������ֱ�ӷ��ʣ���֤�ϴ��ļ��İ�ȫ
			String savePath = this.getServletContext().getRealPath("/musicCloud/userPic/");

			// �ļ��ϴ����
	        ServletFileUpload upload = new ServletFileUpload();
	        upload.setHeaderEncoding("UTF-8");

	        // �õ��ļ���ĵ����������汣��FileItemStream����
	        FileItemIterator iter = upload.getItemIterator(request);
	        
	        // ǰ׺���û���
	        String prefix = "chenpeipei";
	        
	        picPath = FileUtil.uploadFileToProject(iter,savePath,prefix); 
		}
		
		PrintWriter out = response.getWriter();
        // JSON����
		JSONObject json = new JSONObject();
		json.put("picPath", picPath);
		
		out.print(json);
		
		// ��������Ӧ��AJAX
		out.flush();
        out.close();
	}
}
