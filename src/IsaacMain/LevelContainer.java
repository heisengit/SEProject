/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IsaacMain;

import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 *
 * @author danya
 */
public class LevelContainer implements GalaxyComponent{
    
    private String name;
    private ArrayList<GalaxyComponent> children;
    private static final long serialversionUId = 1;
    
    public LevelContainer(String name){
        this.name = name;
        this.children = new ArrayList<GalaxyComponent>();
    }
    
    public LevelContainer(String name, ArrayList<GalaxyComponent> children){
        this.name = name;
        this.children = children;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getName(String name){
        return this.name;
    }
    
    @Override
    public void add(GalaxyComponent galaxyComponent){
        this.children.add(galaxyComponent);
    }
    
    @Override
    public ArrayList<GalaxyComponent> getChildren(){
        return this.children;
    }
    
    @Override
    public GalaxyComponent getChild(int index){
        return this.children.get(index);
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException{
        
    }
    
    @Override
    public void update(GameContainer gc, int delta) throws SlickException{
        
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException{
        
    }
}