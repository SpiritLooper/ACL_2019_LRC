package view.spriteManager.biomManager;
import model.PositionPool;
import model.element.Position;
import model.game.Game;
import view.Painter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Collection;

public abstract class BiomLevel {
    
    protected BufferedImage tileSet;

    private BufferedImage wallAlone;

    private BufferedImage ground;

    private BufferedImage[] wallSquare;
    protected static final int SQUARE_TOP_LEFT = 0;
    protected static final int SQUARE_TOP_MID = 1;
    protected static final int SQUARE_TOP_RIGHT = 2;
    protected static final int SQUARE_MID_LEFT = 3;
    protected static final int SQUARE_MID_MID = 4;
    protected static final int SQUARE_MID_RIGHT = 5;
    protected static final int SQUARE_BOTTOM_LEFT = 6;
    protected static final int SQUARE_BOTTOM_MID = 7;
    protected static final int SQUARE_BOTTOM_RIGHT = 8;


    private BufferedImage[] wallCross;
    protected static final int CROSS_MARGIN_TOP = 0;
    protected static final int CROSS_MARGIN_LEFT = 1;
    protected static final int CROSS_MARGIN_BOTTOM = 2;
    protected static final int CROSS_MARGIN_RIGHT = 3;
    protected static final int CROSS_CENTER = 4;
    protected static final int CROSS_SIDE_VERTICAL = 5;
    protected static final int CROSS_SIDE_HORIZONTAL = 6;

    private BufferedImage[] wallCorner;
    protected static final int CORNER_TOP_LEFT = 0;
    protected static final int CORNER_TOP_RIGHT = 1;
    protected static final int CORNER_BOTTOM_LEFT = 2;
    protected static final int CORNER_BOTTOM_RIGHT = 3;

    private BufferedImage[] wallTetris;
    protected static final int TETRIS_TOP = 0;
    protected static final int TETRIS_LEFT = 1;
    protected static final int TETRIS_BOTTOM = 2;
    protected static final int TETRIS_RIGHT = 3;

    private BufferedImage[] wallTetrisBorder;
    protected static final int TETRIS_BORDER_TOP = 0;
    protected static final int TETRIS_BORDER_LEFT = 1;
    protected static final int TETRIS_BORDER_BOTTOM = 2;
    protected static final int TETRIS_BORDER_RIGHT = 3;

    private BufferedImage[] wallP;
    protected static final int P_TOP_RIGHT = 0;
    protected static final int P_TOP_LEFT = 1;
    protected static final int P_BOTTOM_LEFT = 2;
    protected static final int P_BOTTOM_RIGHT = 3;

    private BufferedImage[] wallPDown;
    protected static final int P_DOWN_TOP_RIGHT = 0;
    protected static final int P_DOWN_TOP_LEFT = 1;
    protected static final int P_DOWN_BOTTOM_LEFT = 2;
    protected static final int P_DOWN_BOTTOM_RIGHT = 3;


    public BiomLevel(String path) {
       try {
           tileSet = ImageIO.read(BiomLevel.class.getClassLoader().getResourceAsStream(path));
           ground = defineGround();
           wallAlone = defineWallAlone();
           wallCross = defineWallCross();
           wallSquare = defineWallSquare();
           wallCorner = defineWallCorners();
           wallTetris = defineWallTetris();
           wallTetrisBorder = defineWallTetrisBorder();
           wallP = defineWallP();
           wallPDown = defineWallPDown();

       } catch (IOException e ) {
           System.err.println("Load setTile Failed");
           e.printStackTrace();
           System.exit(1);
       }

    }



    /**
     * Creer l'image de fond du level
     * @param wallPositions mur du level
     * @return image de fond du level
     */
    public BufferedImage buildImageLevel(Collection<Position> wallPositions) {
        BufferedImage levelDesign = new BufferedImage(
                 (Game.WIDTH + 2 * 1) * Painter.WORLD_UNIT,
                (Game.HEIGHT + 2 * 1) * Painter.WORLD_UNIT,
                BufferedImage.TYPE_INT_RGB);

        Graphics2D g = levelDesign.createGraphics();
        //Dessin du fond
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0, levelDesign.getWidth(), levelDesign.getHeight());

        // Dessin du sol
        for(int l = 0 ; l < Game.HEIGHT ; l++) {
            for(int c = 0 ; c < Game.WIDTH; c++) {
                g.drawImage(ground, ( c + 1 ) * Painter.WORLD_UNIT, ( l + 1 ) * Painter.WORLD_UNIT, null );
            }
        }

