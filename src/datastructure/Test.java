/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datastructure;


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
public class Test {

	public static void main(String[] args) {
		LinkedList myLinkedList = new LinkedList();
		//myLinkedList.init();
		myLinkedList.ardisikKarakterler('f');
		myLinkedList.enCokArdisik();
		myLinkedList.enCokArdisik('s');
		myLinkedList.enAzArdisik();
		myLinkedList.enAzArdisik('q');
	}

}
