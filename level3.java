import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class level3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 * 
 * Music by Adrian von Ziegler
 * Artwork by MGG - "Pixelantasy" 
 */
public class level3 extends World
{
    public Actor leftHeart[];
    public Actor rightHeart[];
    
    boolean spawn=false;
    long lockScreen = System.currentTimeMillis();

    int hp1;
    int hp2;

    /**
     * Constructor for objects of class level3.
     * 
     */
    public level3(int hpWar, int hpBarb)
    {    
        super(1024, 720, 1);

        for(int x=0; x<=getWidth()+100; x+=180){
            addObject(new ground(), x, getHeight()); 
        }

        addObject(new warrior(), 388, 408);
        addObject(new barbarian(), 460, 410);

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
        long curTime = System.currentTimeMillis();
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

        if( finishLevel()==true && spawn==false ){//verifica daca jocul e castigat
            for(int i=0; i<15; i++)
            {
                addObject(new coin(), getRandomNumber(100, 900), getRandomNumber(100, 620));
            }
            spawn=true;
            lockScreen=curTime;
            
            addObject(new gameWin(), 512, 360);
            
        }
        checkGameOver();
        if(getObjects(gameOver.class).isEmpty()==false)
        {
            //daca unul dintre jucatori e mort jocul se poate reincepe
            if(Greenfoot.isKeyDown("enter"))
                Greenfoot.setWorld(new level1());
        }
        if(curTime>lockScreen+2500 && spawn==true)
            Greenfoot.stop();
    }

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

    boolean finishLevel()
    {
        if(getObjects(key.class).isEmpty() == true){
           return true;
        }else{
            return false;
        }
    }

    void checkGameOver()
    {
        if( getObjects(player.class).get(0).gameOver()==true )
            addObject(new gameOver(), 512, 360);
    }
    
    public int getRandomNumber(int start,int end)
    {
       int normal = Greenfoot.getRandomNumber(end-start+1);
       return normal+start;
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        chest chest = new chest();
        addObject(chest,973,656);
        chest chest2 = new chest();
        addObject(chest2,859,663);
        chest chest3 = new chest();
        addObject(chest3,915,586);
        chest2.setLocation(852,659);
        chest3.setLocation(916,574);
        chest2.setLocation(848,652);
        chest.setLocation(975,650);
        chest3.setLocation(913,578);
        chest2.setLocation(856,650);
        chest3.setLocation(917,586);
        movingGround movingground = new movingGround();
        addObject(movingground,471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(471,367);
        movingground.setLocation(1019,485);
        removeObject(movingground);
        fallingGround fallingground = new fallingGround();
        addObject(fallingground,171,452);
        movingGround movingground2 = new movingGround();
        addObject(movingground2,834,442);
        ground ground = new ground();
        addObject(ground,694,150);
        ground ground2 = new ground();
        addObject(ground2,332,157);
        ground ground3 = new ground();
        addObject(ground3,534,444);
        ground ground4 = new ground();
        addObject(ground4,902,289);
        movingground2.setLocation(912,450);
        ground3.setLocation(469,517);
        ground2.setLocation(174,168);
        ground.setLocation(527,142);
        ground4.setLocation(867,263);
        ground4.setLocation(848,184);
        movingground2.setLocation(924,357);
        ground3.setLocation(477,567);
        movingground2.setLocation(779,416);
        ground4.setLocation(898,248);
        ground.setLocation(547,192);
        ground2.setLocation(179,209);
        fallingground.setLocation(246,407);
        ground2.setLocation(139,244);
        ground3.setLocation(503,538);
        fallingground.setLocation(264,495);
        ground2.setLocation(374,187);
        fallingground.setLocation(78,508);
        ground2.setLocation(293,323);
        movingground2.setLocation(738,494);
        ground3.setLocation(438,497);
        movingground2.setLocation(717,526);
        movingground2.setLocation(730,472);
        ground4.setLocation(867,215);
        ground.setLocation(506,151);
        ground2.setLocation(288,297);
        key key = new key();
        addObject(key,519,94);
        key.setLocation(507,91);
        key.setLocation(500,97);
        goblin goblin = new goblin();
        addObject(goblin,88,636);
        goblin goblin2 = new goblin();
        addObject(goblin2,682,637);
        goblin.setLocation(207,631);
    }
}
