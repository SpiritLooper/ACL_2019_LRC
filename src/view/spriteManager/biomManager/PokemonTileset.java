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
}

