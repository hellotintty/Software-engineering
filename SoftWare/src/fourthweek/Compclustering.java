package fourthweek;
/*
 * ���ݹ�ϵ�ľ����㷨
 */

import java.util.ArrayList;

public class Compclustering {
	int[][] D;
	ArrayList<Object> S;
	int temp1;
	int temp2;
	Object x;
	ArrayList<ArrayList> list=new ArrayList<ArrayList>();
	ArrayList<Object> Cnew=new ArrayList<Object>();
	ArrayList<Object> Sx=new ArrayList<Object>();   //�ǵ�ÿ�߹�һ���㷨�����һ��Sc
	ArrayList<Object> Sxx=new ArrayList<Object>();
	public Compclustering(int[][] D,ArrayList<Object> objects,int temp1,int temp2) {
		this.D=D;
		this.S=objects;
		this.temp1=temp1;
		this.temp2=temp2;
	}
	//SC��list)�����ݴأ�Cnew��ÿһ�����ɵ����ݼ� ��D����ϵ������S�����󼯣�&��temp������ֵ
	public ArrayList<ArrayList> complete() {
		//��S��Ϊ�ռ�ʱ��������x����S������x����Cnew,S�м�ȥx���ֵ
		while(S.size()!=0) {			//while S!=null do ������x����S��x����Cnew��S=S-x
			System.out.println("��һ��ѭ������");
				Cnew.add(S.get(0));
				System.out.println("Cnew����������"+S.get(0).name);
				this.x=S.get(0);
				S.remove(0);
			//ΪSx�������x֮��S��ʣ������з�����ֵҪ��Ķ���
			for(int i=0;i<S.size();i++) {		//�ӣ������������� �ģ����������ܦģ���ģ����������ܦģ����ʣӣ��� 
				if(D[x.numberD][S.get(i).numberD]<=temp1&&D[x.numberD][S.get(i).numberD]>=temp2) {
					Sx.add(S.get(i));
					System.out.println("Sx������"+S.get(i).name);
				}
				else System.out.println(x.name+"��"+S.get(i).name+"������");
			}
			//�������ӣ�������null ��� ����������ʣӣ��������ã����������ӣ��������ӣ��������������� ���ӡ䣽�ӣ��ã�����
			while(Sx.size()!=0) {
				System.out.println("��һ��SXѭ������");
				Cnew.add(Sx.get(0));
				System.out.println("Cnew������"+Sx.get(0).name);
				Sx.remove(0);
				Sxx.addAll(S);
				Sxx.removeAll(Cnew);
				for(int i=0;i<Sxx.size();i++) {
					int n=0;
					for(int j=0;j<Cnew.size();j++) {
						if(D[Sxx.get(i).numberD][Cnew.get(j).numberD]<=temp1&&D[Sxx.get(i).numberD][Cnew.get(j).numberD]>=temp2) {
							n++;
						}
					}
					if(n==Cnew.size()) {
						Cnew.add(Sxx.get(i));
						System.out.println("Cnew������"+Sxx.get(i).name);
						Sx.remove(Sxx.get(i));  				//���������Ҫɾ��Sx�еĴ˶��󣬱����ظ�
					}
				}
			}
			//�ж�List�Ƿ�Ϊ�գ���Ϊ�գ�ֱ�ӽ�Cnew����
			if(list.size()==0) {		
				ArrayList<Object> copyCnew=new ArrayList<>();
				copyCnew.addAll(Cnew);
				list.add(copyCnew);
				System.out.println("list���������ӣ�(������)Ϊ:");
				for(int i=0;i<copyCnew.size();i++) {
					System.out.println(copyCnew.get(i).name);
				}
				Cnew.clear();
			}
			//����˵��List���������������ʶ���Cnew��List��ÿ��СList���ȽϿ��Ƿ�Ϊ�Ӽ�
			else {
				int count=0;
				for(int i=0;i<list.size();i++) {
					ArrayList<Object> templist=new ArrayList<Object>();
					templist.addAll(list.get(i));
					templist.retainAll(Cnew);
					if(!templist.equals(Cnew)) {
						count++;			//����ͳ��templist�Ƿ���List��ÿһ��������������
					}
					else {
						System.out.println("��һ��Cnew������������������.");
						Cnew.clear();
						}
					}
				if(count==list.size()) {
					ArrayList<Object> copyCnew=new ArrayList<>();
					copyCnew.addAll(Cnew);
					list.add(copyCnew);
					System.out.println("list���������ӣ�Ϊ:");
					for(int j=0;j<copyCnew.size();j++) {
						System.out.println(copyCnew.get(j).name);
					}
					Cnew.clear();
				}
			}
			System.out.println("����������������������������������������������������������������");
		}
		return list;
	}
}