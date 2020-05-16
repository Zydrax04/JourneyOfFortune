import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class player extends Actor
{
    public int fallSpeed = 0;
    public int yOffset = 10;
    public int xOffset = 10;
    public int xdirection = 1;
    public int ydirection = 1;

    Actor dreapta;
    Actor stanga;
    
    /**
     * Act - do whatever the player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        //
    }
    
    //aplicam graviatia asupra caracterelor
    public void gravitatie()
    {
        setLocation(getX(), getY()+fallSpeed);
        if(fallSpeed < 16)
            fallSpeed += 2;
    }
    
    //verififca coliziunea jucatorilor cu obiectele din jur
    
    //cu pamantul
    boolean onGround()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, ground.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, ground.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        }
    }
    
    //cu platforme miscatoare
    boolean onMovingGround()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, movingGround.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, movingGround.class);
        return !(g == null && h == null);
    }
    boolean onFallingGround()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, fallingGround.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, fallingGround.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        }
    }
    
    
    //cu platformele de deasupra jucatorilor
    boolean underCollision()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, (-spriteHeight/2 -10), ground.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, (-spriteHeight/2 -10), ground.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        } 
    }
    
    //coliziune in stanga si in dreapta jucatorilor
    boolean rightCollision()
    {
        int spriteWidth = this.getImage().getWidth()/2;
        int spriteHeight = this.getImage().getHeight()/4;
        int groundWidth = getWorld().getObjects(ground.class).get(0).getImage().getWidth();
        
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight, ground.class);
        Actor h = getOneObjectAtOffset(spriteWidth, -spriteHeight, ground.class);
        
        return !(g==null && h==null);
    }
    boolean leftCollision()
    {
        int spriteWidth = this.getImage().getWidth()/2;
        int spriteHeight = this.getImage().getHeight()/4;
        Actor g = getOneObjectAtOffset(-spriteWidth, spriteHeight, ground.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, -spriteHeight, ground.class);
        
        return !(g==null && h==null);
    }
    
    //coliziune intre jucatori
    int playerSideCollision()
    {
        int spriteWidth = this.getImage().getWidth();
        int spriteHeight = this.getImage().getHeight()/4;
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
    
    //coliziune cu inmaic
    int enemySideCollision()
    {
        int spriteWidth = this.getImage().getWidth();
        int spriteHeight = this.getImage().getHeight()/4;
        stanga = getOneObjectAtOffset(-spriteWidth/2, spriteHeight, enemy.class);
        dreapta = getOneObjectAtOffset(spriteWidth/2, spriteHeight, enemy.class);
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
    
    
    //furnizeaza inmaicul cu care a avut loc coliziunea de mai sus
    Actor enemy()
    {
        if(enemySideCollision() == 1){
            return stanga;
        }else{
            return dreapta;
        }
    }
    
    boolean onPlayer()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, player.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, player.class);

        if(g == null && h == null){
            return false;
        }else{
            return true;
        }
    }
    
    boolean onEnemy()
    {
        int spriteHeight = this.getImage().getHeight();
        int spriteWidth = this.getImage().getWidth()/4;
        Actor g = getOneObjectAtOffset(spriteWidth, spriteHeight/2 + fallSpeed, enemy.class);
        Actor h = getOneObjectAtOffset(-spriteWidth, spriteHeight/2 + fallSpeed, enemy.class);
        if(g == null && h == null){
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
        Actor h = getOneObjectAtOffset(-spriteWidth, (-spriteHeight/2 - 10), player.class);
        if(g == null && h == null){
            return false;
        }else{
            return true;
        } 
    }
    
    
    //daca unul dintre jucatori e mort atunci se termina jocul
    boolean gameOver()
    {
        if(getWorld().getObjects(warrior.class).get(0).dead==true || getWorld().getObjects(barbarian.class).get(0).dead==true)
            return true;
        else
            return false;
    }
}
