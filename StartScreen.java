import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Music by Adrian von Ziegler
 * Artwork by MGG - "Pixelantasy" 
 */
public class StartScreen extends World
{

    /**
     * Constructor for objects of class StartScreen.
     * 
     */
    public StartScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 
        GreenfootSound music = new GreenfootSound("Celtic Music - Fable.mp3");
        
        music.playLoop();
    }
    
    public void act()
    {
        if(Greenfoot.isKeyDown("enter"))
            Greenfoot.setWorld(new level1());
    }
}
