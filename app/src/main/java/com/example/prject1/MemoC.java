package com.example.prject1;

public class MemoC {

    private int _id;
    private String title;
    private String age;
    private String name;
    private String contents;

    public int get_id() {
       return this._id ;
    }
    public void set_id(int i) {
        this._id =i;
    }

    public void setTilte(String s) {
        this.title = s;
    }
    public String getTilte() {
        return this.title;
    }

    public void setName(String s) {
        this.name =s;
    }
    public String getName() {
        return this.name;
    }

    public void setAge(String s) {
        this.age = s;
    }
    public String getAge() {
        return this.age ;
    }
    public void setContents(String s){this.contents = s;
    }
    public String getContents(){ return  this.contents ;
    }
}

