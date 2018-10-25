package DomainLayer;

import java.util.List;

public class Person extends Resource {

    public Person(String name, int id) {
        super(id);
        this.name = name;
    }

    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

}
