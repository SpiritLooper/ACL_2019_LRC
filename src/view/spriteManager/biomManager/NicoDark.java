package view.spriteManager.biomManager;

import view.spriteManager.SpriteTileParser;

import java.awt.image.BufferedImage;

public class NicoDark extends BiomLevel {

    public NicoDark() {
        super(SpriteTileParser.TEXTURE_PATH + "tileset.png");
    }

    @Override
    protected BufferedImage defineWallAlone() {
        return SpriteTileParser.resizeBufferedImageAsWorldUnit( this.tileSet.getSubimage(0,0,32,32) );
    }

    @Override
    protected BufferedImage defineGround() {

        return SpriteTileParser.resizeBufferedImageAsWorldUnit( this.tileSet.getSubimage(32,0,32,32) ) ;
    }

    @Override
    protected BufferedImage[] defineWallSquare() {
        BufferedImage[] wallSquare = new BufferedImage [9];

        wallSquare[SQUARE_TOP_LEFT] = this.tileSet.getSubimage(192,0,32,32);
        wallSquare[SQUARE_TOP_MID] = this.tileSet.getSubimage(224,0,32,32);
        wallSquare[SQUARE_TOP_RIGHT] = this.tileSet.getSubimage(256,0,32,32);
        wallSquare[SQUARE_MID_LEFT] = this.tileSet.getSubimage(192,32,32,32);
        wallSquare[SQUARE_MID_MID] = this.tileSet.getSubimage(224,32,32,32);
        wallSquare[SQUARE_MID_RIGHT] = this.tileSet.getSubimage(256,32,32,32);
        wallSquare[SQUARE_BOTTOM_LEFT] = this.tileSet.getSubimage(192,64,32,32);
        wallSquare[SQUARE_BOTTOM_MID] = this.tileSet.getSubimage(224,64,32,32);
        wallSquare[SQUARE_BOTTOM_RIGHT] = this.tileSet.getSubimage(256,64,32,32);

        for (int i = 0 ; i < wallSquare.length ; i++ )
            wallSquare[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallSquare[i]);


        return wallSquare;
    }

    @Override
    protected BufferedImage[] defineWallCross() {
        BufferedImage[] wallCross = new BufferedImage[7];

        wallCross[CROSS_MARGIN_TOP] = this.tileSet.getSubimage(64,0,32,32);
        wallCross[CROSS_MARGIN_BOTTOM] = this.tileSet.getSubimage(64,96,32,32);
        wallCross[CROSS_MARGIN_LEFT] = this.tileSet.getSubimage(0,64,32,32);
        wallCross[CROSS_MARGIN_RIGHT] = this.tileSet.getSubimage(96,64,32,32);
        wallCross[CROSS_CENTER] = this.tileSet.getSubimage(64,64,32,32);
        wallCross[CROSS_SIDE_VERTICAL] = this.tileSet.getSubimage(64,32,32,32);
        wallCross[CROSS_SIDE_HORIZONTAL] = this.tileSet.getSubimage(32,64,32,32);


        for (int i = 0 ; i < wallCross.length ; i++ )
            wallCross[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallCross[i]);

        return wallCross;
    }

    @Override
    protected BufferedImage[] defineWallCorners() {
        BufferedImage[] wallCorners = new BufferedImage[4];

        wallCorners[CORNER_TOP_LEFT] = this.tileSet.getSubimage(224,32,32,32);
        wallCorners[CORNER_TOP_RIGHT] = this.tileSet.getSubimage(224,32,32,32);
        wallCorners[CORNER_BOTTOM_LEFT] = this.tileSet.getSubimage(224,32,32,32);
        wallCorners[CORNER_BOTTOM_RIGHT] = this.tileSet.getSubimage(224,32,32,32);

        for (int i = 0 ; i < wallCorners.length ; i++ )
            wallCorners[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(wallCorners[i]);

        return wallCorners;
    }
}
