package ��̬�滮;

public class Protein {

		private String flag;//��¼�����ʱ�ţ��硰A9��
		private String order;//��¼������˳��,�Ⱥź���Ĳ���
		
		public Protein()
		{
			flag="";
			order="";
		}

		public String getFlag() {
			return flag;
		}

		public void setFlag(String flag) {
			this.flag = flag;
		}

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}
		public int getLength()
		{
			return order.length();
		}

		public void disp()
		{
			System.out.println("Flag= "+flag);
			System.out.println("order= "+order);
		}
	
}
