// Необходимо реализовать метод разворота связного списка (двухсвязного или односвязного на выбор).

package HW3;

import java.util.Iterator;

public class hw3 {
    
    public static void main(String[] args) {
        // Создаем и формируем список контактов компании - contactCompany
        SingleLinkList<Contact> contactCompany = new SingleLinkList<>();
        contactCompany.addToEnd(new Contact(1, "Кадышева Лариса Леонидовна", "8-915-234-56-65"));
        contactCompany.addToEnd(new Contact(2, "Киселев Дмитрий Константинович", "8-916-333-45-21"));
        contactCompany.addToEnd(new Contact(3, "Дробышева Екатерина Валерьевна", "8-917-009-23-10"));
        contactCompany.addToEnd(new Contact(4, "Власова Светлана Игоревна", "8-915-146-43-88"));
        contactCompany.addToEnd(new Contact(5, "Демидов Ярослав Алексеевич", "23-45-36"));
        // Выводим список contactCompany
        System.out.println("Начальный список: ");
        for(Contact contact: contactCompany) {
            System.out.println(contact);
        }
       
        contactCompany.reverse();
        
        System.out.println("Результат после разворота списка: ");
        
        // Выводим список contactCompany после разворота
        for(Contact contact: contactCompany) {
            System.out.println(contact);
        }
    }

    // Создаем и описываем класс Contact
    static class Contact {
        int id;
        String name;
        String phone;
        public Contact(int id, String name, String phone) {
            this.id = id;
            this.name = name;
            this.phone = phone;
        }
        @Override
        public String toString() {
            return "Contact{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }

    public static class SingleLinkList<T> implements Iterable<T> {

        ListItem<T> head;
        ListItem<T> tail;

        @Override
        public Iterator<T> iterator() {
            return new Iterator<T>() {
                ListItem<T> current = head;

                @Override
                public boolean hasNext() {
                    return current != null;
                }

                @Override
                public T next() {
                    T data = current.data;
                    current = current.next;
                    return data;
                }
            };
        }

        private static class ListItem<T> {
            T data;
            ListItem<T> next;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void addToEnd(T item) {
            ListItem<T> newItem = new ListItem<>();
            newItem.data = item;
            if (isEmpty()) {
                head = newItem;
                tail = newItem;
            } else {
                tail.next = newItem;
                tail = newItem;
            }
        }
        // Процедура разворота списка
        public void reverse() {
            if (!isEmpty() && head.next != null) {
                tail = head;
                ListItem<T> current = head.next;
                head.next = null;
                while (current != null) {
                    ListItem<T> next = current.next;
                    current.next = head;
                    head = current;
                    current = next;
                }
            }
        }
    }
}