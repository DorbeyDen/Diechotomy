package core;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

public abstract class AbilityManager {

    public static ArrayList<Unit> units  = new ArrayList<Unit>();



    public static void updateCharacter()
    {
        for(int i = 0; i< units.size() ;i++)
        {
            if(units.get(i) instanceof Character)
            {
                units.get(i).update(); 

            }
        }
    }

    public static void updateEnemies()
    {
        for(int i = 0; i< units.size(); i++)
        {
            if(units.get(i) instanceof Enemy)
            {
                units.get(i).update(); 

            }
        }
    }

    public static void renderAll(Graphics g)
    {
        for(int i = 0; i < units.size(); i++)
        {
            units.get(i).render(g);
        }
    }


//    public static void speedUp() 
//    {
//        EntityManager.getCharacter().m
//    }

    public static void increaseFireRate()
    {
        Gun.fireRate +=5; 
    }

    public static void increaseMaxHealth()
    {
        EntityManager.getCharacter().maxHealth+=20;
    }

}