import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
//import java.util.List;

/**
 * Write a description of class level1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Music by Adrian von Ziegler
 * Artwork by MGG - "Pixelantasy" 
 */
public class level1 extends World
{

    /**
     * Constructor for objects of class level1.
     * 
     */
    public Actor leftHeart[];
    public Actor rightHeart[];
    
    Actor warrior = new warrior();
    Actor barbarian = new barbarian();

    int lasthpWar;
    int lasthpBarb;

    public level1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1024, 720, 1); 

        addObject(warrior, 70, 629);
        addObject(barbarian, 170, 629);
        addObject(new goblin(), 750, 629);
        prepare();
        leftHeart = new Actor[5];
        rightHeart = new Actor[5];
        lasthpWar = getObjects(warrior.class).get(0).hp;
        lasthpBarb = getObjects(barbarian.class).get(0).hp;
        for(int i=0; i<lasthpWar; i++){
            leftHeart[i] = new heart();
        }
        for(int i=0; i<lasthpBarb; i++){
            rightHeart[i] = new heart();
        }

        level();
        barbHP();
        warHP();
    }

    public void act()
    {
        if(lasthpWar - getObjects(warrior.class).get(0).hp != 0){
            //scade numarul de inimi de pe ecran
            lasthpWar = getObjects(warrior.class).get(0).hp;
            removeObject(leftHeart[lasthpWar]);
        }
        if(lasthpBarb - getObjects(barbarian.class).get(0).hp != 0){
            //scade numarul de inimi de pe ecran
            lasthpBarb = getObjects(barbarian.class).get(0).hp;
            removeObject(rightHeart[lasthpBarb]);
        }
        //verifica trecerea la urmatorul nivel
        if( nextLevel()==true ){
            Greenfoot.setWorld( new level2(lasthpWar, lasthpBarb) );
        }
        checkGameOver();
        if(getObjects(gameOver.class).isEmpty()==false)
        {
            if(Greenfoot.isKeyDown("enter"))
                Greenfoot.setWorld(new level1());
        }
    }

    void level()
    {
        for(int x=0; x<=getWidth()+100; x+=180){
            addObject(new ground(), x, getHeight()); 
        }
    }
    //afisarea vietilor la barbarian
    void barbHP()
    {
        showText("Barbarian:", 940, 30);
        showText("HP:", 940, 55);
        int xStart = 970;
        int y = 55;
        addObject(rightHeart[0], xStart, y);
        addObject(rightHeart[1], xStart + getObjects(heart.class).get(0).getImage().getWidth()/2, y);
    }
    //afisarea vietilor la warrior
    void warHP()
    {
        showText("Warrior:", 55, 30);
        showText("HP:", 55, 55);
        int xStart = 90;
        int y = 55;
        addObject(leftHeart[0], xStart, y);
        addObject(leftHeart[1], xStart + getObjects(heart.class).get(0).getImage().getWidth()/2, y);
        addObject(leftHeart[2], xStart + getObjects(heart.class).get(0).getImage().getWidth(), y);
    }

    boolean nextLevel()
    {
        int WarX = getObjects(warrior.class).get(0).getX();
        int WarY = getObjects(warrior.class).get(0).getY();
        int BarbX = getObjects(barbarian.class).get(0).getX();
        int BarbY = getObjects(barbarian.class).get(0).getY();
        int doorX = getObjects(door_open.class).get(0).getX();
        int doorY = getObjects(door_open.class).get(0).getY();
        //verifica daca a fost luata cheia
        if(getObjects(key.class).isEmpty() == true){
            //verifica daca unul dintre jucatori se afla pe pozitia usii
            if(Math.abs(WarX - doorX) < 20 && Math.abs(WarY - doorY) < 20){
                return true;
            }
            if(Math.abs(BarbX - doorX) < 20 && Math.abs(BarbY - doorY) < 20){
                return true;
            }
            return false;
        }else{
            return false;
        }
    }
    
    void checkGameOver()
    {
        if( getObjects(player.class).get(0).gameOver()==true )
            addObject(new gameOver(), 512, 360);
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        key key = new key();
        addObject(key,517,591);
        door door = new door();
        addObject(door,927,610);
        addObject(new door_open(), 977, 617);
        door.setLocation(978,622);
        door.setLocation(978,618);
        door.setLocation(977,618);
        ground ground = new ground();
        addObject(ground,352,496);
        ground ground2 = new ground();
        addObject(ground2,852,302);
        ground2.setLocation(848,294);
        ground.setLocation(367,483);
        movingGround movingground = new movingGround();
        addObject(movingground,621,419);
        ground ground3 = new ground();
        addObject(ground3,132,283);
        goblin goblin = new goblin();
        addObject(goblin,138,202);
        goblin.setLocation(134,197);
        ground ground4 = new ground();
        addObject(ground4,309,289);
        ground4.setLocation(302,285);
        ground4.setLocation(302,283);
        goblin.setLocation(210,197);
        key.setLocation(859,212);
        key.setLocation(881,242);
    }
}
