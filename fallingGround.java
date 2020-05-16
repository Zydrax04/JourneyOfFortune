import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fallingGround here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class fallingGround extends ground
{
    /**
     * Act - do whatever the fallingGround wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int min = 0;
    int max = 200;
    public int moveSpeedDown = 1;
    public int moveSpeedUp = -1;
    int curMoveSpeed = 1;
    int y = 100;
    public void act() 
    {
        //platforma se misca pe verticala intre 2 valori
        //apoi isi schimba directia
        if(y == min){
            curMoveSpeed = moveSpeedDown;
        }
        if(y == max){
            curMoveSpeed = moveSpeedUp;
        }
        setLocation(getX(), getY() + curMoveSpeed);
        y += curMoveSpeed;
    }    
}
