package furkanservetkaya.types;

public class SehirlerContract {

    private int id;
    private String sehirId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSehirId() {
        return sehirId;
    }

    public void setSehirId(String sehirId) {
        this.sehirId = sehirId;
    }

    @Override
    public String toString() {
        return sehirId;
    }

}

