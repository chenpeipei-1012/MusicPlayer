package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MusicDao;
import dao.impl.MusicDaoImpl;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/file")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileRelPath = request.getParameter("fileName");
		String musicId = request.getParameter("musicId");
		
		
		String fileAbsPath = this.getServletContext().getRealPath(fileRelPath);
		System.out.println("fileName:" + fileRelPath);
		
	    File file = new File(fileAbsPath);
	    
	    String fileName = fileRelPath.substring(fileRelPath.lastIndexOf("/") + 1);
	    System.out.println("fileName:" + fileName);
	    
	    fileName = new String(fileName.getBytes(), "ISO-8859-1");
	    response.setContentType("application/octet-stream; charset=utf-8");
	    response.setHeader("Content-Disposition","attachment;filename=" + fileName);
	    response.setContentLength((int) file.length());
	    
	    FileInputStream fis = null;
	    try {
	        fis = new FileInputStream(file);
	        byte[] buffer = new byte[128];
	        int count = 0;
	        while ((count = fis.read(buffer)) > 0) {
	        	response.getOutputStream().write(buffer, 0, count);
	        }
	        
	        // 下载成功后，记录下载信息
	        MusicDao musicDao = new MusicDaoImpl();
	        musicDao.addMusicDownloadRecord(Integer.parseInt(musicId));
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    	response.getOutputStream().flush();
	    	response.getOutputStream().close();
	        fis.close();
	    }
	}

}
