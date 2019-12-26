package ShootingEnemyFactory;

import ShootingEnemies.ShootingEnemy;
import java.util.ArrayList;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.tiled.TiledMap;

public class RandomTurretClient {

    /*
        The "StaticDamageList" class uses the concrete factories multiple
        times to create an ArrayList of StaticDamage objects, that will be used
        by the CollisionManager for checking the collisions. 
     */
    private int x, y, difficulty;
    private final int turretsLayer, turretsHitboxLayer;
    private final TiledMap map;

    public RandomTurretClient(TiledMap map, int difficulty) {
        this.map = map;
        this.turretsLayer = this.map.getLayerIndex("RandomTurrets");
        this.turretsHitboxLayer = this.map.getLayerIndex("RandomTurretsHitbox");
        this.difficulty = difficulty;
    }

    public ArrayList<ShootingEnemy> getList() {

        RandomShootingEnemyFactory randomthreeshotFactory = new RandomThreeShotTurretFactory();

        ArrayList<ShootingEnemy> array = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                /*
                Based on the ID of the tiles in the particular layer of the map
                this method creates an array list of different objects that 
                match the ID. 
                 */
                if (map.getTileId(x, y, turretsLayer) > 14 && map.getTileId(x, y, turretsLayer) < 20) {
                    array.add(randomthreeshotFactory.create(x, y, calculateHitboxArea(x, y),difficulty,"./src/graphics/png/thrower/fire_90.png"));
                }
            }
        }
        return array;
    }

    private Shape calculateHitboxArea(int x, int y) {

        int hitboxID = map.getTileId(x, y, turretsHitboxLayer);
        Shape[] hitboxarea = {null};
        int k = 0;
        int j = 0;
        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {
                if (map.getTileId(x, y, turretsHitboxLayer) == hitboxID) {
                    if (k == 0) {
                        hitboxarea[0] = new Rectangle(x * 30, y * 30, 30, 30);
                        k++;
                    } else {
                        hitboxarea = hitboxarea[0].union(new Rectangle(x * 30, y * 30, 30, 30));
                    }
                }
            }
        }
        return hitboxarea[0];
    }
}
