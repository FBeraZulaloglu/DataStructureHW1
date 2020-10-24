/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


/** * @file DataStructureHomeWork * 
 * @description Programın amacı text dosyasından okunan datatları bir liste aracılığı ile daha iyi gözlemlemektir.
 * Program textdeki karakterleri unic bir şekilde tutarak bir ana liste yapısı oluşturur. Alt liste diyebileceğimiz 
 * yapı ilede textdeki unic karakterlerden yani ana listedeki karakterlerden sonra hangi karakterlerin kac defa gectigini 
 * bulmayı hedefleyen bir liste yapısıda ayrıca oluşturmaktadır. Böylelikle program en fazla veya en az
 * birlikte geçen karakterleri tespit edebilmekte olan bir liste yapısını oluşturmaktadır.
 * @assignment 1.ödev 
 * @date 16.04.2020
 * @author FARUK BERA ZÜLALOĞLU 
	farukbera.zulaloglu@stu.fsm.edu.tr */
public class LinkedList {

	Node mainHead;
	Node downHead;
	boolean createLittleList = false;
	File file = new File("C:\\Users\\faruk\\Documents\\NetBeansProjects\\DataStructureHomeWork\\build\\classes\\datastructurehomework\\veri.txt");
	
	//Unic karakterlerle bir liste olustur
	private boolean createMainList() {
		try (FileReader fr = new FileReader(file)) {

			int count;
			char data;

			Node temp = null;

			while ((count = fr.read()) != -1) {
				data = ((char) count);
				if (isCharacter(data) == true) {

					if (isUpperCase(data)) {
						data = makeLowerCase(data);
					}

					if (isEmpty()) {
						mainHead = new Node(data);
						temp = mainHead;
					}
					else{

					if (isExist(data) == false) {
						Node newNode = new Node(data);
						temp.nextNode = newNode;
						temp = temp.nextNode;
					}
					}
				} 

			}
			fr.close();
		} catch (IOException e) {

		}
		if (mainHead == null) {
			return false;
		}
		return true;

	}
	//Her bir unic karakterin alt listesini olustur
	private boolean createLittleList() {
		//eger bir kere bu fonksiyon çalışırsa bir daha calısmasını engelle
		if(createLittleList == false){
			//içinde mainlist i de calıstır.
		if (createMainList()) {

			if (mainHead.nextNode == null) {
				System.out.println("Sadece tek bir karekter vardır bu yuzden islemler  yapılamaz.");
				return false;
			}
				createLittleList = true;
			Node temp = mainHead;
			Node tempDown;
			int count;
			char data;
			while (temp != null) {
				downHead = temp;
				tempDown = downHead;
				try (FileReader fr = new FileReader(file)) {
					while ((count = fr.read()) != -1) {
						data = ((char) count);
						if (isCharacter(data)) {
							if (isUpperCase(data)) {
								data = makeLowerCase(data);
							}
							if (temp.data == data) {
								// bir sonraki data ya gecis yapar
								char downData = (char) fr.read();
								if (isCharacter(downData)) {
									if (isUpperCase(downData)) {
										downData = makeLowerCase(downData);
									}

									if (isExistDown(downHead, downData) == null) {
										tempDown.downNode = new Node(downData, 1);
										tempDown = tempDown.downNode;
									} else {
										isExistDown(downHead, downData).numberOfdownData++;
									}

								}
							}
						}
					}
					fr.close();
				} catch (IOException e) {

				}
				temp = temp.nextNode;
			}
			
		} else {
			System.out.println("Text de hicbir karakter bulunmamaktadır");
			return false;
		}
		}
		return true;
	}
	//main listeyi olustururken karakterlerin unic olmasını sagla
	private boolean isExist(char data) {
		Node temp = mainHead;
		while (temp != null) {
			if (isEmpty()) {
				System.out.println("Liste Bostur");
			} else {
				if (data == temp.data) {
					return true;
				}
				temp = temp.nextNode;
			}
		}
		return false;
	}
	//Alt listelerdeki nodeların tek bir karaktere özgü olmasını sagla
	private Node isExistDown(Node littleHead, char c) {
		while (littleHead.downNode != null) {
			littleHead = littleHead.downNode;
			if (littleHead.data == c) {
				return littleHead;
			}
		}
		return null;
	}
	
	private boolean isEmpty() {
		return mainHead == null;
	}
	
	private boolean isCharacter(char data) {
		return Character.isAlphabetic(data);
	}

	private boolean isUpperCase(char c) {
		if (c >= 65 && c <= 90) {
			return true;
		} else {
			return false;
		}

	}

	private char makeLowerCase(char c) {
		c = (char) (c + 32);
		return c;
	}

	void ardisikKarakterler(char k) {
		boolean bulundu = false;
		if (createLittleList()) {
			Node temp = mainHead;
			Node tempDown;
			if (isCharacter(k)) {
				if (isUpperCase(k)) {
					System.out.println("Veride upperCase bir karakter bulunmamaktadır.");
					return;
				}
				while (temp != null) {
					downHead = temp;
					if (downHead.data == k) {
						bulundu = true;
						System.out.print(k + " dan sonra gelen karakterler");
						tempDown = downHead;
						while (tempDown.downNode != null) {
							System.out.print(" --> " + tempDown.downNode.data);
							tempDown = tempDown.downNode;
						}
					}
					temp = temp.nextNode;
				}
				if(bulundu == false)
				{
					System.out.println("Girilen karakter listede bulunamadi.");
				}
				else{
				System.out.print(" BİTTİ");
				System.out.println("");
			}
			} else {
				System.out.println("Parametre olarak bir karakter girmeniz gerekmektedir.");
			}
		} else {
			System.out.println("Liste olusturulamamıstır");
			return;
		}
	}

