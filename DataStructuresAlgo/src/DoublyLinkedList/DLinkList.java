package DoublyLinkedList;


class DLinkedList {
	private DNode head;
	private DNode tail;

	public DLinkedList() {
		head = null;
		tail = null;
	}

	public boolean isEmpty() {
		return head == null;
	}

	public void inserthead(int dd) {
		DNode newLink = new DNode(dd);
		if (isEmpty()) {
			tail = newLink;
		} else {
			head.previous = newLink;
		}
		newLink.next = head;
		head = newLink;
	}

	public void inserttail(int dd) {
		DNode newLink = new DNode(dd);
		if (isEmpty()) {
			head = newLink;
		} else {
			tail.next = newLink;
			newLink.previous = tail;
		}
		tail = newLink;
	}

	public DNode deletehead() {
		DNode temp = head;
		if (head.next == null) {
			tail = null;
		} else {
			head.next.previous = null;
		}
		head = head.next;
		return temp;
	}

	public DNode deletetail() {
		DNode temp = tail;
		if (head.next == null) {
			head = null;
		} else {
			tail.previous.next = null;
		}
		tail = tail.previous;
		return temp;
	}

	public boolean insertAfter(int key, int dd) {
		DNode current = head;
		while (current.i != key) {
			current = current.next;
			if (current == null) {
				return false;
			}
		}
		DNode newLink = new DNode(dd);

		if (current == tail) {
			newLink.next = null;
			tail = newLink;
		} else {
			newLink.next = current.next;

			current.next.previous = newLink;
		}
		newLink.previous = current;
		current.next = newLink;
		return true;
	}

	public DNode deleteKey(int key) {
		DNode current = head;
		while (current.i != key) {
			current = current.next;
			if (current == null)
				return null;
		}
		if (current == head) {
			head = current.next;
		} else {
			current.previous.next = current.next;
		}

		if (current == tail) {
			tail = current.previous;
		} else {
			current.next.previous = current.previous;
		}
		return current;
	}

	public String toString() {
		String str = "List (head-->tail): ";
		DNode current = head;
		while (current != null) {
			str += current.toString();
			current = current.next;
		}
		System.out.println("");
		System.out.print("List (tail-->head): ");

		current = tail;
		while (current != null) {
			str += current.toString();
			current = current.previous;
		}
		return str;
	}

	
}
