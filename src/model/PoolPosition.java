package model;

import model.element.Position;

public class PoolPosition {

    private static PoolPosition ourInstance = null;
    private Position[][] pool;
    private static final Position lower = new Position(-1,-1); //astuce me demander (Pierre)
    public static PoolPosition getInstance() {
        return ourInstance;
    }

    public static void init(int w, int h){
        if(ourInstance==null){
            ourInstance = new PoolPosition(w,h);
        }
    }

    private PoolPosition(int w, int h) {
        if (ourInstance==null){
            pool = new Position[w][h];
            for(int i=0; i<w; i++){
                for(int j=0; j<h; j++){
                    pool[i][j] = new Position(i,j);
                }
            }
        }
    }

    public Position getPosition(int x, int y){
        if(!(x<0 || y<0 || x>=pool.length || y>=pool[0].length)){//on check si la position est in bounds
            return pool[x][y];
        }
        return lower;//astuce pour checker les bounds me demander (Pierre) si besoin pour changer
    }

    public Position getPosition(Position p) {
        return getPosition(p.getX(), p.getY());
    }
}
