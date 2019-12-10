package IsaacMain;

import Entities.Entity.*;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

//Questa classe viene inizializzata nel costruttore di player (riga 55) e 
//ne tiene un riferimento come parametro di classe (riga 50).
//è stato commentato il metodo collidesWith in player in riga 527
//e sono stati modificati gli if in riga 463 e 479
/**
 * Manages collisions within the map. In this class the character's life and points 
 * are updated, with relative generation of subsequent rewards.
 */
public class CollisionManager implements Mediator{
    //It keeps a reference for all objects that can cause collisions
    private Player playerInstance;
    private Level level;
    private ArrayList<Entity> blocks;
    private Points pts;
    private Shape reward;
    private ArrayList<Shape> spikes;
    
    /**
     * Inizialize all the instance of the class
     * @param level     
     */
    public CollisionManager(Level level){
        this.level = level;
        this.setParameters(level);
        this.playerInstance = Player.getPlayerInstance();
        if(pts.iterator().hasNext()){
           reward = pts.iterator().next().getHitBox();
        }
    }
    
    /**
     * This method has to detect the collisions of the player
     * with the map's objects. This method call the increasing points function
     * if the player collides with a reward and also call the decreasing life 
     * function when the player collides with a spike
     * 
     * @return false if collides with a no stopping object or not collides, 
     * true if collides with a stopping object
     */
    @Override
    public boolean collidesWith(){
        int i;
        if(pts != null){
            getReward();
        }
        
        //Check if the player collides with a spike
        if(spikes != null){
            for(i=0; i < spikes.size(); i++){
                if(playerInstance.getPlayer().intersects(spikes.get(i))){
                    playerInstance.getDamaged(2); // 0 has to be replaced with the doDamage method
                }
            }
        }
        //check if the player collides with a obstacle
        if(blocks != null){
            for(i = 0; i < blocks.size(); i++){
                if(playerInstance.getPlayer().intersects(blocks.get(i).getHitBox())){
                    return true;
                }
            }
        }
        return false;
    }
    
    
    /**
     * This method has the scope of the detection of the collision between Isaac 
     * and the Shape of the current reward that Isacc has to collect
     */
    private void getReward(){
        if(playerInstance.getPlayer().intersects(this.reward)){
            if(pts.iterator().hasNext()){
                this.reward = pts.iterator().next().getHitBox();
            }
        }
    }
    
    /**
     * Takes the blocks and the enemies/weapons from the level
     * Invoked when a level is finished and another one has to start
     * @param level the current level of the game
     */
    public void setParameters(Level level){
        this.blocks = level.getBlock();
        this.pts = level.getPts();
        this.spikes = new ArrayList<Shape>();//Aggiungere get delle spikes
        
    }
}