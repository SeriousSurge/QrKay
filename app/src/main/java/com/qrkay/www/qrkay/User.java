package com.qrkay.www.qrkay;


public class User {
    private String userName;
    private String userEmail;
    private String joinedDate;
    private String profileImage;
    private String DoB;
    private String mobileNumber;

    public User(){

    }

    private User(UserBuilder builder){
        this.userName = builder.userName;
        this.userEmail = builder.userEmail;
        this.joinedDate = builder.joinedDate;
        this.profileImage = builder.profileImage;
        this.DoB = builder.DoB;
        this.mobileNumber = builder.mobileNumber;
    }

    public String getUserName(){
        return userName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    public String getJoinedDate(){
        return joinedDate;
    }

    public String getProfileImage(){
        return profileImage;
    }

    public String getDoB(){
        return DoB;
    }

    public String getMobileNumber(){
        return mobileNumber;
    }

    public static class UserBuilder{
        private String userName = "userName";
        private final String userEmail;
        private final String joinedDate;
        private String profileImage = "http://profileImage";
        private final String DoB;
        private String mobileNumber = "0123456789";

        public UserBuilder(String userEmail, String joinedDate, String DoB){
            this.userEmail = userEmail;
            this.joinedDate = joinedDate;
            this.DoB = DoB;
        }

        public UserBuilder userName(String userName){
            this.userName = userName;
            return this;
        }

        public UserBuilder profileImage(String profileImage){
            this.profileImage = profileImage;
            return this;
        }

        public UserBuilder mobileNumber(String mobileNumber){
            this.mobileNumber = mobileNumber;
            return this;
        }

        public User build(){
            return new User(this);
        }
    }
}
