package model.element;

import controller.Command;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * classe position en coordonnées entière, (x,y)
 * la classe doit être immuable, il n'y a pas de setteurs
 */
public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Position getPosition(){
        return this;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public String toString(){
        return "x:"+x+" y:"+y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(this.getClass() != obj.getClass()) return false;
        Position tmp = (Position) obj;
        return this.getX() == tmp.getX() && this.getY() == tmp.getY();
    }

    /*

    @Override
    public int hashCode() {
        int m = 2147000000;
        int p = 2147000041; //nombre premier
        Random r = new Random();
        int a = m/2;
        int b = m/4;

        int hash = ((a*(getX()+1/(getY()+1)) + b)%p)%m;
        hash += ((a*getY() + b)%p)%m;
        return hash;
    }
*/


    public static void main(String[] argv){
        //System.out.println(testCollision());
        //testPositions();

    }
/*
    private static void testPositions() {
        Position p1 = new Position(1,0);
        Position p2 = new Position(1,0);

        Position p3 = new Position(2,0);
        Position p4 = new Position(0,2);

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());

        System.out.println(p3.hashCode());
        System.out.println(p4.hashCode());


        System.out.println("===========");

        assert(p1.hashCode() == p2.hashCode()):"erreur Classe Position immuable";
        assert(p3.hashCode() != p4.hashCode()):"erreur Classe position immuable";

    }


    private static String testCollision() {

        boolean res = true;
        ArrayList pool = new ArrayList();
        for(int i = 1; i <= 2000; i++){
            for(int j = 0; j < 2000; j++){
                Position p = new Position(i-1,j);
                int hashcode = p.hashCode();
           //     System.out.println(hashcode);
                pool.add(hashcode);
            }
        }

        int len1 = pool.size();
        Set check = new TreeSet();
        for(int i = 0; i < len1; i++) {
            check.add(pool.get(i));
        }
        System.out.println(len1);
        System.out.println(check.size());
        return "collision : " + (float)(check.size()/len1) + " %";
    }
*/
}
