package ��̬�滮;

public class Layer {

	private City[] cityList;//�洢ÿһ����εĳ���
	private int cityNum;//��¼��������=����±���+1
	
	/**
	 * �õ��±�Ϊi�ĳ���
	 * @param �±�Ϊi
	 * @return
	 */
	public City getCity(int i)//�õ��±�Ϊi�ĳ���
	{
		return cityList[i];
	}
	/**
	 * ֱ�ӷ��س����б�City[]
	 * @return City[]
	 */
	public City[] getCityList() {
		return cityList;
	}
	
	public void setCityList(String[] List,int length) {
		int i;
		this.cityList=new City[length];
		for(i=0;i<length;i++)
		{
			cityList[i]=new City();
			cityList[i].setXulie(List[i]);
			cityList[i].setBianhao(i);
	
//			cityList[i].disp();
		}
		cityNum=length;
	}

	/**
	 * �õ����layer�ĳ�����
	 * @return
	 */
	public int getCityNum() {
		return cityNum;
	}
	
	public void setCityNum(int cityNum) {
		this.cityNum = cityNum;
	}
	
}
