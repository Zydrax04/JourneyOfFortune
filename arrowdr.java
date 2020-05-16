import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class arrowdr here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class arrowdr extends arrow
{
    /**
     * Act - do whatever the arrowdr wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    Actor actor = this;
    public void act() 
    {
        // Add your action code here.
        int spriteWidth = this.getImage().getWidth();
        setLocation( getX()+8, getY() );
        //dispare daca iese din lume sau daca atinge un obiect
        if(rightCollision()==true || isAtEdge()==true)
            getWorld().removeObject(actor);
        else if(playerSideCollision() == 2){ //verifica coliziunea in dreapta cu un jucator
            Actor player1 = getOneObjectAtOffset(spriteWidth, 0, warrior.class);
            Actor player2 = getOneObjectAtOffset(spriteWidth, 0, barbarian.class);
            //verifica jucatorul corespunzator
            if(player1 != null){
                //scade viata si il impinge intr-o parte
                getWorld().getObjects(warrior.class).get(0).setLocation(getWorld().getObjects(warrior.class).get(0).getX()+10, getWorld().getObjects(warrior.class).get(0).getY()-20);
                getWorld().getObjects(warrior.class).get(0).hp--;
            }else{
                //scade viata si il impinge intr-o parte
                getWorld().getObjects(barbarian.class).get(0).setLocation(getWorld().getObjects(barbarian.class).get(0).getX()+10, getWorld().getObjects(barbarian.class).get(0).getY()-20);
                getWorld().getObjects(barbarian.class).get(0).hp--;
            }
            getWorld().removeObject(actor);
        }
    }    
}
