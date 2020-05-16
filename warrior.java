import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class warrior here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Artwork by MGG - "Pixelantasy" 
 */
public class warrior extends player
{
    /**
     * Act - do whatever the warrior wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage[] attackRight = new GreenfootImage[3];
    private GreenfootImage[] attackLeft = new GreenfootImage[3];
    private GreenfootImage[] die = new GreenfootImage[5];
    private int frame = 0;
    int moveSpeedRight = 4;
    int moveSpeedLeft = -4;
    int animCounter = 0;
    boolean dir = true;
    boolean finishAnimation = true;
    long lastTime = System.currentTimeMillis();
    long nextAttack = System.currentTimeMillis();
    public boolean dead = false;
    public int hp = 3;
    int platMoveSpeed = 0;
    
    public warrior()
    {
        initSprites();
    }
    
    public void act() 
    {
        //contorizez timpul
        long curTime  = System.currentTimeMillis();
        //aplic gravitatie
        falling();
        
        if(hp > 0){
            //daca caracterul nu este mort si acesta nu se afla in animatie, poate sa se deplaseze
            if(finishAnimation == true && gameOver()==false)
                movement();
            if(underCollision()==true || underPlayer()==true)
                fallSpeed=6;
        }else{
            //animatie de moarte
            dead = true;
            if(animCounter%4 == 0 && frame <= 4)
                dieAnimation();
        }
        if(underCollision()==true || underPlayer()==true)
            fallSpeed=6;
        //in caz ca caracterul se afla pe o platforma miscatoare, se misca cu aceasta
        if(onMovingGround()==true){
            platMoveSpeed = getWorld().getObjects(movingGround.class).get(0).curMoveSpeed;
            move(2*platMoveSpeed);
        }
        if(onFallingGround()==true){
            platMoveSpeed = getWorld().getObjects(fallingGround.class).get(0).curMoveSpeed;
            setLocation(getX(), getY()+2*platMoveSpeed);
        }
        //daca a trecut o secunda de la ultimul atac
        if(curTime>nextAttack+1000){
            //verific daca "space" a fost apasat
            if(Greenfoot.isKeyDown("space")){
                //marchez faptul ca caracterul e in mijlocul unei animatii
                finishAnimation = false;
            }
        }
        //execut animatia pana la capat
        if(finishAnimation == false){
            if(curTime>lastTime+110){//timp intre schimbarea imaginilor
                attackAnimation(dir);
                lastTime=curTime;
            }
            if(frame == 1){
                //verific daca caracterul a atacat cat timp a fost in coliziune cu un inamic
                // daca e adevarat atunci omoara inamicul
                if(dir == true && enemySideCollision() == 2){
                    getWorld().removeObject(enemy());
                }
                else if(dir == false && enemySideCollision() == 1){
                    getWorld().removeObject(enemy());
                }
            }
        }
        animCounter++;
    }
    
    //se verifica tastele apasate
    void movement()
    {
        if(Greenfoot.isKeyDown("a") && enemySideCollision()!=1){
            move(moveSpeedLeft);
            setImage("Characters/Warrior/soldier_left.png");
            dir=false;
            if(leftCollision() == true || playerSideCollision() == 1){
                this.setLocation(getX() + moveSpeedRight, getY());
            }
        }
        
        if(Greenfoot.isKeyDown("d") && enemySideCollision()!=2){
            move(moveSpeedRight);
            setImage("Characters/Warrior/soldier_right.png");
            dir=true;
            if(rightCollision() == true || playerSideCollision() == 2){
                this.setLocation(getX() + moveSpeedLeft, getY());
            }
        }
        
        if(Greenfoot.isKeyDown("w") && underPlayer()==false && underCollision() == false)
        {
            jump();
        }
    }
    
    void falling()
    {
        if(onGround() == true || onPlayer() == true || onEnemy()==true){
            fallSpeed = 0;
        }else{
            gravitatie();
        }
    }
    
    void jump()
    {
        if(onGround() == true || onPlayer() == true || onEnemy()==true){
            fallSpeed = -24;
            gravitatie();
        }
    }
    
    void initSprites()
    {
        attackRight[0] = new GreenfootImage("Characters/Warrior/soldier_attack_001.png");
        attackRight[1] = new GreenfootImage("Characters/Warrior/soldier_attack_002.png");
        attackRight[2] = new GreenfootImage("Characters/Warrior/soldier_right.png");
        
        attackLeft[0] = new GreenfootImage("Characters/Warrior/soldier_attack_001_left.png");
        attackLeft[1] = new GreenfootImage("Characters/Warrior/soldier_attack_002_left.png");
        attackLeft[2] = new GreenfootImage("Characters/Warrior/soldier_left.png");
        
        die[0] = new GreenfootImage("Characters/Warrior/soldier_die_001.png");
        die[1] = new GreenfootImage("Characters/Warrior/soldier_die_002.png");
        die[2] = new GreenfootImage("Characters/Warrior/soldier_die_003.png");
        die[3] = new GreenfootImage("Characters/Warrior/soldier_die_004.png");
        die[4] = new GreenfootImage("Characters/Warrior/soldier_die_005.png");
    }
    
    void attackAnimation(boolean directie)
    {
        //in functie de ultima directie in care se afla jucatorul, executa animatia de atac corespunztoare
        if(directie == true){
            switch(frame){
                case 0 : setImage(attackRight[frame]); break;
                case 1 : setImage(attackRight[frame]); break;
                case 2 : setImage(attackRight[frame]); frame = -1; nextAttack=System.currentTimeMillis(); finishAnimation = true; break;
            }
        }else{
            switch(frame){
                case 0 : setImage(attackLeft[frame]); break;
                case 1 : setImage(attackLeft[frame]); break;
                case 2 : setImage(attackLeft[frame]); frame = -1; nextAttack=System.currentTimeMillis(); finishAnimation = true; break;
            }
        }
        frame++;
        return;
    }
    
    void dieAnimation()
    {
        switch(frame){
            case 0 : setImage(die[frame]); break;
            case 1 : setImage(die[frame]); break;
            case 2 : setImage(die[frame]); break;
            case 3 : setImage(die[frame]); break;
            case 4 : setImage(die[frame]); break;
        }
        frame++;
    }
}
