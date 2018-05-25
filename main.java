package kr.krl.credit;
import java.util.*;
import java.io.*;

public class main {
	public static void main(String[] args) throws IOException{
		boolean loop = true;
		int num = 0;
		Scanner sc = new Scanner(System.in);
		Credit[] card = new Credit[50];
		
		while(loop) {
			System.out.println("===== �ſ�ī�� ====");
			System.out.println("1. ī�� ����\n2. ī������ ��ȸ\n3. ī�� ���\n4.������ �б�\n5.������ ���\n6. ����");
			System.out.print(">> ");
			
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				System.out.print("�̸� > ");
				String name = sc.next();
				card[num++] = new Credit(name);
				System.out.println("���ο� ī�尡 �����Ǿ����ϴ�.");
				System.out.println("ī�� ��ȣ = "+card[num-1].CardNumber());
				System.out.println("ī�� Owner = "+card[num-1].CardOwner());
				break;
			case 2:
				System.out.print("ī���ȣ > ");
				String code = sc.next();
				for(int i=0; i<num; i++) {
					if(code.equals(card[i].CardNumber()) == true) {
						System.out.println("ī�� ��ȣ = "+card[i].CardNumber());
						System.out.println("ī�� Owner = "+card[i].CardOwner());
						System.out.println("ī�� �� ��� �ݾ� = "+card[i].getBalance());
					}
				}
				break;
			case 3:
				System.out.print("ī���ȣ > ");
				code = sc.next();
				System.out.print("���ݾ�> ");
				int amount = sc.nextInt();
				for(int i=0; i<num; i++) {
					if(code.equals(card[i].CardNumber()) == true) {
						card[i].Check(amount);
						System.out.println("ī�� ��ȣ = "+card[i].CardNumber());
						System.out.println("ī�� Owner = "+card[i].CardOwner());
						System.out.println("ī�� ��� �ݾ� = "+ amount);
						System.out.println("ī�� �� ��� �ݾ� = "+card[i].getBalance());
						System.out.println("ó���Ϸ�.");
					}
				}
				break;
			case 4:
				DataInputStream dis = new DataInputStream(new FileInputStream("data.bin"));
				num = dis.readInt();
				
				for(int i =0; i<num; i++) {
					String owner = dis.readUTF();
					int balance = dis.readInt();
					String Number = dis.readUTF();
					
					card[i] = new Credit(owner);
					card[i].address = Number;
					card[i].balance = balance;
				}
				
				dis.close();
				System.out.println("ó���Ϸ�.");
				break;
			case 5:
				DataOutputStream dos = new DataOutputStream(new FileOutputStream("data.bin"));
				dos.writeInt(num);
				for(int i =0; i<num; i++) {
					dos.writeUTF(card[i].CardOwner());
					dos.writeInt(card[i].getBalance());
					dos.writeUTF(card[i].CardNumber());
				}
				dos.close();
				System.out.println("ó���Ϸ�.");
				break;
			case 6:
				loop = false;
				break;
			}
		}
	}
}
class Credit{
	int balance; //ī�� ���ݾ�
	String owner; //ī�� ����� �̸�
	String address; //ī���ȣ
	
	Credit(String owner) {
		Random rr = new Random();
		this.owner = owner;
		balance = 0;
		address = "";
		for(int i=0; i<4; i++) {
			address += Integer.toString(rr.nextInt(8999)+1000)+"-";
		}
		address = address.substring(0, address.length()-1);
	}
	String CardNumber() {
		return address;
	}
	String CardOwner() {
		return owner;
	}
	int getBalance() {
		return balance;
	}
	void Check(int amount) {
		balance += amount;
	}
}
