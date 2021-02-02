package com.marcosledesma.a13_ejemploretrofit.modelos;

import com.google.gson.annotations.SerializedName;

public class Comment {
        @SerializedName("postId")
        private int postID;
        private int id;
        private String name;
        private String email;
        private String body;

        public int getPostID() { return postID; }
        public void setPostID(int value) { this.postID = value; }

        public int getID() { return id; }
        public void setID(int value) { this.id = value; }

        public String getName() { return name; }
        public void setName(String value) { this.name = value; }

        public String getEmail() { return email; }
        public void setEmail(String value) { this.email = value; }

        public String getBody() { return body; }
        public void setBody(String value) { this.body = value; }

        @Override
        public String toString() {
                return "Comment{" +
                        "postID=" + postID +
                        ", id=" + id +
                        ", name='" + name + '\'' +
                        ", email='" + email + '\'' +
                        ", body='" + body + '\'' +
                        '}';
        }
}

