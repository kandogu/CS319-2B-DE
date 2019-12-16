package org.openjfx.model.preBossEntities;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import org.openjfx.model.commonEntities.Buff.Buff;
import org.openjfx.model.commonEntities.Location;
import org.openjfx.model.preBossEntities.Enemy.Tier1Enemy;
import org.openjfx.model.preBossEntities.Station.EnemyStation;
import org.openjfx.model.preBossEntities.Station.Station;
import org.openjfx.model.commonEntities.Bullet.Bullet;
import org.openjfx.model.preBossEntities.Enemy.Enemy;
import org.openjfx.model.preBossEntities.Meteor.Meteor;
import org.openjfx.model.commonEntities.Spacecraft.Spacecraft;
import org.openjfx.utilization.PositionHelper;

import java.io.Serializable;
import java.util.*;

public class PreBossMap implements Serializable {
    private static Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
    public static double MAP_HEIGHT = primaryScreenBounds.getHeight();
    public static double MAP_WIDTH = 10000;
    private int level;
    private java.util.Map<Long, Enemy> enemies = new HashMap<Long, Enemy>();
    private java.util.Map<Long, Station> stations = new HashMap<Long, Station>();
    private java.util.Map<Long, Bullet> bullets = new HashMap<Long, Bullet>();
    private java.util.Map<Long, Buff> buffs = new HashMap<Long, Buff>();
    private java.util.Map<Long, Meteor> meteors = new HashMap<Long, Meteor>();
    private boolean isSinglePlayer;
    private Spacecraft spacecraft1;
    private Spacecraft spacecraft2;

    public PreBossMap(boolean isSinglePlayer){
        this.isSinglePlayer = isSinglePlayer;
        initMap();
    }

    public PreBossMap(int level, boolean isSinglePlayer) {
        this.level = level;
        this.isSinglePlayer = isSinglePlayer;
    }

    private void initMap() {
            spacecraft1 = new Spacecraft(new Location(4960, 390));
        if(!isSinglePlayer)
            spacecraft2 = new Spacecraft(new Location(4960, 520));

        for (int i=0; i < 50 ; i++){
            double x = Math.random()*PreBossMap.MAP_WIDTH;
            double y = Math.random()*PreBossMap.MAP_HEIGHT;
            Tier1Enemy enemy = new Tier1Enemy(new Location(x,y), false);
            PositionHelper helper = new PositionHelper(enemy);
            helper.isInside(enemy.getHitBoxWidth(), enemy.getHitBoxHeight());
            if(helper.getRight() >= PreBossMap.MAP_WIDTH){
                x -= 100;
            }
            if(helper.getLeft() <= 0){
                x += 100;
            }
            if(helper.getTop() <= 0){
                y += 100;
            }
            if(helper.getBottom() >= PreBossMap.MAP_HEIGHT){
                y -= 100;
            }
            enemy.setLocation(new Location(x,y));
            addEnemy(enemy);

        }
        for (int i=0; i < 5 ; i++){
            double x = Math.random()*PreBossMap.MAP_WIDTH;
            double y = Math.random()*PreBossMap.MAP_HEIGHT;
            EnemyStation enemyStation = new EnemyStation(new Location(x,y));
            PositionHelper helper = new PositionHelper(enemyStation);
            if(helper.getRight() >= PreBossMap.MAP_WIDTH){
                x -= 100;
            }
            if(helper.getLeft() <= 0){
                x += 100;
            }
            if(helper.getTop() <= 0){
                y += 100;
            }
            if(helper.getBottom() >= PreBossMap.MAP_HEIGHT){
                y -= 100;
            }
            enemyStation.setLocation(new Location(x,y));
            addStation(enemyStation);
        }
    }


    public void addEnemy(Enemy enemy) {
        enemies.put(enemy.getID(), enemy);
    }

    public void deleteEnemy(long ID) {
        enemies.remove(ID);
    }

    public void addStation(Station station) {
        stations.put(station.getID(), station);
    }

    public void deleteStation(long ID) {
        stations.remove(ID);
    }

    public void addBullet(Bullet bullet) {
        if(bullet != null)
            bullets.put(bullet.getID(), bullet);
    }

    public void deleteBullet(long ID) {
        bullets.remove(ID);
    }

    public void addBuff(Buff buff) {
        buffs.put(buff.getID(), buff);
    }

    public void deleteBuff(long ID) {
        buffs.remove(ID);
    }

    public void addMeteor(Meteor meteor) {
        meteors.put(meteor.getID(), meteor);
    }

    public void deleteMeteor(long ID) {
        meteors.remove(ID);
    }


    public int getLevel() {
        return level;
    }

    public Map<Long, Enemy> getEnemies() {
        return enemies;
    }

    public Map<Long, Station> getStations() {
        return stations;
    }

    public Map<Long, Bullet> getBullets() {
        return bullets;
    }

    public Map<Long, Buff> getBuffs() {
        return buffs;
    }

    public Map<Long, Meteor> getMeteors() {
        return meteors;
    }

    public boolean isSinglePlayer() {
        return isSinglePlayer;
    }

    public Spacecraft getSpacecraft1() {
        return spacecraft1;
    }

    public Spacecraft getSpacecraft2() {
        return spacecraft2;
    }

    public void setSpacecraft1(Spacecraft spacecraft1) {
        this.spacecraft1 = spacecraft1;
    }

    public void setSpacecraft2(Spacecraft spacecraft2) {
        this.spacecraft2 = spacecraft2;
    }

    public void setSinglePlayer(boolean singlePlayer) {
        isSinglePlayer = singlePlayer;
    }
}