        //Dessin des murs interne
        PositionPool pool = PositionPool.getInstance();
        for (Position p : wallPositions) {
            boolean wallUp, wallDown, wallLeft, wallRight;
            boolean wallTopLeft, wallTopRight, wallBottomLeft, wallBottomRight;

            wallUp = p.getY() == 0 || wallPositions.contains(pool.getPosition(p.getX() , p.getY() - 1));
            wallDown = p.getY() + 1 == Game.HEIGHT || wallPositions.contains(pool.getPosition(p.getX() , p.getY() + 1));
            wallLeft = p.getX() == 0 || wallPositions.contains(pool.getPosition(p.getX() - 1 , p.getY()));
            wallRight = p.getX() + 1 == Game.WIDTH || wallPositions.contains(pool.getPosition(p.getX() + 1 , p.getY()));

            wallBottomLeft = p.getX() - 1 < 0 || p.getY() + 1 >= Game.HEIGHT || wallPositions.contains( pool.getPosition( p.getX() - 1 , p.getY() + 1) );
            wallBottomRight = p.getX() + 1 >= Game.WIDTH || p.getY() + 1 >= Game.HEIGHT || wallPositions.contains(pool.getPosition( p.getX() + 1 , p.getY() + 1)  );
            wallTopLeft = p.getX() - 1 < 0 || p.getY() - 1 < 0 || wallPositions.contains(pool.getPosition( p.getX() - 1, p.getY() -  1) );
            wallTopRight = p.getX() + 1 >= Game.WIDTH  || p.getY() - 1 < 0 || wallPositions.contains(pool.getPosition( p.getX() + 1, p.getY() - 1) );

            BufferedImage wall = getWall(p , wallUp, wallDown, wallLeft, wallRight, wallTopLeft, wallTopRight, wallBottomLeft, wallBottomRight);
            g.drawImage(wall, (p.getX() + 1 ) * Painter.WORLD_UNIT, (p.getY() + 1 ) * Painter.WORLD_UNIT, null);
        }


        //Dessin de l'enceinte de mur
            // Ajout des murs angles
            BufferedImage cornerTopLeftSprite, cornerTopRightSprite, cornerBottomLeftSprite, cornerBottomRightSprite;

            cornerTopRightSprite = (wallPositions.contains(pool.getPosition(0,0))) ? wallSquare[SQUARE_MID_MID] : wallCorner[CORNER_TOP_LEFT];
            cornerTopLeftSprite = (wallPositions.contains(pool.getPosition( Game.WIDTH - 1,0))) ? wallSquare[SQUARE_MID_MID] : wallCorner[CORNER_TOP_RIGHT];
            cornerBottomRightSprite = (wallPositions.contains(pool.getPosition(0,Game.HEIGHT - 1))) ? wallSquare[SQUARE_MID_MID] : wallCorner[CORNER_BOTTOM_LEFT];
            cornerBottomLeftSprite = (wallPositions.contains(pool.getPosition(Game.WIDTH - 1,Game.HEIGHT - 1 ))) ? wallSquare[SQUARE_MID_MID] : wallCorner[CORNER_BOTTOM_RIGHT];

            g.drawImage(cornerTopRightSprite, 0 * Painter.WORLD_UNIT , 0 * Painter.WORLD_UNIT, null ); // Haut Gauche
            g.drawImage(cornerTopLeftSprite, ( Game.WIDTH + 1 ) * Painter.WORLD_UNIT , 0 * Painter.WORLD_UNIT, null ); // Haut Droite
            g.drawImage(cornerBottomRightSprite, 0 * Painter.WORLD_UNIT , ( Game.HEIGHT + 1 ) * Painter.WORLD_UNIT, null ); // Bas Gauche
            g.drawImage(cornerBottomLeftSprite, ( Game.WIDTH + 1 ) * Painter.WORLD_UNIT , ( Game.HEIGHT + 1 ) * Painter.WORLD_UNIT, null ); // Bas Droite

