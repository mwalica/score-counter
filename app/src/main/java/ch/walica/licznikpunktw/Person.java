package ch.walica.licznikpunktw;

public class Person {
    private String name;
    private int count;

    public Person(String name) {
        this.name = name;
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
