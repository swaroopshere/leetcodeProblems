package org.home.sshere.leetcode;

public class RobotDirections {
    public static boolean isRobotBounded(String instructions) {
        if(instructions == null)
            return true;
        if(instructions.length() == 0)
            return true;
        char originalDirection = 'N';
        char currentDirection = originalDirection;
        int x = 0;
        int y = 0;
        for(int i=0; i<instructions.length();i++) {
            char currentInstruction = instructions.charAt(i);
            if(currentDirection == 'N') {
                switch(currentInstruction){
                    case 'G':
                        y++;
                        break;
                    case 'L':
                        currentDirection = 'W';
                        break;
                    case 'R':
                        currentDirection = 'E';
                        break;
                    default:
                        throw new IllegalStateException("Incorrect instruction");
                }
            } else if(currentDirection == 'E') {
                switch(currentInstruction) {
                    case 'G':
                        x++;
                        break;
                    case 'L':
                        currentDirection = 'N';
                        break;
                    case 'R':
                        currentDirection = 'S';
                        break;
                    default:
                        throw new IllegalStateException("Incorrect instruction");
                }
            } else if(currentDirection == 'S') {
                switch(currentInstruction) {
                    case 'G':
                        y--;
                        break;
                    case 'L':
                        currentDirection = 'E';
                        break;
                    case 'R':
                        currentDirection = 'W';
                        break;
                    default:
                        throw new IllegalStateException("Incorrect instruction");
                }
            } else if(currentDirection == 'W') {
                switch(currentInstruction) {
                    case 'G':
                        x--;
                        break;
                    case 'L':
                        currentDirection = 'S';
                        break;
                    case 'R':
                        currentDirection = 'N';
                        break;
                    default:
                        throw new IllegalStateException("Incorrect instruction");
                }
            }
        }
        if(x == 0 && y == 0)
            return true;
        if(currentDirection != originalDirection)
            return true;
        else
            return false;
    }
}
