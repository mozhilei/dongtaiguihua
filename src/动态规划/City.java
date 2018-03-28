package 动态规划;

//每一个Layer中有多个City
public class City {
	
	static private final int avalue=0;
	static private final int cvalue=1;
	static private final int gvalue=2;
	static private final int uvalue=3;//定义A, G,C,U
	
	//城市结点，一个层面上有多个城市。
	private String xulie;//字母序列，如AUG。。。
	private int numPoint;//结点在trans中对应的行、列号码。
	private int bianhao;//编号
	
	private int mindis;//当前最短路径
	private int nextheap;//最短路径对应的下一跳 ，在layer[i+1]中是第几个
		
	public City()
	{
		nextheap=-1;//初始不通
	}
	/**
	 * 得到最短、长距离
	 * @return
	 */
	public int getMindis() {
		return mindis;
	}

	public void setMindis(int mindis) {
		this.mindis = mindis;
	}

	public int getNextheap() {
		return nextheap;
	}

	public void setNextheap(int nextheap) {
		this.nextheap = nextheap;
	}

	/**
	 * 计算结点在trans中对应的行、列号码。
	 * @return
	 */
	public int setNumPoint()
	{
		int total=0;
		int i;
		for(i=0;i<3;i++)
		{
			if(i==0)
				total=total+keyValue(i)*16;
			else if(i==1)
				total=total+keyValue(i)*4;
			else if(i==2)
				total=total+keyValue(i)*1;
		}
		numPoint=total;
		return 0;
	}
	
	public int keyValue(int i)
	{
		char c=xulie.charAt(i);
		if(c=='A')
			return avalue;
		else if(c=='G')
			return gvalue;
		else if(c=='C')
			return cvalue;
		else if(c=='U')
			return uvalue;
		else
		{	
			System.out.println("error in city.java!");
			return -1;
		}
		
	}

	
	public String getXulie() {
		return xulie;
	}

	public void setXulie(String xulie) {
		this.xulie = xulie;
		setNumPoint();
	}

	public int getBianhao() {
		return bianhao;
	}

	public void setBianhao(int bianhao) {
		this.bianhao = bianhao;
	}

	/**
	 * 
	 * @return结点在trans中对应的行、列号码
	 */
	public int getNumPoint() {
		return numPoint;
	}
	public void disp()
	{
		System.out.println("xulie="+xulie+" numPoint="+numPoint);
	}

	
}
