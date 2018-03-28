package 动态规划;
//mapping.txt数据
public class Map {
	private String ochar;//如，A,B,C...
	private String[] newChar;//如 GCU,GCC,GCA,GCG
	private int charSize;//newChar数组的长度
	
	Map(){}
	public void disp()
	{
		System.out.print(ochar);
		System.out.print(':');
		int i;
		for(i=0;i<charSize;i++)
		{
			System.out.print(newChar[i]);
			System.out.print(' ');
		}
		System.out.print('\n');
	}
	
	public void setStringSize(int i)//传入数组大小
	{
		if(i==0)
		{
			this.charSize=0;
			this.newChar=null;
		}
		else
		{
		this.charSize=i;
		this.newChar=new String[i];
		}
	}
	public String getOchar() {
		return ochar;
	}
	public void setOchar(String ochar) {
		this.ochar = ochar;
	}
	public String[] getNewChar() {
		return newChar;
	}
	public void setNewChar(int i,String a)//将第i个添加为a
	{
		this.newChar[i]=a;
	}
	public int getCharSize() {
		return charSize;
	}
	public void setCharSize(int charSize) {
		this.charSize = charSize;
	}
	

}
