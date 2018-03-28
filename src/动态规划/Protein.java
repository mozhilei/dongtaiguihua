package 动态规划;

public class Protein {

		private String flag;//记录蛋白质编号，如“A9”
		private String order;//记录蛋白质顺序,等号后面的部分
		
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
