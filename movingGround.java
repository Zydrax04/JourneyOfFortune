import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class fallingGround here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class movingGround extends ground
{
    /**
     * Act - do whatever the fallingGround wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int min = 0;
    int max = 200;
    public int moveSpeedRight = 1;
    public int moveSpeedLeft = -1;
    int curMoveSpeed = 1;
    int x = 100;
    public void act() 
    {
        //platforma se misca pe orizontala intre 2 valori
        //apoi isi schimba directia
        if(x == min){
            curMoveSpeed = moveSpeedRight;
        }
        if(x == max){
            curMoveSpeed = moveSpeedLeft;
        }
        setLocation(getX()+ curMoveSpeed, getY());
        x += curMoveSpeed;
    }    
}
