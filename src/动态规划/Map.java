package ��̬�滮;
//mapping.txt����
public class Map {
	private String ochar;//�磬A,B,C...
	private String[] newChar;//�� GCU,GCC,GCA,GCG
	private int charSize;//newChar����ĳ���
	
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
	
	public void setStringSize(int i)//���������С
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
	public void setNewChar(int i,String a)//����i�����Ϊa
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
