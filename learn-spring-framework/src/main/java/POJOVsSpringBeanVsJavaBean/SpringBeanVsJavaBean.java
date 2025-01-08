package POJOVsSpringBeanVsJavaBean;

import java.io.Serializable;

class Pojo{
     private String text;
     private int number;
     public String toString(){
         return text + ":"+number;
     }
 }

class JavaBean implements Serializable { //Enterprise Java Beans(EJB)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //constraints
     //1)No argument constructor, by default
    //2) getters anf setters
    //3) Implements Serializable
    private String text;
    private int number;
}
public class SpringBeanVsJavaBean {
    public static void main(String[] args) {
        Pojo pojo = new Pojo();

        System.out.println(pojo);
    }
}
