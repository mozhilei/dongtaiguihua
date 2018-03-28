package 动态规划;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * 首先读取3个数据文件mapping.txt,protein.txt,trans.txt分别保存在mapList,plist,trans中，
 * 从plist中取一条蛋白质，根据mapList进行转换，得到结果存在layer中，layer中有多个结点，用city表示
 * 根据算法求最大路径
 * @author 主账户
 *
 */
public class Solve {
	private int MapLineMax=10;//要大于mapping.txt最大一行数目
	private int MapListMax=26;//map一共有a-z 26行
	
	private Map[] mapList;//mapping.txt数据
	
	private Protein[] plist;//蛋白质序列
	private int listNum;//蛋白质序列数组长度
	
	private int trans[][]=new int[64][64];//转换矩阵
	
	private Layer[] layer;

	public Solve()//构造函数
	{
		getMappingFile();//读取mapping.txt,protein.txt,trans.txt
		getProteinFile();
		getTransFile();

//		start_guihua();//开始规划
		
//		disp_trans();
//		plist=new Protein[1];//测试蛋白质数据
//		plist[0]=new Protein();
//		plist[0].setFlag("test");
//		plist[0].setOrder("MQVT");
//		listNum=1;		
		guihua(0);//只求第1个蛋白质
//		disp_mapList();
	}
	/**
	 * 输出trans查看结果
	 */
	public void disp_trans()
	{

		int i,j;
		for(i=0;i<64;i++)
		{	for(j=0;j<64;j++)
			{
				System.out.print(trans[i][j]);
				System.out.print(' ');	
			}
		System.out.print('\n');
		}
		
	}
	/**
	 * 调用规划算法求最小值
	 */
	public void start_guihua()
	{
		int i=0;
		for(i=0;i<listNum;i++)
		{
			guihua(i);
		}
	}
	
