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
 * 图像处理类
 * @author 华为MateBook 13
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
			//得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
			String savePath = this.getServletContext().getRealPath("/musicCloud/userPic/");

			// 文件上传组件
	        ServletFileUpload upload = new ServletFileUpload();
	        upload.setHeaderEncoding("UTF-8");

	        // 得到文件项的迭代器，里面保存FileItemStream对象
	        FileItemIterator iter = upload.getItemIterator(request);
	        
	        // 前缀：用户名
	        String prefix = "chenpeipei";
	        
	        picPath = FileUtil.uploadFileToProject(iter,savePath,prefix); 
		}
		
		PrintWriter out = response.getWriter();
        // JSON对象
		JSONObject json = new JSONObject();
		json.put("picPath", picPath);
		
		out.print(json);
		
		// 把数据响应给AJAX
		out.flush();
        out.close();
	}
}
