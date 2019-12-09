package view.spriteManager.sprite;

import view.spriteManager.biomManager.BiomLevel;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class BreakableWall extends BasicSprite {

    private BiomLevel biom;

    public BreakableWall(BiomLevel b ) throws IOException {
        super(b.path, true);
        this.biom = b;
    }

    public BufferedImage getSprite() {
        return this.biom.getWallAlone();
    }

    public void setBiom(BiomLevel biomFromEnum) {
        this.biom = biomFromEnum;
    }
}
