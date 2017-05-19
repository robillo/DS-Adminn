package dailysuvichar.ds_admin;

/**
 * Created by rishabhshukla on 20/05/17.
 */

public class Guru {
    private String name;
    private String email;
    private String bio;
    private String preferredLang;
    private String photoUrl;
    private String coverUrl;
    private String DOB;
    private String gender;
    private String phone;
    private String uid;
    private int age;
    private int followersCount;
    private String specialization, govDB, specDB;

    public Guru(String name, String email, String DOB, String uid, int age) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.uid = uid;
        this.age = age;
    }
}
