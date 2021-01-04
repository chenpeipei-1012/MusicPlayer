package dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entity.Music;

public interface BandOrderDao {

    //�¸�� ���ϴ�ʱ��
	List<Music> orderByUpdata() throws SQLException;
	
	//�ղذ� ���ղ�����
	//public List<Music> orderByCollect() throws SQLException;
	
	//���ذ� �����ش���
	//public List<Music> orderByDownlod() throws SQLException;
	
}
