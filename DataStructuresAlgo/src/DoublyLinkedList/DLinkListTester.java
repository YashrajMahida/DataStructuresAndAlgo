package DoublyLinkedList;


public class DLinkListTester {

	public static void main(String[] args) {
		DLinkedList theList = new DLinkedList();

		theList.inserthead(22);
		theList.inserthead(44);
		theList.inserthead(66);

		theList.inserttail(11);
		theList.inserttail(33);
		theList.inserttail(55);

		System.out.println(theList);

		theList.deletehead();
		theList.deletetail();
		theList.deleteKey(11);

		System.out.println(theList);

		theList.insertAfter(22, 77);
		theList.insertAfter(33, 88);

		System.out.println(theList);

	}

}