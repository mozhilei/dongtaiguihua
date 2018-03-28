package 动态规划;

public class Layer {

	private City[] cityList;//存储每一个层次的城市
	private int cityNum;//记录城市数量=最大下标编号+1
	
	/**
	 * 得到下标为i的城市
	 * @param 下标为i
	 * @return
	 */
	public City getCity(int i)//得到下标为i的城市
	{
		return cityList[i];
	}
	/**
	 * 直接返回城市列表City[]
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
	 * 得到这个layer的城市数
	 * @return
	 */
	public int getCityNum() {
		return cityNum;
	}
	
	public void setCityNum(int cityNum) {
		this.cityNum = cityNum;
	}
	
}
