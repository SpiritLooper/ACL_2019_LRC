package view.spriteManager.sprite.oriented;

import controller.Orientation;
import model.element.entities.Status;
import view.spriteManager.SpriteTileParser;
import view.spriteManager.sprite.BasicSprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Oriented sprite handler
 */
public abstract class OrientedSprite extends BasicSprite {

    private Orientation orientation = Orientation.DOWN;
    private Status status = Status.STANDING;
    private int indiceSpriteRotation = 0;

    private BufferedImage[][] spritesMove = new BufferedImage[Orientation.values().length][3];
    private BufferedImage[][] spritesAttack = new BufferedImage[Orientation.values().length][3];
    private BufferedImage[] sufferingSprite = new BufferedImage[Orientation.values().length];
    private BufferedImage[] idleSprite = new BufferedImage[Orientation.values().length];

    /**
     * Affect an oriented sprite
     * @param path path of the sprite
     * @throws IOException throw if path doesnt exist
     */
    public OrientedSprite(String path) throws IOException {
        super(path, false);
        setSpriteIdle(idleSprite);
        setSpritesMove(this.spritesMove);
        setSpritesAttack(this.spritesAttack);
        setSufferingSprite(this.sufferingSprite);

        resizeSprites();
    }

    /**
     * resize all sprites substracted
     */
    private void resizeSprites() {
        for (int i = 0; i < Orientation.values().length; i++) {
            spritesMove[i][0] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesMove[i][0]);
            spritesMove[i][1] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesMove[i][1]);
            spritesMove[i][2] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesMove[i][2]);

            spritesAttack[i][0] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesAttack[i][0]);
            spritesAttack[i][1] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesAttack[i][1]);
            spritesAttack[i][2] = SpriteTileParser.resizeBufferedImageAsWorldUnit(spritesAttack[i][2]);

            sufferingSprite[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(sufferingSprite[i]);

            idleSprite[i] = SpriteTileParser.resizeBufferedImageAsWorldUnit(idleSprite[i]);
        }
    }

    /**
     * define move sprite
     * @param spritesTab tab instancied to define
     */
    protected abstract void setSpritesMove(BufferedImage[][] spritesTab);


    /**
     * define idle move sprite
     * @param idleSprite tab instancied to define
     */
    protected abstract void setSpriteIdle(BufferedImage[] idleSprite);

    /**
     * define attack move sprite
     * @param spritesTab tab instancied to define
     */
    protected abstract void setSpritesAttack(BufferedImage[][] spritesTab);


    /**
     * define suffering move sprite
     * @param spritesTab tab instancied to define
     */
    protected abstract void setSufferingSprite(BufferedImage[] spritesTab);

    /**
     * Get the good sprite en function of entity status
     * @return sprite
     */
    @Override
    public BufferedImage getSprite() {

        BufferedImage res = null;

        switch (status) {
            case ATTACKING:
                res = spritesAttack[this.orientation.ordinal()][indiceSpriteRotation];
                break;
            case SUFFERING:
                res = sufferingSprite[this.orientation.ordinal()];
                break;
            case STANDING:
                res = spritesMove[this.orientation.ordinal()][indiceSpriteRotation];
                break;
            default:
                res = idleSprite[this.orientation.ordinal()];
        }


        indiceSpriteRotation++;
        indiceSpriteRotation %= 3;

        return idleSprite[this.orientation.ordinal()];
    }

    public void setOrientation(Orientation o) {
        this.orientation = o;
    }

    public void setStatus(Status s) {
        this.status = s;
    }

    /**
     * Flip verticaly a buffered image
     * @param image image to flip
     * @return image flipped
     */
    protected BufferedImage createFlipped(BufferedImage image) {
        for (int j = 0; j < image.getHeight(); j++) {
            for (int i = 0; i < image.getWidth() / 2; i++) {
                int tmp = image.getRGB(i, j);
                image.setRGB(i, j, image.getRGB(image.getWidth() - i - 1, j));
                image.setRGB(image.getWidth() - i - 1, j, tmp);
            }
        }
        return image;
    }

}
