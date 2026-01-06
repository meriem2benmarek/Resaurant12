import java.util.*;


public class Order {
private ArrayList<OrderItem> items = new ArrayList<>();


public void addItem(MenuItem item) {
for (OrderItem oi : items) {
if (oi.getItem().getName().equals(item.getName())) {
oi.increment();
return;
}
}
items.add(new OrderItem(item));
}


public ArrayList<OrderItem> getItems() {
return items;
}


public double getTotal() {
double total = 0;
for (OrderItem oi : items) {
total += oi.getTotal();
}
return total;
}
}



