import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class level2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Music by Adrian von Ziegler
 * Artwork by MGG - "Pixelantasy" 
 */
public class level2 extends World
{

    /**
     * Constructor for objects of class level2.
     * 
     */

    public Actor leftHeart[];
    public Actor rightHeart[];

    int hp1, hp2;

    public level2(int hpWar, int hpBarb)
    {    
        super(1024, 720, 1);

        for(int x=0; x<=getWidth()+100; x+=180){
            addObject(new ground(), x, getHeight()); 
        }

        addObject(new warrior(), 70, 640);
        addObject(new barbarian(), 170, 640);

        getObjects(warrior.class).get(0).hp = hpWar;
        getObjects(barbarian.class).get(0).hp = hpBarb;

        leftHeart = new Actor[5];
        rightHeart = new Actor[5];
        for(int i=0; i<hpWar; i++){
            leftHeart[i] = new heart();
        }
        for(int i=0; i<hpBarb; i++){
            rightHeart[i] = new heart();
        }

        hp1 = hpWar;
        hp2 = hpBarb;

        barbHP();
        warHP();
        prepare();
    }

    public void act()
    {
         if(hp1 - getObjects(warrior.class).get(0).hp != 0){
             //scade numarul de inimi de pe ecran
            hp1 = getObjects(warrior.class).get(0).hp;
            removeObject(leftHeart[hp1]);
        }
        if(hp2 - getObjects(barbarian.class).get(0).hp != 0){
            //scade numarul de inimi de pe ecran
            hp2 = getObjects(barbarian.class).get(0).hp;
            removeObject(rightHeart[hp2]);
        }
         //verifica trecerea la urmatorul nivel
        if( nextLevel()==true ){
            Greenfoot.setWorld( new level3(hp1, hp2) );
        }
        checkGameOver();
        if(getObjects(gameOver.class).isEmpty()==false)
        {
            if(Greenfoot.isKeyDown("enter"))
                Greenfoot.setWorld(new level1());
        }
    }
    //afisarea vietilor la barbarian
    void barbHP()
    {
        showText("Barbarian:", 940, 30);
        showText("HP:", 940, 55);
        int xStart = 970;
        int y = 55;
        if(hp2 >= 1)
            addObject(rightHeart[0], xStart, y);
        if(hp2 >= 2)
            addObject(rightHeart[1], xStart + getObjects(heart.class).get(0).getImage().getWidth()/2, y);
    }
    //afisarea vietilor la warrior
    void warHP()
    {
        showText("Warrior:", 55, 30);
        showText("HP:", 55, 55);
        int xStart = 90;
        int y = 55;
        if(hp1 >= 1)
            addObject(leftHeart[0], xStart, y);
        if(hp1 >= 2)
            addObject(leftHeart[1], xStart + getObjects(heart.class).get(0).getImage().getWidth()/2, y);
        if(hp1 >= 3)
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
        ground ground = new ground();
        addObject(ground,325,503);
        fallingGround fallingground = new fallingGround();
        addObject(fallingground,519,343);
        ground ground2 = new ground();
        addObject(ground2,782,510);
        ground ground3 = new ground();
        addObject(ground3,790,312);
        ground2.setLocation(777,529);
        ground3.setLocation(771,330);
        ground ground4 = new ground();
        addObject(ground4,785,154);
        goblin goblin = new goblin();
        addObject(goblin,763,441);
        goblin goblin2 = new goblin();
        addObject(goblin2,752,252);
        goblin goblin3 = new goblin();
        addObject(goblin3,758,61);
        ground ground5 = new ground();
        addObject(ground5,950,534);
        ground ground6 = new ground();
        addObject(ground6,947,339);
        goblin2.setLocation(847,247);
        ground6.setLocation(947,332);
        goblin2.setLocation(871,243);
        ground5.setLocation(946,528);
        goblin.setLocation(981,440);
        goblin2.setLocation(981,245);
        goblin3.setLocation(327,405);
        goblin3.setLocation(508,227);
        goblin3.setLocation(324,409);
        removeObject(goblin3);
        ground4.setLocation(772,145);
        ground4.setLocation(772,202);
        fallingground.setLocation(536,345);
        ground.setLocation(340,500);
        door door = new door();
        addObject(door,981,628);
        door.setLocation(979,623);
        door_open door_open = new door_open();
        addObject(door_open,811,634);
        door_open.setLocation(980,621);
        key key = new key();
        addObject(key,763,154);
        ground ground7 = new ground();
        addObject(ground7,98,331);
        fallingground.setLocation(549,345);
        fallingground.setLocation(556,355);
        ground4.setLocation(812,204);
        key.setLocation(781,117);
        ground4.setLocation(808,183);
        key.setLocation(802,132);
        ground6.setLocation(943,330);
        goblin2.setLocation(982,242);
        goblin.setLocation(984,435);
        ground4.setLocation(813,183);
        key.setLocation(808,90);
        ground4.setLocation(839,146);
        key.setLocation(838,94);
        key.setLocation(845,92);
        ground4.setLocation(839,154);
        key.setLocation(846,107);
        key.setLocation(845,103);
        ground4.setLocation(843,156);
        ground4.setLocation(812,169);
        key.setLocation(813,120);
        fallingground.setLocation(556,360);
        ground7.setLocation(84,344);
        ground.setLocation(344,512);
    }
}
