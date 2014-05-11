package emse.softwaredesign.sokoban.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Marek Lewandowski <marek.lewandowski@icompass.pl>
 * @since 05/05/14
 */
public class LevelParser {

    public static Level parseLevel (String level) {
        Position playerPosition = null;
        Map<Position, Block> blocks = new HashMap<>();

        final String[] split = level.replace(" ", "").split("\n");

        int rows = 0;
        for (String line : split) {
            int col = 0;
            final char[] chars = line.toCharArray();
            for (Character c : chars) {
                Position position = new Position(rows, col);
                if (c.equals('P')) {
                    // player
                    playerPosition = position;
                    final Floor floor = new Floor(position, false);
                    blocks.put(position, floor);
                } else if (c.equals('B')) {
                    // box
                    final Floor floor = new Floor(position, false);
                    floor.addBox();
                    blocks.put(position, floor);
                } else if (c.equals('L')) {
                    // box slot
                    final Floor floor = new Floor(position, true);
                    blocks.put(position, floor);

                } else if (c.equals('f')) {
                    // floor
                    final Floor floor = new Floor(position, false);
                    blocks.put(position, floor);

                } else if (c.equals('W')) {
                    // wall
                    blocks.put(position, new Wall(position));
                } else if (c.equals('C')) {
                    // box on the slot
                    final Floor floor = new Floor(position, true);
                    floor.addBox();
                    blocks.put(position, floor);
                } else if (c.equals('-')) {
                    // skip it
                } else {
                    throw new IllegalStateException("Unexpected character '"+c+"'");
                }
                col = col + 1;
            }
            rows = rows + 1;
        }
        return new Level(playerPosition, blocks);
    }
}