	/**
	 * 输出mapList,查看是否正确
	 */
	public void disp_mapList()
	{
		int i;
		for(i=0;i<MapListMax;i++)
		{
			System.out.print(i);
			System.out.print('+');
			mapList[i].disp();		
		}
	}

/**
 * 求下标为listNumber的蛋白质的最短路径,控制结果输出格式
 * @param 对应plist中的第listNumber条蛋白质
 * @return
 */
	public int guihua(int listNumber)
	{
//		int wuqiongda=Integer.MAX_VALUE;//近似表示表示正无穷大,求最短路径用
		int wuqiongda=Integer.MIN_VALUE;//近似表示表示负无穷大,求最大路径用
		String protein=plist[listNumber].getOrder();//蛋白质序列
		String flag=plist[listNumber].getFlag();
		int plength=protein.length();
		layer=new Layer[plength];//有k个阶段
		
		//根据mapList中的对于关系，将protein中的每一项翻译成layer中的每一项
		int i,j,k,indexMap;
		char tempChar;
		for(i=0;i<plength;i++)
		{
			layer[i]=new Layer();
			
			tempChar=protein.charAt(i);
			indexMap=tempChar-'A';
			layer[i].setCityList(mapList[indexMap].getNewChar(),mapList[indexMap].getCharSize());
		}
		int mapXY=0,disX,disY;
		int tempX,tempY,panduan=0;
		try{
		FileWriter fw = new FileWriter("guocheng.txt",false); //将中间结果输出到文件中 
		//回溯规划最短路径
		for(i=0;i<layer[plength-1].getCityNum();i++)
		{
			layer[plength-1].getCity(i).setMindis(0);//最后一个城市到下一个城市距离为0
			layer[plength-1].getCity(i).setNextheap(-1);//已经是最后一跳
		}
		for(i=(plength-2);i>=0;i--)//for k-1 downto 0
		{

			for(j=0;j<layer[i].getCityNum();j++)//dis[x]取遍k阶段所有城市
			{
				layer[i].getCity(j).setMindis(wuqiongda);//初始化路径最大
				panduan=layer[i+1].getCityNum();
				for(k=0;k<layer[i+1].getCityNum();k++)//y取遍k+1阶段所有城市
				{
					
					disX=layer[i].getCity(j).getMindis();//得到当前保存的最值距离
					disY=layer[i+1].getCity(k).getMindis();
					tempX=layer[i].getCity(j).getNumPoint();
					tempY=layer[i+1].getCity(k).getNumPoint();
					mapXY=trans[tempY][tempX];//转换矩阵的要求
					fw.write("layerX="+i+" x="+layer[i].getCity(j).getXulie()+"行-1:"+tempX+" Y="+layer[i+1].getCity(k).getXulie()+"列-1:"+tempY+" 权重mapXY="+mapXY+"\n");
					if(mapXY==0)//x,y之间的距离=0，说明xy不通
					{
						panduan--;
						if(panduan==0)
						{
							System.out.println("layer"+i+":i+1"+"之间没有通路！");
						}
					}
					else
					{
//						if((disY+mapXY)<disX)//求最短路径
						if((disY+mapXY)>disX)//求最长路径
						{
							layer[i].getCity(j).setMindis((disY+mapXY));//更新距离
							layer[i].getCity(j).setNextheap(k);//更新下一跳
						}
					}
					
					fw.write("i="+i+"maxdis"+layer[i].getCity(j).getXulie()+"= "+layer[i].getCity(j).getMindis()+"\n");
				}
			}
		}
		fw.close();//关闭文件
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}
		//输出计算结果和路径(求最大路径)//先求第一层结点的最大值
		int tempMin=wuqiongda,MinCity=-1;
		for(i=0;i<layer[0].getCityNum();i++)
		{
			System.out.println("city"+i+" Mindis="+layer[0].getCity(i).getMindis());
//			if(layer[0].getCity(i).getMindis()<tempMin)//第一个
			if(layer[0].getCity(i).getMindis()>tempMin)
			{
				tempMin=layer[0].getCity(i).getMindis();
				MinCity=i;
			}
		}
		System.out.println("蛋白质"+flag+" 最大路径距离为="+tempMin);

		
		//输出最短路径
		int nextCity=MinCity;
		for(i=0;i<plength;i++)
		{
			System.out.print(layer[i].getCity(nextCity).getXulie());
			if(nextCity==-1)
				System.out.println("nextCity 不能为-1");
			nextCity=layer[i].getCity(nextCity).getNextheap();
			if(i==plength-1)
				continue;
			else
				System.out.print("->");	
		}
		System.out.println("");

		//输出到文件,在这里可以修改输出格式
		try{
			FileWriter fw = new FileWriter("result.txt",true);  
			String s="蛋白质"+flag+" 最长路径距离为="+tempMin;
			fw.write(s);
			fw.write("\n");
			nextCity=MinCity;
			for(i=0;i<plength;i++)
			{
				fw.write(layer[i].getCity(nextCity).getXulie());
				nextCity=layer[i].getCity(nextCity).getNextheap();
				if(i==plength-1)
					continue;
				else
					fw.write("->");
				
			}
			fw.write("\n");
			fw.close();
			System.out.println("输出到result.txt中");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}

		return 0;
	}
	
	
	
	/**
	 * 从mapping.txt中读取数据
	 * @return 0成功 -1不成功
	 */
	
	public int getMappingFile()
	{
		System.out.println("我把mapping.txt的文件进行了一些修改，之前有的行是以‘，’结尾，现在所有行都是以‘，’结尾");
		String[] tempString=new String[MapLineMax];
		String readString="";//读取字符串
		int top=0;//临时队列的顶部
		int nlength=0;//当前字节的长度
		mapList=new Map[MapListMax];
		int mapPointer=-1;
		int i;
		File file=new File("data/mapping.txt");
		Reader reader=null;
		try
		{
			reader=new InputStreamReader(new FileInputStream(file));
			int tempchar;
			char tempchar1;
			while((tempchar=reader.read())!=-1)
			{
				tempchar1=(char)tempchar;
				if(tempchar1=='\n')//消除回车影响
					continue;
				if(tempchar1==',')
				{
					nlength=readString.length();
					if(nlength==1)
					{
						if(mapPointer==-1)
						{}
						else
						{
							
							mapList[mapPointer].setStringSize(top);
							for(i=0;i<top;i++)
							{
								mapList[mapPointer].setNewChar(i, tempString[i]);
							}
						}
						mapPointer++;
						mapList[mapPointer]=new Map();
						mapList[mapPointer].setOchar(readString);	
						top=0;					
					}
					else if(nlength==3)
					{
						tempString[top]=readString;
						top++;
					}
					readString="";		
					
				}
				else 
				{
					readString=readString+tempchar1;
				}
			}
			
//			System.out.println("输出mapList读取结果：");
//			for(i=0;i<=mapPointer;i++)//输出查看读取结果
//				mapList[i].disp();
			
			reader.close();
			return 0;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}
		
	}
	
	
	/**
	 * 读取protein.txt中的内容赋给plist
	 * @return 文件中的记录条数，-1表示发生错误
	 */
	
