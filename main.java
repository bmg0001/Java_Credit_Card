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
			System.out.println("===== 신용카드 ====");
			System.out.println("1. 카드 생성\n2. 카드정보 조회\n3. 카드 사용\n4.데이터 읽기\n5.데이터 기록\n6. 종료");
			System.out.print(">> ");
			
			int menu = sc.nextInt();
			switch(menu) {
			case 1:
				System.out.print("이름 > ");
				String name = sc.next();
				card[num++] = new Credit(name);
				System.out.println("새로운 카드가 생성되었습니다.");
				System.out.println("카드 번호 = "+card[num-1].CardNumber());
				System.out.println("카드 Owner = "+card[num-1].CardOwner());
				break;
			case 2:
				System.out.print("카드번호 > ");
				String code = sc.next();
				for(int i=0; i<num; i++) {
					if(code.equals(card[i].CardNumber()) == true) {
						System.out.println("카드 번호 = "+card[i].CardNumber());
						System.out.println("카드 Owner = "+card[i].CardOwner());
						System.out.println("카드 총 사용 금액 = "+card[i].getBalance());
					}
				}
				break;
			case 3:
				System.out.print("카드번호 > ");
				code = sc.next();
				System.out.print("사용금액> ");
				int amount = sc.nextInt();
				for(int i=0; i<num; i++) {
					if(code.equals(card[i].CardNumber()) == true) {
						card[i].Check(amount);
						System.out.println("카드 번호 = "+card[i].CardNumber());
						System.out.println("카드 Owner = "+card[i].CardOwner());
						System.out.println("카드 사용 금액 = "+ amount);
						System.out.println("카드 총 사용 금액 = "+card[i].getBalance());
						System.out.println("처리완료.");
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
				System.out.println("처리완료.");
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
				System.out.println("처리완료.");
				break;
			case 6:
				loop = false;
				break;
			}
		}
	}
}
class Credit{
	int balance; //카드 사용금액
	String owner; //카드 사용자 이름
	String address; //카드번호
	
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
