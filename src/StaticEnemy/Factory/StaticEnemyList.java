package StaticEnemy.Factory;

import StaticEnemy.StaticDamage;
import java.util.ArrayList;
import org.newdawn.slick.tiled.TiledMap;

public class StaticEnemyList {

    /*
        The "StaticDamageList" class uses the concrete factories multiple
        times to create an ArrayList of StaticDamage objects, that will be used
        by the CollisionManager for checking the collisions. 
     */
    private int x;
    private int y;
    private final int objlayer;
    private final TiledMap map;

    public StaticEnemyList(TiledMap map) {
        this.map = map;
        this.objlayer = this.map.getLayerIndex("StaticEnemies");
    }

    public ArrayList<StaticDamage> getStaticEnemyList() {
        StaticEnemyFactory oneFactory = new OneHeartSpikeFactory();
        StaticEnemyFactory halfFactory = new HalfHeartSpikeFactory();
        StaticEnemyFactory acidFactory = new AcidLakeFactory();
        StaticEnemyFactory barrelFactory = new BarrelFactory();

        ArrayList<StaticDamage> staticDamageArray = new ArrayList<>();

        for (y = 0; y < map.getHeight(); y++) {
            for (x = 0; x < map.getWidth(); x++) {

                /*
                Based on the ID of the tiles in the particular layer of the map
                this method creates an array list of different objects that 
                match the ID. 
                 */
                if (map.getTileId(x, y, objlayer) == 11 || map.getTileId(x, y, objlayer) == 12) {
                    staticDamageArray.add(oneFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == 13 || map.getTileId(x, y, objlayer) == 14) {
                    staticDamageArray.add(halfFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == 61 || map.getTileId(x, y, objlayer) == 62) {
                    staticDamageArray.add(acidFactory.create(x * 30, y * 30));
                } else if (map.getTileId(x, y, objlayer) == 75) {
                    staticDamageArray.add(barrelFactory.create(x * 30, y * 30));
                }
            }
        }

        return staticDamageArray;
    }
}