public class Item {
    // Declarações
    private double id;
    private String title;
    private String acc;
    // Declarações fim

    // Get e set
    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    // Get e set Fim


    public Item(double id, String title, String acc) {
        this.id = id;
        this.title = title;
        this.acc = acc;
    }
}
