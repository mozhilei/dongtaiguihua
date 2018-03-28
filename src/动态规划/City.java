package ��̬�滮;

//ÿһ��Layer���ж��City
public class City {
	
	static private final int avalue=0;
	static private final int cvalue=1;
	static private final int gvalue=2;
	static private final int uvalue=3;//����A, G,C,U
	
	//���н�㣬һ���������ж�����С�
	private String xulie;//��ĸ���У���AUG������
	private int numPoint;//�����trans�ж�Ӧ���С��к��롣
	private int bianhao;//���
	
	private int mindis;//��ǰ���·��
	private int nextheap;//���·����Ӧ����һ�� ����layer[i+1]���ǵڼ���
		
	public City()
	{
		nextheap=-1;//��ʼ��ͨ
	}
	/**
	 * �õ���̡�������
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
	 * ��������trans�ж�Ӧ���С��к��롣
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
	 * @return�����trans�ж�Ӧ���С��к���
	 */
	public int getNumPoint() {
		return numPoint;
	}
	public void disp()
	{
		System.out.println("xulie="+xulie+" numPoint="+numPoint);
	}

	
}
