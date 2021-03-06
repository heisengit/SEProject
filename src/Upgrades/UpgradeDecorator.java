package Upgrades;

import IsaacMain.CollisionManager;
import IsaacMain.Player;
import menu.Mapping;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import skins.Animations;

/**
 * This class is extended by all the type of decorator
 * 
 */
public class UpgradeDecorator implements UpgradeComponent{

    protected UpgradeComponent player;

    public UpgradeDecorator(UpgradeComponent player) {
        this.player = player;
    }
    
    
    @Override
    public Shape getPlayer(){
        return player.getPlayer();
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        player.init(gc);
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        player.update(gc, delta);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        player.render(gc, g);
    }

    
    @Override
    public int getNumHearts() {
        return player.getNumHearts();
    }
    
    @Override
    public void setNumHearts(int numHearts) {
        player.setNumHearts(numHearts);
    }

    @Override
    public void setNumVoidHearts(int NumVoidHearts) {
        player.setNumVoidHearts(NumVoidHearts);
    }

    @Override
    public void setSpeedUp(float speedUp) {
        player.setSpeedUp(speedUp);
    }

    @Override
    public int getNumVoidHearts() {
        return player.getNumVoidHearts();
    }

    @Override
    public void getDamaged(int damage) {
        player.getDamaged(damage);
    }

    @Override
    public void execute() {
    }

    @Override
    public void setCommands(Mapping options) {
        player.setCommands(options);
    }

    @Override
    public Animations getAnimations() {
        return player.getAnimations();
    }

    @Override
    public void selectAnimations() {
        player.selectAnimations();
    }

    @Override
    public void resetStats() {
        player.resetStats();
    }

    @Override
    public void setCollisionManager(CollisionManager collision) {
        player.setCollisionManager(collision);
    }

    @Override
    public boolean isAppear() {
        return player.isAppear();
    }
}
