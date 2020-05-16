import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class enemy extends Actor
{
    /**
     * Act - do whatever the enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int fallSpeed=0;
    int x=100;
    int moveSpeed=4;
    public void act() 
    {
        // Add your action code here
         
    }
    
    void falling()
    {
        if(onGround() == true){
            fallSpeed = 0;
        }else{
            gravitatie();
        }
    }
    //aplicam graviatia asupra caracterelor
    public void gravitatie()
    {
        setLocation(getX(), getY()+fallSpeed);
        if(fallSpeed < 16)
            fallSpeed += 2;
    }
    //coliziuni cu pamantul din toate partile
     boolean onGround()
    {
        int spriteHeight = getImage().getHeight();
        int spriteWidth = getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, ground.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, ground.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        }
    }
    boolean underCollision()
    {
        int spriteHeight = getImage().getHeight();
        int spriteWidth = getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, (-spriteHeight/2 - 10), ground.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, (-spriteHeight/2 - 10), ground.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        } 
    }
    boolean rightCollision()
    {
        int spriteWidth = getImage().getWidth()/2;
        int spriteHeight = getImage().getHeight()/4;
        Actor g = getOneObjectAtOffset(spriteWidth + 10, spriteHeight, ground.class);
        Actor h = getOneObjectAtOffset(spriteWidth + 10, -spriteHeight, ground.class);
        
        return !(g==null && h==null);
    }
    boolean leftCollision()
    {
        int spriteWidth = getImage().getWidth()/2;
        int spriteHeight = getImage().getHeight()/4;
        Actor g = getOneObjectAtOffset(-spriteWidth - 10, spriteHeight, ground.class);
        Actor h = getOneObjectAtOffset(spriteWidth + 10, -spriteHeight, ground.class);
        
        return !(g==null && h==null);
    }
    //coliziune cu jucatorii
    int playerSideCollision()
    {
        int spriteWidth = this.getImage().getWidth();
        int spriteHeight = this.getImage().getWidth()/4;
        Actor stanga = getOneObjectAtOffset(-spriteWidth/2-10, spriteHeight, player.class);
        Actor dreapta = getOneObjectAtOffset(spriteWidth/2+10, spriteHeight, player.class);
        if(dreapta == null && stanga == null){
            return 0;
        }else{
            if(dreapta == null){
                return 1;
            }else{
                return 2;
            }
        }
    }
    
    boolean onPlayer()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed-6, player.class);
        if(g == null){
            return false;
        }else{
            return true;
        }
    }
    
    boolean underPlayer()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, (-spriteHeight/2 - 10), player.class);
        if(g == null){
            return false;
        }else{
            return true;
        } 
    }
    
    int getRandom(int min, int max)
    {
        return Math.abs(Greenfoot.getRandomNumber(max) - Greenfoot.getRandomNumber(min));
    }
}
