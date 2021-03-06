package menu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Brings the game to the SelectLevel State
 */
public class ChooseLevel implements Command {

    @Override
    public void execute(GameContainer gc, int delta, StateBasedGame sbg) throws SlickException {
        sbg.getState(5).init(gc, sbg);
        sbg.enterState(5);
        try {
            Thread.sleep(200);  // Prevents accidental clicks
        } catch (InterruptedException ex) {}
    }
}