            // Ajout des murs qui vont jusque ces angles
        for(int i = 1 ; i <= Game.WIDTH ; i++ ) {
            BufferedImage topWallSprite, bottomWallSprite;

            // Murs haut
            topWallSprite = spriteBorderTop(
                    ( i - 2 <= 0 ) || wallPositions.contains(pool.getPosition(i - 2 ,0)),
                    wallPositions.contains(pool.getPosition(i - 1 ,0)),
                    (i == Game.WIDTH) || wallPositions.contains(pool.getPosition(i ,0))
            );
            g.drawImage(topWallSprite, i * Painter.WORLD_UNIT , 0 * Painter.WORLD_UNIT, null );

            // Murs bas
            bottomWallSprite = spriteBorderBottom(
                    ( i - 1 == 0 ) || wallPositions.contains(pool.getPosition(i - 2 ,Game.HEIGHT - 1)),
                    wallPositions.contains(pool.getPosition(i - 1 ,Game.HEIGHT - 1)),
                    (i == Game.WIDTH) || wallPositions.contains(pool.getPosition(i ,Game.HEIGHT - 1))

            );
            g.drawImage(bottomWallSprite, i * Painter.WORLD_UNIT , ( Game.HEIGHT + 1 ) * Painter.WORLD_UNIT, null );
        }

        for(int j = 1 ; j <= Game.HEIGHT ; j++) {
            BufferedImage leftWallSprite, rightWallSprite;

            // Murs gauche
            leftWallSprite = spriteBorderLeft(
                    j - 2 <= 0  || wallPositions.contains(pool.getPosition(0, j - 2)),
                    wallPositions.contains(pool.getPosition(0, j - 1)),
                    j  == Game.HEIGHT  || wallPositions.contains(pool.getPosition(0, j ))
            );

            g.drawImage(leftWallSprite, 0 * Painter.WORLD_UNIT , j  * Painter.WORLD_UNIT, null );

            // Murs droite
            rightWallSprite = spriteBorderRight(
                    j - 2 <= 0  || wallPositions.contains(pool.getPosition( Game.WIDTH - 1 , j - 2)),
                    wallPositions.contains(pool.getPosition(Game.WIDTH - 1 , j - 1)),
                    j  == Game.HEIGHT  || wallPositions.contains(pool.getPosition(Game.WIDTH - 1 , j ))
            );

            g.drawImage(rightWallSprite, ( Game.WIDTH + 1 ) * Painter.WORLD_UNIT , j  * Painter.WORLD_UNIT, null );
        }

