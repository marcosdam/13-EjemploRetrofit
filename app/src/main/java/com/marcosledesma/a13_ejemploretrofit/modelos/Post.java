package com.marcosledesma.a13_ejemploretrofit.modelos;

import com.google.gson.annotations.SerializedName;

public class Post {
        @SerializedName("userId")
        private int userID;
        private int id;
        private String title;
        private String body;

        public int getUserID() { return userID; }
        public void setUserID(int value) { this.userID = value; }

        public int getID() { return id; }
        public void setID(int value) { this.id = value; }

        public String getTitle() { return title; }
        public void setTitle(String value) { this.title = value; }

        public String getBody() { return body; }
        public void setBody(String value) { this.body = value; }

        @Override
        public String toString() {
            return "Post{" +
                    "userID=" + userID +
                    ", id=" + id +
                    ", title='" + title + '\'' +
                    ", body='" + body + '\'' +
                    '}';
        }
    }

