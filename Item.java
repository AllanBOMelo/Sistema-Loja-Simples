public class Item {
    // Declarações
    private double id;
    private String title;
    private String acc;
    private double price;
    private String pseudo;
    private String comprador;
    private String compemail;
    private String status;
    private String descri;
    private String pagmet;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }
    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public String getCompemail() {
        return compemail;
    }

    public void setCompemail(String compemail) {
        this.compemail = compemail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescri() {
        return descri;
    }

    public void setDescri(String descri) {
        this.descri = descri;
    }

    public String getPagmet() {
        return pagmet;
    }

    public void setPagmet(String pagmet) {
        this.pagmet = pagmet;
    }

    // Get e set Fim


    public Item(double id, String title, String acc, double price, String pseudo, String status, String descri, String pagmet) {
        this.id = id;
        this.title = title;
        this.acc = acc;
        this.price = price;
        this.pseudo = pseudo;
        this.status = status;
        this.descri = descri;
        this.pagmet = pagmet;
    }
}
