package view.spriteManager.biomManager;

import view.spriteManager.SpriteTileParser;

import java.awt.image.BufferedImage;

public abstract class PokemonTileset extends BiomLevel {

    public PokemonTileset(String filename) {
        super(SpriteTileParser.TEXTURE_PATH + filename);
    }

    @Override
    protected BufferedImage defineWallAlone() {
        return SpriteTileParser.resizeBufferedImageAsWorldUnit( this.tileSet.getSubimage(109,263,24,24) );
    }

    @Override
    protected BufferedImage defineGround() {

        return SpriteTileParser.resizeBufferedImageAsWorldUnit( this.tileSet.getSubimage(334,188,24,24) ) ;
    }

    @Override
    protected BufferedImage[] defineWallSquare() {
        BufferedImage[] wallSquare = new BufferedImage [9];

        wallSquare[SQUARE_TOP_LEFT] = this.tileSet.getSubimage(84,163,24,24);
        wallSquare[SQUARE_TOP_MID] = this.tileSet.getSubimage(109,163,24,24);
        wallSquare[SQUARE_TOP_RIGHT] = this.tileSet.getSubimage(134,163,24,24);
        wallSquare[SQUARE_MID_LEFT] = this.tileSet.getSubimage(84,188,24,24);
        wallSquare[SQUARE_MID_MID] = this.tileSet.getSubimage(109,188,24,24);
        wallSquare[SQUARE_MID_RIGHT] = this.tileSet.getSubimage(134,188,24,24);
        wallSquare[SQUARE_BOTTOM_LEFT] = this.tileSet.getSubimage(84,213,24,24);
        wallSquare[SQUARE_BOTTOM_MID] = this.tileSet.getSubimage(109,213,24,24);
        wallSquare[SQUARE_BOTTOM_RIGHT] = this.tileSet.getSubimage(134,213,24,24);

        for (int i = 0 ; i < wallSquare.length ; i++ )
            wallSquare[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallSquare[i]);


        return wallSquare;
    }

    @Override
    protected BufferedImage[] defineWallCross() {
        BufferedImage[] wallCross = new BufferedImage[7];

        wallCross[CROSS_MARGIN_TOP] = this.tileSet.getSubimage(109,313,24,24);
        wallCross[CROSS_MARGIN_BOTTOM] = this.tileSet.getSubimage(109,363,24,24);
        wallCross[CROSS_MARGIN_LEFT] = this.tileSet.getSubimage(84,338,24,24);
        wallCross[CROSS_MARGIN_RIGHT] = this.tileSet.getSubimage(134,338,24,24);
        wallCross[CROSS_CENTER] = this.tileSet.getSubimage(109,338,24,24);
        wallCross[CROSS_SIDE_VERTICAL] = this.tileSet.getSubimage(84,263,24,24);
        wallCross[CROSS_SIDE_HORIZONTAL] = this.tileSet.getSubimage(109,238,24,24);


        for (int i = 0 ; i < wallCross.length ; i++ )
            wallCross[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallCross[i]);

        return wallCross;
    }

    @Override
    protected BufferedImage[] defineWallCorners() {
        BufferedImage[] wallCorners = new BufferedImage[4];

        wallCorners[CORNER_TOP_LEFT] = this.tileSet.getSubimage(84,538,24,24);
        wallCorners[CORNER_TOP_RIGHT] = this.tileSet.getSubimage(109,538,24,24);
        wallCorners[CORNER_BOTTOM_LEFT] = this.tileSet.getSubimage(84,563,24,24);
        wallCorners[CORNER_BOTTOM_RIGHT] = this.tileSet.getSubimage(109,563,24,24);

        for (int i = 0 ; i < wallCorners.length ; i++ )
            wallCorners[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallCorners[i]);

        return wallCorners;
    }

    @Override
    protected BufferedImage[] defineWallTetris() {
        BufferedImage[] wallTetris = new BufferedImage[4];

        wallTetris[TETRIS_TOP] = this.tileSet.getSubimage(109,388,24,24);
        wallTetris[TETRIS_LEFT] = this.tileSet.getSubimage(84,413,24,24);
        wallTetris[TETRIS_BOTTOM] = this.tileSet.getSubimage(109,438,24,24);
        wallTetris[TETRIS_RIGHT] = this.tileSet.getSubimage(134,413,24,24);

        for (int i = 0 ; i < wallTetris.length ; i++ )
            wallTetris[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallTetris[i]);


        return wallTetris;
    }

    @Override
    protected BufferedImage[] defineWallTetrisBorder() {
        BufferedImage[] wallTetrisBorder = new BufferedImage[4];

        wallTetrisBorder[TETRIS_BORDER_TOP] = this.tileSet.getSubimage(109,463,24,24);
        wallTetrisBorder[TETRIS_BORDER_LEFT] = this.tileSet.getSubimage(84,488,24,24);
        wallTetrisBorder[TETRIS_BORDER_BOTTOM] = this.tileSet.getSubimage(109,513,24,24);
        wallTetrisBorder[TETRIS_BORDER_RIGHT] = this.tileSet.getSubimage(134,488,24,24);

        for (int i = 0 ; i < wallTetrisBorder.length ; i++ )
            wallTetrisBorder[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallTetrisBorder[i]);

        return wallTetrisBorder;
    }

    protected BufferedImage[] defineWallP() {
        BufferedImage[] wallP = new BufferedImage[4];

        wallP[P_TOP_LEFT] = this.tileSet.getSubimage(84,588,24,24);
        wallP[P_BOTTOM_LEFT] = this.tileSet.getSubimage(84,613,24,24);
        wallP[P_TOP_RIGHT] = this.tileSet.getSubimage(109,588,24,24);
        wallP[P_BOTTOM_RIGHT] = this.tileSet.getSubimage(109,613,24,24);

        for (int i = 0 ; i < wallP.length ; i++ )
            wallP[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallP[i]);

        return wallP;
    }

    protected BufferedImage[] defineWallPDown() {
        BufferedImage[] wallPDown = new BufferedImage[4];

        wallPDown[P_DOWN_TOP_LEFT] = this.tileSet.getSubimage(84,638,24,24);
        wallPDown[P_DOWN_BOTTOM_LEFT] = this.tileSet.getSubimage(84,663,24,24);
        wallPDown[P_DOWN_TOP_RIGHT] = this.tileSet.getSubimage(109,638,24,24);
        wallPDown[P_DOWN_BOTTOM_RIGHT] = this.tileSet.getSubimage(109,663,24,24);

        for (int i = 0 ; i < wallPDown.length ; i++ )
            wallPDown[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallPDown[i]);

        return wallPDown;
    }

    @Override
    protected BufferedImage[] defineWallCornersTiny() {
        BufferedImage[] wallTinyCorner = new BufferedImage[4];

        wallTinyCorner[CORNER_TINY_TOP_LEFT] = this.tileSet.getSubimage(84,238,24,24);
        wallTinyCorner[CORNER_TINY_BOTTOM_LEFT] = this.tileSet.getSubimage(84,288,24,24);
        wallTinyCorner[CORNER_TINY_TOP_RIGHT] = this.tileSet.getSubimage(134,238,24,24);
        wallTinyCorner[CORNER_TINY_BOTTOM_RIGHT] = this.tileSet.getSubimage(134,288,24,24);

        for (int i = 0 ; i < wallTinyCorner.length ; i++ )
            wallTinyCorner[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallTinyCorner[i]);

        return wallTinyCorner;
    }
}

