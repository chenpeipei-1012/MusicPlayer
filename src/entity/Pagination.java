package entity;

/**
 * ��װ��ҳ��Ϣ
 * @author ��ΪMateBook 13
 *
 */
public class Pagination {

	// ÿҳ����
	private int pageSize;
	// �ܼ�¼����
	private int total;
	// ��ǰҳ
	private int curPage;
	
	public Pagination(){
		// Ĭ��Ϊ10��
		this.pageSize = 10;
	}
	
	// �ܼ�¼���� / ÿҳ���� 
	public int getTotalPage(){
        int totalPage;

        if (0 == total % pageSize)
            totalPage = total / pageSize;
        else
            totalPage = total / pageSize + 1;
 
        if(0 == totalPage)
            totalPage = 1;
        
        return totalPage;
    }
 
	// �õ����һҳ�Ŀ�ʼ
    public int getLast(){
        int last = -1;
        // ����������50�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����45
//        if (0 == total % pageSize)
//            last = total - pageSize;
//            // ����������51�����ܹ���5�����ģ���ô���һҳ�Ŀ�ʼ����50
//        else
//            last = total - total % count;
//        last = last<0?0:last;
        return last;
    }

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	
	// ͨ����ǰҳ��ÿҳ�����õ���ʼ��¼��
	public int getCurStartRow(){
		return (curPage - 1) * pageSize;
	}
	// (curPage - 1) * pageSize
    
}
