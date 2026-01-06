import java.util.*;


public class Category {
private String name;
private ArrayList<MenuItem> items = new ArrayList<>();


public Category(String name) {
this.name = name;
}


public void addItem(String name, double price) {
items.add(new MenuItem(name, price));
}


public String getName() {
return name;
}


public ArrayList<MenuItem> getItems() {
return items;
}
}




