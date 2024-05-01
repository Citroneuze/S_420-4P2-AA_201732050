package org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Deplacement;

import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Application_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Interface.StrategieDeplacementEnnemi;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Jeu_spaceInvader;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.Vaiseau.EnnemiSpaceShip;
import org.calma.ui.s_4204p2aa_201732050.ETC_SPACE_INVADER.utils.Direction;
import org.calma.ui.s_4204p2aa_201732050.South_Park.JeuApplication_SouthPark;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DeplacementEnnemi implements StrategieDeplacementEnnemi {

    private int indexCalqueObstacles;
    private long lastMoveTime;
    private int delay = 1000;
    private int tilesSize = 32;
    private Random random = new Random();

    @Override
    public void deplacer(EnnemiSpaceShip ennemi, GameContainer gc) {
        indexCalqueObstacles = Jeu_spaceInvader.getInstance().getIndexCalqueObstacles();
        long currentTime = System.currentTimeMillis();
        if (currentTime - ennemi.getLastMoveTime() >= delay) {
            ennemi.setLastMoveTime(currentTime);
            List<Direction> validMoves = getValidMoves(ennemi);  // Get the list of valid moves
            if (!validMoves.isEmpty()) {
                Direction direction = validMoves.get(random.nextInt(validMoves.size()));  // Choose a random valid direction
                moveShip(ennemi, direction);
            }
        }
    }

    private List<Direction> getValidMoves(EnnemiSpaceShip ennemi) {
        List<Direction> validMoves = new ArrayList<>();
        int x = ennemi.getCaseX();
        int y = ennemi.getCaseY();
        int mapWidth = Jeu_spaceInvader.getInstance().getMap().getWidth();
        int mapHeight = Jeu_spaceInvader.getInstance().getMap().getHeight();

        // Check possible directions for valid movement (excluding UP)
        if (x > 0 && isMoveValid(x - 1, y)) {
            validMoves.add(Direction.LEFT);
        }
        if (x < mapWidth - 1 && isMoveValid(x + 1, y)) {
            validMoves.add(Direction.RIGHT);
        }
        if (y < mapHeight - 1 && isMoveValid(x, y + 1)) {
            validMoves.add(Direction.DOWN);  // Add DOWN once
            validMoves.add(Direction.DOWN);  // Add DOWN again to increase its probability
            validMoves.add(Direction.DOWN);  // Optionally, add it a third time for even higher bias
        }

        return validMoves;
    }


    private boolean isMoveValid(int x, int y) {
        // Check if the tile at (x, y) is empty and not occupied by any ship
        boolean isOccupiedByShip = Jeu_spaceInvader.getInstance().isOccupiedByShip(x, y);
        boolean isTileEmpty = Jeu_spaceInvader.getInstance().isCaseEmpty(x, y);

        // Additionally, check if the tile is not an obstacle
        int tileID = Jeu_spaceInvader.getInstance().getMap().getTileId(x, y, indexCalqueObstacles);
        boolean isNotObstacle = (tileID == 0); // Assuming '0' means no obstacle; adjust based on your tileset configuration

        return isTileEmpty && !isOccupiedByShip && isNotObstacle;
    }

    private void moveShip(EnnemiSpaceShip ennemi, Direction direction) {
        switch (direction) {
            case LEFT:
                ennemi.setCaseX(ennemi.getCaseX() - 1);
                break;
            case RIGHT:
                ennemi.setCaseX(ennemi.getCaseX() + 1);
                break;
            case DOWN:
                ennemi.setCaseY(ennemi.getCaseY() + 1);
                break;
        }
    }

}

