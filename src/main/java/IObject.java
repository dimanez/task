public class IObject {
    private String uid;
    private String name;
    private String description;

    IObject(String uid, String name, String description){
        this.uid = uid;
        this.name = name;
        this.description = description;
    }

    IObject(String uid, String name){
        this.uid = uid;
        this.name = name;
    }
}
