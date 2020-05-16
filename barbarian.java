import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class barbarian here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Artwork by MGG - "Pixelantasy" 
 */



public class barbarian extends player
{
    /**
     * Act - do whatever the barbarian wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int moveSpeedRight = 6;
    int moveSpeedLeft = -6;
    public boolean dead = false;
    public int hp = 2;
    private GreenfootImage[] dieRight = new GreenfootImage[5];
    private GreenfootImage[] dieLeft = new GreenfootImage[5];
    private int frame = 0;
    private int animCounter = 0;
    int platMoveSpeed = 0;
    
    public barbarian()
    {
        initSprites();
    }
    
    public void act() 
    {
        //daca caracterul nu este mort acesta poate sa se deplaseze
        if(hp > 0 ){
            if(gameOver() == false)
                playerControls();
            if(underCollision()==true || underPlayer()==true)
                fallSpeed=6;
        }else{
            //daca este mort se incepe animatia de moarte
            dead = true;
            if(animCounter%4 == 0 && frame <= 4)
                dieAnimation();
        }
        falling();
        animCounter++;
        //in caz ca caracterul se afla pe o platforma miscatoare, se misca cu aceasta
        if(onMovingGround()==true){
            platMoveSpeed = getWorld().getObjects(movingGround.class).get(0).curMoveSpeed;
            setLocation( getX() + 2*platMoveSpeed, getY() );
        }
        if(onFallingGround()==true){
            platMoveSpeed = getWorld().getObjects(fallingGround.class).get(0).curMoveSpeed;
            setLocation(getX(), getY()+2*platMoveSpeed);
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
            fallSpeed = -30;
            gravitatie();
        }
    }
    
    //se verifica tastele apasate
    void playerControls()
    {   
       if(Greenfoot.isKeyDown("left")){
            move(moveSpeedLeft);
            setImage("Characters/Jumper/barbarian_1_left.png");
            //in caz de coliziune, caracterul sta pe loc
            if(leftCollision() == true || playerSideCollision() == 1 || enemySideCollision() == 1){
                this.setLocation(getX() + moveSpeedRight, getY());
            }
        }
        
        if(Greenfoot.isKeyDown("right")){
            move(moveSpeedRight);
            setImage("Characters/Jumper/barbarian_1.png");
            //in caz de coliziune, caracterul sta pe loc
            if(rightCollision() == true || playerSideCollision() == 2 || enemySideCollision() == 2){
                this.setLocation(getX() + moveSpeedLeft, getY());
            }
        }
       if(Greenfoot.isKeyDown("up") && underPlayer() == false && underCollision() == false)
       {
           jump();
       }
    }
    
    
    //animatia de moarte a caracterului
    void dieAnimation()
    {
        switch(frame){
            case 0 : setImage(dieRight[frame]); break;
            case 1 : setImage(dieRight[frame]); break;
            case 2 : setImage(dieRight[frame]); break;
            case 3 : setImage(dieRight[frame]); break;
            case 4 : setImage(dieRight[frame]); break;
        }
        frame++;
    }
    
    void initSprites()
    {
        dieRight[0] = new GreenfootImage("Characters/Jumper/barbarian_1_die_001.png");
        dieRight[1] = new GreenfootImage("Characters/Jumper/barbarian_1_die_002.png");
        dieRight[2] = new GreenfootImage("Characters/Jumper/barbarian_1_die_003.png");
        dieRight[3] = new GreenfootImage("Characters/Jumper/barbarian_1_die_004.png");
        dieRight[4] = new GreenfootImage("Characters/Jumper/barbarian_1_die_005.png");
    }
}
