import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class key here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class key extends Actor
{
    /**
     * Act - do whatever the key wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
   public void act() 
    {
        //daca un jucator a luat cheia, aceasta dispare
        if(playerCollision() == true){
            getWorld().removeObject(this);
        }
    }
    
   boolean playerCollision()
   {
        //coliziunea cheii cu jucatorul
        return !getObjectsInRange(50, warrior.class).isEmpty() || !getObjectsInRange(50, barbarian.class).isEmpty();
   }
}
