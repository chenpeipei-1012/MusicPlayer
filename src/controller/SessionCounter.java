package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * �����û�ͳ��
 * @author ��ΪMateBook 13
 *
 */
public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

    //��ȡ���session����(��������)
    public static int getActiveSessions() {
        return activeSessions;
    }
	
    // Session����ʱִ��
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		System.out.println("========  Session����  =======");
	}

	// Session����ʱִ��
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > 0){
            activeSessions--;
            System.out.println("========  Session����  =======");
		}
	}

}