	void enCokArdisik() {
		if (createLittleList()) {
			Node temp = mainHead;
			Node tempDown;
			//print edebilmek için degiskenler tanımla
			int max = 0;
			Node maxTemp = null;
			Node maxHead = null;
			while (temp != null) {
				downHead = temp;
				tempDown = downHead;
				while (tempDown.downNode != null) {
					tempDown = tempDown.downNode;
					if (tempDown.numberOfdownData > max) {
						//print edecegin degerleri ata
						max = tempDown.numberOfdownData;
						maxTemp = tempDown;
						maxHead = temp;
					}
				}
				temp = temp.nextNode;
			}
			System.out.println(maxHead.data + " ile " + maxTemp.data + " max birlikte bulunan charlardır." + " (" + max + "kere bulunmaktadirlar.)");
		} else {
			System.out.println("Liste olusturulamamıstır");
			return;
		}
	}

	void enCokArdisik(char k) {
		if (createLittleList()) {
			//istenilen char bulunup bulunmadıgını kontrol et
			boolean kBulundu = false;
			if (isCharacter(k)) {
				if (isUpperCase(k)) {
					System.out.println("Upper case karakter bulunmamaktadır.Lutfen lowerCase sekilde giriniz.");
					return;
				}
				Node temp = mainHead;
				Node downTemp ;
				int maxValue = 0;
				Node maxNode = null;

				while (temp.nextNode != null) {
					if (temp.data == k) {
						kBulundu = true;
						downTemp = temp;
						while (downTemp.downNode != null) {
							if (downTemp.downNode.numberOfdownData > maxValue) {
								maxValue = downTemp.downNode.numberOfdownData;
								maxNode = downTemp.downNode;
							}
							downTemp = downTemp.downNode;
						}
						System.out.println(temp.data + " dan sonra gelen en cok char '" + maxNode.data + "' dir. (" + maxNode.numberOfdownData + " kere bulunmaktadir.)");
						break;
					}
					temp = temp.nextNode;
				}
				if(!kBulundu){
					System.out.println("Girilen karakter bulunamadi.");
				}

			} else {
				System.out.println("Parametre olarak bir karakter girmeniz gerekmektedir.");
			}
		} else {
			System.out.println("Liste olusturulamamıstır");
			return;
		}
	}

	void enAzArdisik() {
		if (createLittleList()) {
			Node temp = mainHead;
			Node tempDown;
			//print edebilmek için degiskenler tanımla
			int min = temp.downNode.numberOfdownData;
			Node minTemp = temp.downNode;
			Node minHead = temp;

			while (temp.nextNode != null) {
				downHead = temp;
				tempDown = downHead;
				while (tempDown.downNode != null) {
					tempDown = tempDown.downNode;
					if (tempDown.numberOfdownData < min) {
						//print edilecek degerleri ata
						min = tempDown.numberOfdownData;
						minTemp = tempDown;
						minHead = temp;
					}
				}
				temp = temp.nextNode;
			}
			System.out.println(minHead.data + " ile " + minTemp.data + " min birlikte bulunan charlardır." + " (" + min + "kere bulunmaktadirlar.)");
		} else {
			System.out.println("Liste olusturulamamıstır");
			return;
		}
	}

	void enAzArdisik(char k) {
		if (createLittleList()) {
			//istenilen char bulunup bulunmadıgını kontrol et
			boolean kBulundu = false;
			if (isCharacter(k)) {
				if (isUpperCase(k)) {
					System.out.println("Upper case karakter bulunmamaktadır.Lutfen lowerCase sekilde giriniz.");
					return;
				}
				Node temp = mainHead;
				Node downTemp;
				int minValue;
				Node minNode;
				minValue = temp.downNode.numberOfdownData;

				while (temp != null) {
					if (temp.data == k) {
						kBulundu = true;
						downTemp = temp;
						minValue = downTemp.downNode.numberOfdownData;
						minNode = downTemp.downNode;
						while (downTemp.downNode != null) {
							if (downTemp.downNode.numberOfdownData < minValue) {
								minValue = downTemp.downNode.numberOfdownData;
								minNode = downTemp.downNode;
							}
							downTemp = downTemp.downNode;
						}
						System.out.println(temp.data + " dan sonra gelen en az char '" + minNode.data + "' dir. (" + minNode.numberOfdownData + " kere bulunmaktadir.)");
					}
					temp = temp.nextNode;
				}
				if(!kBulundu){
					System.out.println("girilen karakter bulunamamıstır.");
				}
				
			}
			else{
				System.out.println("Lutfen bir karakter giriniz.");
				return;
			}
		} else {
			System.out.println("Liste olusturulamamıstır");
			return;
		}

	}

	private void printMainList() {
		createMainList();
		System.out.println("---------------------------------MAIN LIST-----------------------------");
		Node temp;
		temp = mainHead;
		while (temp != null) {
			System.out.print(temp.data + "--> ");
			temp = temp.nextNode;
		}
		System.out.println("NULL");

	}

	private void printLittleList() {
		createLittleList();
		System.out.println("---------------------------------LİTTLE LIST-----------------------------");
		Node temp = mainHead;
		Node tempDown = downHead;

		while (temp != null) {
			System.out.print(temp.data + "------->");
			tempDown = temp;
			while (tempDown.downNode != null) {
				tempDown = tempDown.downNode;

				System.out.print(tempDown.data + "(" + tempDown.numberOfdownData + ")");
			}

			temp = temp.nextNode;
			System.out.println("");

		}

	}
	//Bütün listeyi yazdırmaktadır.
	void printList(){
		printMainList();
		printLittleList();
	}

}