        return levelDesign;
    }

    /**
     * Give sprite of a wall
     * @param p wall position
     * @param wallUp true if wall top of p
     * @param wallDown true if wall bottom of p
     * @param wallLeft true if wall left of p
     * @param wallRight true if wall right of p
     * @param topLeft true if no wall top left
     * @param topRight true if no wall top right
     * @param bottomLeft true if no wall bottom left
     * @param bottomRight true if no wall bottom right
     */
    private BufferedImage getWall(Position p, boolean wallUp, boolean wallDown, boolean wallLeft, boolean wallRight, 
                                  boolean topLeft, boolean topRight, boolean bottomLeft, boolean bottomRight) {
     // On traite tout les cas possible : Liste par suite binaire (Je pleure en ecrivant ce commentaire)

        BufferedImage wallSelected = null ;
        if(!wallUp && !wallDown && !wallLeft && !wallRight) { // 0  0  0  0
            wallSelected = wallAlone;
        } else if (!wallUp && !wallDown && !wallLeft && wallRight ) { // 0  0  0  1
            wallSelected = wallCross[CROSS_MARGIN_LEFT];
        } else if (!wallUp && !wallDown && wallLeft && !wallRight) { // 0  0  1  0
            wallSelected = wallCross[CROSS_MARGIN_RIGHT];
        } else if (!wallUp && !wallDown && wallLeft && wallRight) { // 0  0  1  1
            wallSelected = wallCross[CROSS_SIDE_HORIZONTAL];
        } else if (!wallUp && wallDown && !wallLeft && !wallRight ) { // 0  1  0  0
            wallSelected = wallCross[CROSS_MARGIN_TOP];
        } else if (!wallUp && wallDown && !wallLeft && wallRight) { // 0  1  0  1
            wallSelected = wallSquare[SQUARE_TOP_LEFT];
        } else if (!wallUp && wallDown && wallLeft && !wallRight) { // 0  1  1  0
            wallSelected = wallSquare[SQUARE_TOP_RIGHT];
        } else if (!wallUp && wallDown && wallLeft && wallRight  ) { // 0  1  1  1
            if(!bottomRight && !bottomLeft){
                wallSelected = wallTetris[TETRIS_TOP];
            } else if ( !bottomLeft && bottomRight) {
                wallSelected = wallPDown[P_DOWN_TOP_RIGHT];
            } else if ( bottomLeft && !bottomRight) {
                wallSelected = wallPDown[P_DOWN_TOP_LEFT];
            } else {
                wallSelected = wallSquare[SQUARE_TOP_MID];
            }
        } else if (wallUp && !wallDown && !wallLeft && !wallRight ) { // 1  0  0  0
            wallSelected = wallCross[CROSS_MARGIN_BOTTOM];
        } else if (wallUp && !wallDown && !wallLeft && wallRight ) { // 1  0  0  1
            wallSelected = wallSquare[SQUARE_BOTTOM_LEFT];
        } else if (wallUp && !wallDown && wallLeft && !wallRight ) { // 1  0  1  0
            wallSelected = wallSquare[SQUARE_BOTTOM_RIGHT];
        } else if (wallUp && !wallDown && wallLeft && wallRight ) { // 1  0  1  1
            if(!topRight && !topLeft) {
                wallSelected = wallTetris[TETRIS_BOTTOM];
            } else if( !topRight && topLeft) {
                wallSelected = wallPDown[P_DOWN_BOTTOM_LEFT];
            } else if(topRight && !topLeft) {
                wallSelected = wallPDown[P_DOWN_BOTTOM_RIGHT];
            } else {
                wallSelected = wallSquare[SQUARE_BOTTOM_MID];
            }
        } else if (wallUp && wallDown && !wallLeft && !wallRight) { // 1  1  0  0
            wallSelected = wallCross[CROSS_SIDE_VERTICAL];
        } else if (wallUp && wallDown && !wallLeft && wallRight ) { // 1  1  0  1
            if(!topRight && !bottomRight) {
                wallSelected = wallTetris[TETRIS_LEFT];
            } else if( !topRight && bottomRight ) {
                wallSelected = wallP[P_BOTTOM_LEFT];
            } else if ( topRight && !bottomRight) {
                wallSelected = wallP[P_TOP_LEFT];
            } else {
                wallSelected = wallSquare[SQUARE_MID_LEFT];
            }
        } else if (wallUp && wallDown && wallLeft && !wallRight ) { // 1  1  1  0
            if(!topLeft && !bottomLeft) {
                wallSelected = wallTetris[TETRIS_RIGHT];
            } else if ( !topLeft && bottomLeft ) {
                wallSelected = wallP[P_BOTTOM_RIGHT];
            } else if ( topLeft && !bottomLeft ) {
                wallSelected = wallP[P_TOP_RIGHT];
            } else {
                wallSelected = wallSquare[SQUARE_MID_RIGHT];
            }
        } else if (wallUp && wallDown && wallLeft && wallRight ) { // 1  1  1  1

            if(!bottomLeft && bottomRight && topLeft && topRight) {
                wallSelected = wallCorner[CORNER_TOP_RIGHT];
            } else if( bottomLeft && !bottomRight && topLeft && topRight) {
                wallSelected = wallCorner[CORNER_TOP_LEFT];
            } else if( bottomLeft && bottomRight && !topLeft && topRight) {
                wallSelected = wallCorner[CORNER_BOTTOM_RIGHT];
            } else if( bottomLeft && bottomRight && topLeft && !topRight) {
                wallSelected = wallCorner[CORNER_BOTTOM_LEFT];
            } else {
                wallSelected = wallSquare[SQUARE_MID_MID];
            }
        }

        return wallSelected;
    }

    /**
     * Defini le sprite avec le mur sans mur autour de lui
     * @return L'image
     */
    protected abstract BufferedImage defineWallAlone();

    /**
     * Defini le sprite du sol
     * @return Image du sol
     */
    protected abstract BufferedImage defineGround();

    /**
     * Defini les murs en forme de rectangle
     * @return tableau d'image organise comme les constante SQUARE_*
     */
    protected abstract BufferedImage[] defineWallSquare();

    /**
     * Defini les murs en forme de croix
     * @return tableau d'image organise comme les constante CROSS_*
     */
    protected abstract BufferedImage[] defineWallCross();

    /**
     * Defini les murs en forme de croix
     * @return tableau d'image organise comme les constante CORNER_*
     */
    protected abstract BufferedImage[] defineWallCorners();

    /**
     * Give wall in disposition tetris like
     * @return tab sprite
     */
    protected abstract BufferedImage[] defineWallTetris();


    /**
     * Give wall in disposition tetris like however in border
     * @return tab sprite
     */
    protected abstract BufferedImage[] defineWallTetrisBorder();

    /**
     * Give wall in center of P disposition
     * @return tab sprite
     */
    protected abstract BufferedImage[] defineWallP();


    /**
     * Give wall in center of P lie down disposition
     * @return tab sprite
     */
    protected abstract BufferedImage[] defineWallPDown();

    /**
     * Give top border sprite of level
     * @param wallBottomLeft true if is there a wall bottom left of the sprite
     * @param wallBottom true if is there a wall bottom of the sprite
     * @param wallBottomRight true if is there a wall bottom right of the sprite
     * @return good sprite
     */
    private BufferedImage spriteBorderTop(boolean wallBottomLeft, boolean wallBottom, boolean wallBottomRight) {
        if (wallBottom && wallBottomLeft && wallBottomRight) {
            return wallSquare[SQUARE_MID_MID];
        } else if (wallBottom && !wallBottomLeft && wallBottomRight) {
            return wallCorner[CORNER_TOP_RIGHT];
        } else if (wallBottom && wallBottomLeft && !wallBottomRight) {
            return wallCorner[CORNER_TOP_LEFT];
        } else if( wallBottom && !wallBottomLeft && !wallBottomRight){
            return wallTetrisBorder[TETRIS_BORDER_TOP];
        } else {
            return wallSquare[SQUARE_BOTTOM_MID];
        }
    }


    /**
     * Give bottom border sprite of level
     * @param wallTopLeft true if is there a wall bottom left of the sprite
     * @param wallTop true if is there a wall bottom of the sprite
     * @param wallTopRight true if is there a wall bottom right of the sprite
     * @return good sprite
     */
    private BufferedImage spriteBorderBottom(boolean wallTopLeft, boolean wallTop, boolean wallTopRight) {
        if (wallTop && wallTopLeft && wallTopRight) {
            return wallSquare[SQUARE_MID_MID];
        } else if(wallTop && !wallTopLeft && wallTopRight ) {
            return wallCorner[CORNER_BOTTOM_RIGHT];
        } else if(wallTop && wallTopLeft && !wallTopRight ) {
            return wallCorner[CORNER_BOTTOM_LEFT];
        } else if( wallTop && !wallTopLeft && !wallTopRight){
            return wallTetrisBorder[TETRIS_BORDER_BOTTOM];
        } else {
            return wallSquare[SQUARE_TOP_MID];
        }
    }


    /**
     * Give left border sprite of level
     * @param wallRightTop true if is there a wall right top of the sprite
     * @param wallRightMid true if is there a wall right of the sprite
     * @param wallRightBottom true if is there a wall right  bottom of the sprite
     * @return good sprite
     */
    private BufferedImage spriteBorderLeft(boolean wallRightTop, boolean wallRightMid, boolean wallRightBottom) {
       if (wallRightMid && wallRightTop && wallRightBottom) {
            return wallSquare[SQUARE_MID_MID];
       } else if(wallRightMid && !wallRightTop && wallRightBottom ) {
            return wallCorner[CORNER_BOTTOM_LEFT];
        } else if(wallRightMid && wallRightTop && !wallRightBottom ) {
            return wallCorner[CORNER_TOP_LEFT];
        } else if( wallRightMid && !wallRightTop && !wallRightBottom){
           return wallTetrisBorder[TETRIS_BORDER_LEFT];
        } else {
            return wallSquare[SQUARE_MID_RIGHT];
        }
    }


    /**
     * Give right border sprite of level
     * @param wallLeftTop true if is there a wall top left of the sprite
     * @param wallLeft true if is there a wall left of the sprite
     * @param wallLeftBottom true if is there a wall left bottom of the sprite
     * @return good sprite
     */
    private BufferedImage spriteBorderRight(boolean wallLeftTop, boolean wallLeft, boolean wallLeftBottom) {
        if (wallLeft && wallLeftTop && wallLeftBottom) {
            return wallSquare[SQUARE_MID_MID];
        } else if(wallLeft && !wallLeftTop && wallLeftBottom ) {
            return wallCorner[CORNER_BOTTOM_RIGHT];
        } else if(wallLeft && wallLeftTop && !wallLeftBottom ) {
            return wallCorner[CORNER_TOP_RIGHT];
        } else if( wallLeft && !wallLeftTop && !wallLeftBottom){
            return wallTetrisBorder[TETRIS_BORDER_RIGHT];
        } else {
            return wallSquare[SQUARE_MID_LEFT];
        }
    }


}
