package controller;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 在线用户统计
 * @author 华为MateBook 13
 *
 */
public class SessionCounter implements HttpSessionListener {

	private static int activeSessions = 0;

    //获取活动的session个数(在线人数)
    public static int getActiveSessions() {
        return activeSessions;
    }
	
    // Session创建时执行
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		System.out.println("========  Session创建  =======");
	}

	// Session销毁时执行
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		if (activeSessions > 0){
            activeSessions--;
            System.out.println("========  Session销毁  =======");
		}
	}

}
