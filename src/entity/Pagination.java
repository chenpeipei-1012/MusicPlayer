package entity;

/**
 * 封装分页信息
 * @author 华为MateBook 13
 *
 */
public class Pagination {

	// 每页条数
	private int pageSize;
	// 总记录条数
	private int total;
	// 当前页
	private int curPage;
	
	public Pagination(){
		// 默认为10条
		this.pageSize = 10;
	}
	
	// 总记录条数 / 每页条数 
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
 
	// 得到最后一页的开始
    public int getLast(){
        int last = -1;
        // 假设总数是50，是能够被5整除的，那么最后一页的开始就是45
//        if (0 == total % pageSize)
//            last = total - pageSize;
//            // 假设总数是51，不能够被5整除的，那么最后一页的开始就是50
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
	
	// 通过当前页和每页条数得到起始记录号
	public int getCurStartRow(){
		return (curPage - 1) * pageSize;
	}
	// (curPage - 1) * pageSize
    
}
