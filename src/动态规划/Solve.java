package ��̬�滮;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * ���ȶ�ȡ3�������ļ�mapping.txt,protein.txt,trans.txt�ֱ𱣴���mapList,plist,trans�У�
 * ��plist��ȡһ�������ʣ�����mapList����ת�����õ��������layer�У�layer���ж����㣬��city��ʾ
 * �����㷨�����·��
 * @author ���˻�
 *
 */
public class Solve {
	private int MapLineMax=10;//Ҫ����mapping.txt���һ����Ŀ
	private int MapListMax=26;//mapһ����a-z 26��
	
	private Map[] mapList;//mapping.txt����
	
	private Protein[] plist;//����������
	private int listNum;//�������������鳤��
	
	private int trans[][]=new int[64][64];//ת������
	
	private Layer[] layer;

	public Solve()//���캯��
	{
		getMappingFile();//��ȡmapping.txt,protein.txt,trans.txt
		getProteinFile();
		getTransFile();

//		start_guihua();//��ʼ�滮
		
//		disp_trans();
//		plist=new Protein[1];//���Ե���������
//		plist[0]=new Protein();
//		plist[0].setFlag("test");
//		plist[0].setOrder("MQVT");
//		listNum=1;		
		guihua(0);//ֻ���1��������
//		disp_mapList();
	}
	/**
	 * ���trans�鿴���
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
	 * ���ù滮�㷨����Сֵ
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
	 * ���mapList,�鿴�Ƿ���ȷ
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
 * ���±�ΪlistNumber�ĵ����ʵ����·��,���ƽ�������ʽ
 * @param ��Ӧplist�еĵ�listNumber��������
 * @return
 */
	public int guihua(int listNumber)
	{
//		int wuqiongda=Integer.MAX_VALUE;//���Ʊ�ʾ��ʾ�������,�����·����
		int wuqiongda=Integer.MIN_VALUE;//���Ʊ�ʾ��ʾ�������,�����·����
		String protein=plist[listNumber].getOrder();//����������
		String flag=plist[listNumber].getFlag();
		int plength=protein.length();
		layer=new Layer[plength];//��k���׶�
		
		//����mapList�еĶ��ڹ�ϵ����protein�е�ÿһ����layer�е�ÿһ��
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
		FileWriter fw = new FileWriter("guocheng.txt",false); //���м���������ļ��� 
		//���ݹ滮���·��
		for(i=0;i<layer[plength-1].getCityNum();i++)
		{
			layer[plength-1].getCity(i).setMindis(0);//���һ�����е���һ�����о���Ϊ0
			layer[plength-1].getCity(i).setNextheap(-1);//�Ѿ������һ��
		}
		for(i=(plength-2);i>=0;i--)//for k-1 downto 0
		{

			for(j=0;j<layer[i].getCityNum();j++)//dis[x]ȡ��k�׶����г���
			{
				layer[i].getCity(j).setMindis(wuqiongda);//��ʼ��·�����
				panduan=layer[i+1].getCityNum();
				for(k=0;k<layer[i+1].getCityNum();k++)//yȡ��k+1�׶����г���
				{
					
					disX=layer[i].getCity(j).getMindis();//�õ���ǰ�������ֵ����
					disY=layer[i+1].getCity(k).getMindis();
					tempX=layer[i].getCity(j).getNumPoint();
					tempY=layer[i+1].getCity(k).getNumPoint();
					mapXY=trans[tempY][tempX];//ת�������Ҫ��
					fw.write("layerX="+i+" x="+layer[i].getCity(j).getXulie()+"��-1:"+tempX+" Y="+layer[i+1].getCity(k).getXulie()+"��-1:"+tempY+" Ȩ��mapXY="+mapXY+"\n");
					if(mapXY==0)//x,y֮��ľ���=0��˵��xy��ͨ
					{
						panduan--;
						if(panduan==0)
						{
							System.out.println("layer"+i+":i+1"+"֮��û��ͨ·��");
						}
					}
					else
					{
//						if((disY+mapXY)<disX)//�����·��
						if((disY+mapXY)>disX)//���·��
						{
							layer[i].getCity(j).setMindis((disY+mapXY));//���¾���
							layer[i].getCity(j).setNextheap(k);//������һ��
						}
					}
					
					fw.write("i="+i+"maxdis"+layer[i].getCity(j).getXulie()+"= "+layer[i].getCity(j).getMindis()+"\n");
				}
			}
		}
		fw.close();//�ر��ļ�
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}
		//�����������·��(�����·��)//�����һ��������ֵ
		int tempMin=wuqiongda,MinCity=-1;
		for(i=0;i<layer[0].getCityNum();i++)
		{
			System.out.println("city"+i+" Mindis="+layer[0].getCity(i).getMindis());
//			if(layer[0].getCity(i).getMindis()<tempMin)//��һ��
			if(layer[0].getCity(i).getMindis()>tempMin)
			{
				tempMin=layer[0].getCity(i).getMindis();
				MinCity=i;
			}
		}
		System.out.println("������"+flag+" ���·������Ϊ="+tempMin);

		
		//������·��
		int nextCity=MinCity;
		for(i=0;i<plength;i++)
		{
			System.out.print(layer[i].getCity(nextCity).getXulie());
			if(nextCity==-1)
				System.out.println("nextCity ����Ϊ-1");
			nextCity=layer[i].getCity(nextCity).getNextheap();
			if(i==plength-1)
				continue;
			else
				System.out.print("->");	
		}
		System.out.println("");

