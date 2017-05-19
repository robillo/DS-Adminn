package dailysuvichar.ds_admin;

import com.google.firebase.storage.StorageReference;

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
    private StorageReference imgGov, imgSpec;

    public StorageReference getImgGov() {
        return imgGov;
    }

    public void setImgGov(StorageReference imgGov) {
        this.imgGov = imgGov;
    }

    public StorageReference getImgSpec() {
        return imgSpec;
    }

    public void setImgSpec(StorageReference imgSpec) {
        this.imgSpec = imgSpec;
    }

    public Guru(){

    }

    public Guru(String name, String email, String DOB, String uid, int age) {
        this.name = name;
        this.email = email;
        this.DOB = DOB;
        this.uid = uid;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getPreferredLang() {
        return preferredLang;
    }

    public void setPreferredLang(String preferredLang) {
        this.preferredLang = preferredLang;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getGovDB() {
        return govDB;
    }

    public void setGovDB(String govDB) {
        this.govDB = govDB;
    }

    public String getSpecDB() {
        return specDB;
    }

    public void setSpecDB(String specDB) {
        this.specDB = specDB;
    }
}
