package Task3;

public class Client {

    private String name;
    private float dist;

    public Client(String name, Float dist) {
        this.name = name;
        this.dist = dist;
    }

    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}

    public Float getDist() {return this.dist;}
    public void setDist(Float dist) {this.dist = dist;}

}
