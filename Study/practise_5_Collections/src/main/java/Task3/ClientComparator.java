package Task3;
import java.util.Comparator;

class ClientComparator implements Comparator<Client> {
    public int compare(Client c1, Client c2) {
        if (c1.getDist() < c2.getDist()) {
            return 1;
        } else if (c1.getDist() > c2.getDist()) {
            return -1;
        }
        return 0;
    }
}
