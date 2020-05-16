import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class goblin here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Artwork by MGG - "Pixelantasy" 
 */
public class goblin extends enemy
{
    /**
     * Act - do whatever the goblin wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int max = 200;
    int min = 0;
    int boundary = 100;
    int moveSpeed = 2*(int)Math.pow(-1, Greenfoot.getRandomNumber(101));
    private boolean spriteDir = true;
    private GreenfootImage left = new GreenfootImage("goblin_2st.png");
    private GreenfootImage right = new GreenfootImage("goblin_2.png");
    
    private GreenfootImage[] attackRight = new GreenfootImage[3];
    private GreenfootImage[] attackLeft = new GreenfootImage[3];
    long lastAdded = System.currentTimeMillis();
    long lastChange = System.currentTimeMillis();
    
    private int frame = 0;
    boolean finishAnimation = true;
    
    public goblin()
    {
        initSprites();
    }
    
    public void act() 
    {
        falling();
        long curChange = System.currentTimeMillis();
        //se plimba intre 2 parametrii atata timp cat nu observa nici-un jucator
        if(detection()==true || underPlayer()==true)
            movement();
       else{//ataca daca jucatorul nu este mort
            if(getWorld().getObjects(warrior.class).get(0).dead == false && getWorld().getObjects(barbarian.class).get(0).dead == false){
                attack();
            }else{
                movement();
            }
        }
       if(finishAnimation == false){
           //realizeaza animatia de atac
           if(curChange >=lastChange+130){
               attackAnimation(spriteDir);
               lastChange=curChange;
           }
       }
        
    }
    
    void attack()
    {
        Actor a;
        //verifica care jucator e mai aproape pentru a-l ataca
        if(Math.abs(getX() - getWorld().getObjects(warrior.class).get(0).getX()) < Math.abs(getX() - getWorld().getObjects(barbarian.class).get(0).getX())){
            a = getWorld().getObjects(warrior.class).get(0);
        }else{
            a = getWorld().getObjects(barbarian.class).get(0);
        }
        //ataca in directia corespunzatoare
        if(getX() - a.getX() > 0){
            if(finishAnimation==true)
                setImage(left);
            spriteDir=true;
            long curTime  = System.currentTimeMillis();
            if(curTime >= lastAdded + 1500){
                finishAnimation = false;
                getWorld().addObject(new arrowst(), getX()-60, getY());
                lastAdded=curTime;
            }
        }else{
            if(finishAnimation == true)
                setImage(right);
            spriteDir=false;
            long curTime  = System.currentTimeMillis();
            if(curTime >= lastAdded + 1500){
                finishAnimation = false;
                getWorld().addObject(new arrowdr(), getX()+60, getY());
                lastAdded=curTime;
            }
        }
    }
    //se plimba intre 2 parametri
    void movement()
    {
        if(boundary == min || boundary == max || leftCollision() == true || rightCollision()==true || isAtEdge()==true){
            moveSpeed=-moveSpeed;
        }
        if(moveSpeed>0)
                setImage(right);
            else
                setImage(left);
        setLocation(getX()+moveSpeed, getY());
        boundary-=moveSpeed;
    }
    //vede jucatorii pe un anumit radius
    boolean detection()
    {
        return getObjectsInRange(400, barbarian.class).isEmpty() && getObjectsInRange(400, warrior.class).isEmpty() ;
    }
    
    void initSprites()
    {
        attackRight[0] = new GreenfootImage("Characters/Goblin/goblin_2_attack_001.png");
        attackRight[1] = new GreenfootImage("Characters/Goblin/goblin_2_attack_002.png");
        attackRight[2] = new GreenfootImage("Characters/Goblin/goblin_2_attack_003.png");
    
        attackLeft[0] = new GreenfootImage("Characters/Goblin/goblin_2_attack_001_left.png");
        attackLeft[1] = new GreenfootImage("Characters/Goblin/goblin_2_attack_002_left.png");
        attackLeft[2] = new GreenfootImage("Characters/Goblin/goblin_2_attack_003_left.png");
    }
    
    void attackAnimation(boolean directie)
    {
        if(directie == false){
            switch(frame){
                case 0 : setImage(attackRight[frame]); break;
                case 1 : setImage(attackRight[frame]); break;
                case 2 : setImage(attackRight[frame]); frame = -1; finishAnimation = true; break;
            }
        }else{
            switch(frame){
                case 0 : setImage(attackLeft[frame]); break;
                case 1 : setImage(attackLeft[frame]); break;
                case 2 : setImage(attackLeft[frame]); frame = -1; finishAnimation = true; break;
            }
        }
        frame++;
        return;
    }
}