	public int getProteinFile()
	{
		
		String tempFlag="";//用于存储flag
		String tempOrder="";//用于存储序列
		int totalOrderSum=0;
		totalOrderSum=getProteinNum();
		int porder=0;//下标开始
		plist=new Protein[totalOrderSum];//生成数组存储蛋白质序列
		listNum=totalOrderSum;
		if(totalOrderSum==0)
		{
			System.out.println("protein.txt without record or read error!");
			return -1;
		}
		File file=new File("data/protein.txt");
		Reader reader=null;
		try
		{
			reader=new InputStreamReader(new FileInputStream(file));
			int tempchar;
			char tempchar1;
			int flag=0;//flag=1等号，flag=2第一个引号，flag=0遇到第二个引号
			while((tempchar=reader.read())!=-1)
			{
				tempchar1=(char)tempchar;
				if(tempchar1=='\n')//消除回车影响
				{}
				else if(tempchar1=='=')
				{
					flag=1;	
				}
				else if(flag==1&&tempchar1=='\"')//前引号
				{
					flag=2;
				}
				else if(flag==2&&tempchar1=='\"')//后引号
				{
					
					//读完一条，要赋值
					plist[porder]=new Protein();
					plist[porder].setFlag(tempFlag);
					plist[porder].setOrder(tempOrder);	
					flag=0;
					porder++;
					//赋值后清空
					tempFlag="";
					tempOrder="";
				}
				else if(flag==0)
				{
					tempFlag=tempFlag+tempchar1;				
				}
				else if(flag==2)
				{
					tempOrder=tempOrder+tempchar1;	
				}
				else
				{System.out.println("here is a bug");}
			
			}
			reader.close();
			
			for(int i=0;i<listNum;i++)//输出测试
			{
				plist[i].disp();
				System.out.println(plist[i].getLength());
			}
			return totalOrderSum;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println("error here");
			return -1;
		}
		

	}
	

	/**
	 * 从protein.txt中读取蛋白质序列的条数
	 * @return 一共有多少条蛋白质
	 */
	public int getProteinNum()
	{
		File file=new File("data/protein.txt");
		Reader reader=null;

		int totalOrderSum=0;
		try
		{
			reader=new InputStreamReader(new FileInputStream(file));
			int tempchar;
			char tempchar1;
			while((tempchar=reader.read())!=-1)
			{
				tempchar1=(char)tempchar;
				if(tempchar1=='=')
				{
					totalOrderSum++;
				}
			}
			System.out.println("total order number ="+totalOrderSum);
			reader.close();
			return totalOrderSum;
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**从trans.txt中读取trans
	 * @return true成功，false读取不成功
	 */
	public boolean getTransFile()
	{
		File file=new File("data/trans.txt");
		Reader reader=null;
		int temp[]=new int[4096];//存读取的数据
		char tempChar1;
		try
		{
			reader=new InputStreamReader(new FileInputStream(file));
			int tempchar,flag=0;
			while((tempchar=reader.read())!=-1)
			{
				tempChar1=(char)tempchar;
				if((tempChar1>='0')&&(tempChar1<='9'))//判断是数字
				{
					temp[flag]=(int)tempChar1-48;//根据ascll码转换为int
					flag++;
				}
			}
			reader.close();
//			System.out.println("flag= "+flag);
			flag=0;
			int i,j;
			for(i=0;i<64;i++)
			{	for(j=0;j<64;j++)
				{
					trans[i][j]=temp[flag];
//					System.out.print(trans[i][j]);
//					System.out.print(" ");
					flag++;
				}
//			System.out.print('\n');
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
}