		//������ļ�,����������޸������ʽ
		try{
			FileWriter fw = new FileWriter("result.txt",true);  
			String s="������"+flag+" �·������Ϊ="+tempMin;
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
			System.out.println("�����result.txt��");
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return -1;
		}

		return 0;
	}
	
	
	
	/**
	 * ��mapping.txt�ж�ȡ����
	 * @return 0�ɹ� -1���ɹ�
	 */
	
	public int getMappingFile()
	{
		System.out.println("�Ұ�mapping.txt���ļ�������һЩ�޸ģ�֮ǰ�е������ԡ�������β�����������ж����ԡ�������β");
		String[] tempString=new String[MapLineMax];
		String readString="";//��ȡ�ַ���
		int top=0;//��ʱ���еĶ���
		int nlength=0;//��ǰ�ֽڵĳ���
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
				if(tempchar1=='\n')//�����س�Ӱ��
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
			
//			System.out.println("���mapList��ȡ�����");
//			for(i=0;i<=mapPointer;i++)//����鿴��ȡ���
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
	 * ��ȡprotein.txt�е����ݸ���plist
	 * @return �ļ��еļ�¼������-1��ʾ��������
	 */
	
	public int getProteinFile()
	{
		
		String tempFlag="";//���ڴ洢flag
		String tempOrder="";//���ڴ洢����
		int totalOrderSum=0;
		totalOrderSum=getProteinNum();
		int porder=0;//�±꿪ʼ
		plist=new Protein[totalOrderSum];//��������洢����������
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
			int flag=0;//flag=1�Ⱥţ�flag=2��һ�����ţ�flag=0�����ڶ�������
			while((tempchar=reader.read())!=-1)
			{
				tempchar1=(char)tempchar;
				if(tempchar1=='\n')//�����س�Ӱ��
				{}
				else if(tempchar1=='=')
				{
					flag=1;	
				}
				else if(flag==1&&tempchar1=='\"')//ǰ����
				{
					flag=2;
				}
				else if(flag==2&&tempchar1=='\"')//������
				{
					
					//����һ����Ҫ��ֵ
					plist[porder]=new Protein();
					plist[porder].setFlag(tempFlag);
					plist[porder].setOrder(tempOrder);	
					flag=0;
					porder++;
					//��ֵ�����
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
			
			for(int i=0;i<listNum;i++)//�������
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
	 * ��protein.txt�ж�ȡ���������е�����
	 * @return һ���ж�����������
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
	
	/**��trans.txt�ж�ȡtrans
	 * @return true�ɹ���false��ȡ���ɹ�
	 */
	public boolean getTransFile()
	{
		File file=new File("data/trans.txt");
		Reader reader=null;
		int temp[]=new int[4096];//���ȡ������
		char tempChar1;
		try
		{
			reader=new InputStreamReader(new FileInputStream(file));
			int tempchar,flag=0;
			while((tempchar=reader.read())!=-1)
			{
				tempChar1=(char)tempchar;
				if((tempChar1>='0')&&(tempChar1<='9'))//�ж�������
				{
					temp[flag]=(int)tempChar1-48;//����ascll��ת��Ϊint
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
