package com.example.prject1;

public class Planet {
    int x,y;
    int palnetSpeed = 7;
    int dir;
    Planet(int x, int y, int d){
        this.x=x; this.y = y; this.dir =d;
    }
    public void move(){
        if(dir == 1)
           this.x -= palnetSpeed;
        else
            this.x +=palnetSpeed;
    }
}
