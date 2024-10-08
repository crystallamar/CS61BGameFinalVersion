package core;

import tileengine.TETile;
import tileengine.Tileset;
import utils.FileUtils;
import java.io.*;
import java.util.ArrayList;

public class SavedGame {
    FileUtils fileUtils = new FileUtils();
    public static final String SAVEDGAMEDIRECTORY = (new File(System.getProperty("user.dir")).getPath());

    public void createSavedFile(TETile[][] world, ArrayList<Integer> avatarCoor, long seed,
                                 ArrayList<Integer> oGCoin1, ArrayList<Integer> oGCoin2, ArrayList<Integer> oGCoin3,
                                 int numOGCoinsPickedUp, boolean trial) {
        saveSeed(seed);
        saveAvatarCoor(avatarCoor);
        saveOGCoin1(oGCoin1);
        saveOGCoin2(oGCoin2);
        saveOGCoin3(oGCoin3);
        saveNumOGCoinsPickedUp(numOGCoinsPickedUp);
    }

    public TETile[][] openSavedFile() {
        long seed = readSeed("seed.txt");
        World genWorld = new World(seed);
        TETile[][] world = new TETile[94][55];
        Coins coin = new Coins();

        ArrayList<Integer> avatarCoor = readAvatarCoor("avatarCoor.txt");
        ArrayList<Integer> oGCoin1 = readOGCoin1("OGCoin1.txt");
        ArrayList<Integer> oGCoin2 = readOGCoin2("OGCoin2.txt");
        ArrayList<Integer> oGCoin3 = readOGCoin3("OGCoin3.txt");
        int trialCoinsPickedUp = avatarCoor.get(2);
        int trialBool = avatarCoor.get(3);
        int numOGCoins = avatarCoor.get(4);
        Boolean ifTrial = readIfTrial("ifTrial.txt");



        //Check if OGCoins have been removed
        //Trial Num

        if (!ifTrial) {
            world = genWorld.generateSavedWorld(world, avatarCoor, oGCoin1, oGCoin2, oGCoin3, trialCoinsPickedUp,
                    trialBool, numOGCoins);
            if (numOGCoins != 0) {
                ArrayList<Integer> firstCoinPickedUp = readFirstCoinPickedUp("FirstCoinPickedUp.txt");
                coin.removeCoin(world, firstCoinPickedUp.get(0), firstCoinPickedUp.get(1));
            }
            if (numOGCoins == 2) {
                ArrayList<Integer> secondCoindPickedUp = readSecondCoinPickedUp("secondCoinPickedUp.txt");
                coin.removeCoin(world, secondCoindPickedUp.get(0), secondCoindPickedUp.get(1));
            }
            return world;
        } else if (ifTrial) {
            world = genWorld.generateTrialWorld(world, seed);
            return world;
        }
        return world;
    }

    public void saveSeed(long seed) {
        String seedString = Long.toString(seed);
        FileUtils.writeFile("seed.txt", seedString);
    }

    public long readSeed(String fileName) {
        String stringSeed = FileUtils.readFile(fileName);
        long seed = Long.parseLong(stringSeed);
        return seed;
    }

    public void saveAvatarCoor(ArrayList<Integer> avatarCoor) {
        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        int trialCoinsPickedUp = avatarCoor.get(2);
        int trialBool = avatarCoor.get(3);
        int numOGCoins = avatarCoor.get(4);

        int oGCoin1X = avatarCoor.get(5);
        int oGCoin1Y = avatarCoor.get(6);
        int oGCoin2X = avatarCoor.get(7);
        int oGCoin2Y = avatarCoor.get(8);
        int oGCoin3X = avatarCoor.get(9);
        int oGCoin3Y = avatarCoor.get(10);

        String sXCoor = Integer.toString(xCoor);
        String sYCoor = Integer.toString(yCoor);
        String sTrialCoinsPickedUp = Integer.toString(trialCoinsPickedUp);
        String sTrialBool = Integer.toString(trialBool);
        String sNumOGCoins = Integer.toString(numOGCoins);
        String sOGCOIN1X = Integer.toString(oGCoin1X);
        String sOGCOIN1Y = Integer.toString(oGCoin1Y);
        String sOGCOIN2X = Integer.toString(oGCoin2X);
        String sOGCOIN2Y = Integer.toString(oGCoin2Y);
        String sOGCOIN3X = Integer.toString(oGCoin3X);
        String sOGCOIN3Y = Integer.toString(oGCoin3Y);

        String stringCoor = sXCoor + " " + sYCoor + " " + sTrialCoinsPickedUp + " " + sTrialBool + " "
                + sNumOGCoins + " " + sOGCOIN1X + " " + sOGCOIN1Y + " " + sOGCOIN2X + " " + sOGCOIN2Y
                + " " + sOGCOIN3X + " " + sOGCOIN3Y;

        FileUtils.writeFile("avatarCoor.txt", stringCoor);
    }

    public ArrayList<Integer> readAvatarCoor(String fileName) {
        ArrayList<Integer> avatarCoor = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);

        int trialCoinsPickedUp = Integer.parseInt(stringArray[2]);
        int trialBool = Integer.parseInt(stringArray[3]);
        int numOGCoins = Integer.parseInt(stringArray[4]);

        int oGCoin1X = Integer.parseInt(stringArray[5]);
        int oGCoin1Y = Integer.parseInt(stringArray[6]);
        int oGCoin2X = Integer.parseInt(stringArray[7]);
        int oGCoin2Y = Integer.parseInt(stringArray[8]);
        int oGCoin3X = Integer.parseInt(stringArray[9]);
        int oGCoin3Y = Integer.parseInt(stringArray[10]);

        avatarCoor.add(xCoor);
        avatarCoor.add(yCoor);
        avatarCoor.add(trialCoinsPickedUp);
        avatarCoor.add(trialBool);
        avatarCoor.add(numOGCoins);
        avatarCoor.add(oGCoin1X);
        avatarCoor.add(oGCoin1Y);
        avatarCoor.add(oGCoin2X);
        avatarCoor.add(oGCoin2Y);
        avatarCoor.add(oGCoin3X);
        avatarCoor.add(oGCoin3Y);

        return avatarCoor;
    }

    public void saveOGAvCoor(ArrayList<Integer> avatarCoor) {
        int xCoor = avatarCoor.get(0);
        int yCoor = avatarCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGAvCoor.txt", stringCoor);
    }

    public ArrayList<Integer> readOGAvCoor(String fileName) {
        ArrayList<Integer> oGavatarCoor = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        oGavatarCoor.add(xCoor);
        oGavatarCoor.add(yCoor);
        return oGavatarCoor;
    }

    public void saveOGCoin1(ArrayList<Integer> coin1Coor) {
        int xCoor = coin1Coor.get(0);
        int yCoor = coin1Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin1.txt", stringCoor);
    }

    public ArrayList<Integer> readOGCoin1(String fileName) {
        ArrayList<Integer> oGCoin1 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        oGCoin1.add(xCoor);
        oGCoin1.add(yCoor);
        return oGCoin1;
    }

    public void saveOGCoin2(ArrayList<Integer> coin2Coor) {
        int xCoor = coin2Coor.get(0);
        int yCoor = coin2Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin2.txt", stringCoor);
    }

    public ArrayList<Integer> readOGCoin2(String fileName) {
        ArrayList<Integer> oGCoin2 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        oGCoin2.add(xCoor);
        oGCoin2.add(yCoor);
        return oGCoin2;
    }

    public void saveOGCoin3(ArrayList<Integer> coin3Coor) {
        int xCoor = coin3Coor.get(0);
        int yCoor = coin3Coor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("OGCoin3.txt", stringCoor);
    }

    public ArrayList<Integer> readOGCoin3(String fileName) {
        ArrayList<Integer> oGCoin3 = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        oGCoin3.add(xCoor);
        oGCoin3.add(yCoor);
        return oGCoin3;
    }

    public void saveCoinPickedUpFirst(ArrayList<Integer> coinCoor) {
        int xCoor = coinCoor.get(0);
        int yCoor = coinCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("FirstCoinPickedUp.txt", stringCoor);
    }

    public ArrayList<Integer> readFirstCoinPickedUp(String fileName) {
        ArrayList<Integer> firstCoin = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        firstCoin.add(xCoor);
        firstCoin.add(yCoor);
        return firstCoin;
    }

    public void saveCoinPickedUpSecond(ArrayList<Integer> coinCoor) {
        int xCoor = coinCoor.get(0);
        int yCoor = coinCoor.get(1);
        String stringCoor = Integer.toString(xCoor);
        stringCoor += " ";
        stringCoor += Integer.toString(yCoor);

        FileUtils.writeFile("secondCoinPickedUp.txt", stringCoor);
    }

    public ArrayList<Integer> readSecondCoinPickedUp(String fileName) {
        ArrayList<Integer> secondCoin = new ArrayList<>();
        String stringAvatarCoor = FileUtils.readFile(fileName);
        String[] stringArray = stringAvatarCoor.split(" ");
        int xCoor = Integer.parseInt(stringArray[0]);
        int yCoor = Integer.parseInt(stringArray[1]);
        secondCoin.add(xCoor);
        secondCoin.add(yCoor);
        return secondCoin;
    }

    public void saveNumOGCoinsPickedUp(int num) {
        String stringCoor = Integer.toString(num);
        FileUtils.writeFile("numOGCoinsPickedUp.txt", stringCoor);
    }

    public int readNumOGCoinsPickedUp(String fileName) {
        String stringNum = FileUtils.readFile(fileName);
        int numCoins = Integer.parseInt(stringNum);
        return numCoins;
    }

    public void saveTrialCoinPositionRed(TETile[][] world, ArrayList<Integer> trialCoins) {
        boolean trialRedCoin = false;
        int trialCoin1X = 0;
        int trialCoin1Y = 0;
        while (!trialRedCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLRED
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num1
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(0);
                trialCoin1Y = trialCoins.get(1);
                trialRedCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLRED
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num1
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(2);
                trialCoin1Y = trialCoins.get(3);
                trialRedCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLRED
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num1
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(4);
                trialCoin1Y = trialCoins.get(5);
                trialRedCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLRED
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num1
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(6);
                trialCoin1Y = trialCoins.get(7);
                trialRedCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLRED
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num1
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(8);
                trialCoin1Y = trialCoins.get(9);
                trialRedCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLRED
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num1
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterA) {
                trialCoin1X = trialCoins.get(10);
                trialCoin1Y = trialCoins.get(11);
                trialRedCoin = true;
                String stringTrialCoins = trialCoin1X + " " + trialCoin1Y;
                FileUtils.writeFile("trialCoinsCoorRED.txt", stringTrialCoins);
            }
        }
    }

    public void saveTrialCoinsPositionOrange(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {
        boolean trialOrangeCoin = false;
        int trialCoin2X = 0;
        int trialCoin2Y = 0;
        while (!trialOrangeCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLOrange
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num2
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(0);
                trialCoin2Y = trialCoins.get(1);
                trialOrangeCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLOrange
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num2
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(2);
                trialCoin2Y = trialCoins.get(3);
                trialOrangeCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLOrange
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num2
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(4);
                trialCoin2Y = trialCoins.get(5);
                trialOrangeCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLOrange
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num2
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(6);
                trialCoin2Y = trialCoins.get(7);
                trialOrangeCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLOrange
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num2
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(8);
                trialCoin2Y = trialCoins.get(9);
                trialOrangeCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLOrange
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num2
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterB) {
                trialCoin2X = trialCoins.get(10);
                trialCoin2Y = trialCoins.get(11);
                trialOrangeCoin = true;
            }
        }
        String stringTrialCoins = trialCoin2X + " " + trialCoin2Y;
        FileUtils.writeFile("trialCoinsCoorORANGE.txt", stringTrialCoins);
    }

    public void saveTrialCoinsPositionYellow(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {
        boolean trialYellowCoin = false;
        int trialCoin3X = 0;
        int trialCoin3Y = 0;
        while (!trialYellowCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLYellow
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num3
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(0);
                trialCoin3Y = trialCoins.get(1);
                trialYellowCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLYellow
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num3
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(2);
                trialCoin3Y = trialCoins.get(3);
                trialYellowCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLYellow
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num3
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(4);
                trialCoin3Y = trialCoins.get(5);
                trialYellowCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLYellow
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num3
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(6);
                trialCoin3Y = trialCoins.get(7);
                trialYellowCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLYellow
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num3
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(8);
                trialCoin3Y = trialCoins.get(9);
                trialYellowCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLYellow
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num3
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterC) {
                trialCoin3X = trialCoins.get(10);
                trialCoin3Y = trialCoins.get(11);
                trialYellowCoin = true;
            }
        }
        String stringTrialCoins = trialCoin3X + " " + trialCoin3Y;
        FileUtils.writeFile("trialCoinsCoorYELLOW.txt", stringTrialCoins);
    }
    public void saveTrialCoinsPositionGreen(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {
        boolean trialGreenCoin = false;
        int trialCoin4X = 0;
        int trialCoin4Y = 0;
        while (!trialGreenCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLGreen
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num4
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(0);
                trialCoin4Y = trialCoins.get(1);
                trialGreenCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLGreen
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num4
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(2);
                trialCoin4Y = trialCoins.get(3);
                trialGreenCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLGreen
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num4
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(4);
                trialCoin4Y = trialCoins.get(5);
                trialGreenCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLGreen
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num4
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(6);
                trialCoin4Y = trialCoins.get(7);
                trialGreenCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLGreen
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num4
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(8);
                trialCoin4Y = trialCoins.get(9);
                trialGreenCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLGreen
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num4
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterD) {
                trialCoin4X = trialCoins.get(10);
                trialCoin4Y = trialCoins.get(11);
                trialGreenCoin = true;
            }
        }
        String stringTrialCoins = trialCoin4X + " " + trialCoin4Y;
        FileUtils.writeFile("trialCoinsCoorGREEN.txt", stringTrialCoins);
    }
    public void saveTrialCoinsPositionBlue(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {
        boolean trialBlueCoin = false;
        int trialCoin5X = 0;
        int trialCoin5Y = 0;
        while (!trialBlueCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLBlue
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num5
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(0);
                trialCoin5Y = trialCoins.get(1);
                trialBlueCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLBlue
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num5
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(2);
                trialCoin5Y = trialCoins.get(3);
                trialBlueCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLBlue
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num5
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(4);
                trialCoin5Y = trialCoins.get(5);
                trialBlueCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLBlue
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num5
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(6);
                trialCoin5Y = trialCoins.get(7);
                trialBlueCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLBlue
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num5
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(8);
                trialCoin5Y = trialCoins.get(9);
                trialBlueCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLBlue
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num5
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterE) {
                trialCoin5X = trialCoins.get(10);
                trialCoin5Y = trialCoins.get(11);
                trialBlueCoin = true;
            }
        }

        String stringTrialCoins = trialCoin5X + " " + trialCoin5Y;
        FileUtils.writeFile("trialCoinsCoorBLUE.txt", stringTrialCoins);
    }
    public void saveTrialCoinsPositionViolet(TETile[][] world, ArrayList<Integer> trialCoins, int trialNum) {
        boolean trialVioletCoin = false;
        int trialCoin6X = 0;
        int trialCoin6Y = 0;
        while (!trialVioletCoin) {
            if (world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.CELLViolet
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.num6
                    || world[trialCoins.get(0)][trialCoins.get(1)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(0);
                trialCoin6Y = trialCoins.get(1);
                trialVioletCoin = true;
            } else if (world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.CELLViolet
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.num6
                    || world[trialCoins.get(2)][trialCoins.get(3)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(2);
                trialCoin6Y = trialCoins.get(3);
                trialVioletCoin = true;
            } else if (world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.CELLViolet
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.num6
                    || world[trialCoins.get(4)][trialCoins.get(5)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(4);
                trialCoin6Y = trialCoins.get(5);
                trialVioletCoin = true;
            } else if (world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.CELLViolet
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.num6
                    || world[trialCoins.get(6)][trialCoins.get(7)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(6);
                trialCoin6Y = trialCoins.get(7);
                trialVioletCoin = true;
            } else if (world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.CELLViolet
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.num6
                    || world[trialCoins.get(8)][trialCoins.get(9)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(8);
                trialCoin6Y = trialCoins.get(9);
                trialVioletCoin = true;
            } else if (world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.CELLViolet
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.num6
                    || world[trialCoins.get(10)][trialCoins.get(11)] == Tileset.letterF) {
                trialCoin6X = trialCoins.get(10);
                trialCoin6Y = trialCoins.get(11);
                trialVioletCoin = true;
            }
        }

        String stringTrialCoins = trialCoin6X + " " + trialCoin6Y;
        FileUtils.writeFile("trialCoinsCoorVIOLET.txt", stringTrialCoins);
    }

    public ArrayList<Integer> readTrialCoinsCoor(String fileName) {
        ArrayList<Integer> trialCoins = new ArrayList<>();
        String sTrialCoins = FileUtils.readFile(fileName);
        String[] stringArray = sTrialCoins.split(" ");
        int trialCoin1X = Integer.parseInt(stringArray[0]);
        int trialCoin1Y = Integer.parseInt(stringArray[1]);

        trialCoins.add(trialCoin1X);
        trialCoins.add(trialCoin1Y);
        return trialCoins;
    }

    public void saveTrialCoinsBool() {
        boolean bool1 = readTrialCoin1Bool("trialCoin1Bool.txt");
        boolean bool2 = readTrialCoin1Bool("trialCoin2Bool.txt");
        boolean bool3 = readTrialCoin1Bool("trialCoin3Bool.txt");
        boolean bool4 = readTrialCoin1Bool("trialCoin4Bool.txt");
        boolean bool5 = readTrialCoin1Bool("trialCoin5Bool.txt");
        boolean bool6 = readTrialCoin1Bool("trialCoin6Bool.txt");

        String sBool1 = Boolean.toString(bool1);
        String sBool2 = Boolean.toString(bool2);
        String sBool3 = Boolean.toString(bool3);
        String sBool4 = Boolean.toString(bool4);
        String sBool5 = Boolean.toString(bool5);
        String sBool6 = Boolean.toString(bool6);

        String sTrialCoinsBool = sBool1 + " " + sBool2 + " " + sBool3 + " " + sBool4 + " " + sBool5 + " " + sBool6;
        FileUtils.writeFile("trialCoinsBool.txt", sTrialCoinsBool);
    }

    public void saveTrialCoin1Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin1Bool.txt", s);
    }

    public void saveTrialCoin2Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin2Bool.txt", s);
    }

    public void saveTrialCoin3Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin3Bool.txt", s);
    }

    public void saveTrialCoin4Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin4Bool.txt", s);
    }

    public void saveTrialCoin5Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin5Bool.txt", s);
    }

    public void saveTrialCoin6Bool(Boolean bool) {
        String s = Boolean.toString(bool);
        FileUtils.writeFile("trialCoin6Bool.txt", s);
    }

    public boolean readTrialCoin1Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public boolean readTrialCoin2Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public boolean readTrialCoin3Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public boolean readTrialCoin4Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public boolean readTrialCoin5Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public boolean readTrialCoin6Bool(String fileName) {
        String s = FileUtils.readFile(fileName);
        return Boolean.parseBoolean(s);
    }

    public ArrayList<Boolean> readTrialCoinsBool(String fileName) {
        ArrayList<Boolean> trialCoinsBool = new ArrayList<>();
        String sTrialCoins = FileUtils.readFile(fileName);
        String[] stringArray = sTrialCoins.split(" ");

        boolean trialCoin1 = Boolean.parseBoolean(stringArray[0]);
        boolean trialCoin2 = Boolean.parseBoolean(stringArray[1]);
        boolean trialCoin3 = Boolean.parseBoolean(stringArray[2]);
        boolean trialCoin4 = Boolean.parseBoolean(stringArray[3]);
        boolean trialCoin5 = Boolean.parseBoolean(stringArray[4]);
        boolean trialCoin6 = Boolean.parseBoolean(stringArray[5]);

        trialCoinsBool.add(trialCoin1);
        trialCoinsBool.add(trialCoin2);
        trialCoinsBool.add(trialCoin3);
        trialCoinsBool.add(trialCoin4);
        trialCoinsBool.add(trialCoin5);
        trialCoinsBool.add(trialCoin6);

        return trialCoinsBool;
    }

    public void saveIfTrial(boolean trial) {
        String bool = Boolean.toString(trial);
        FileUtils.writeFile("ifTrial.txt", bool);
    }

    public Boolean readIfTrial(String fileName) {
        String sTrial = FileUtils.readFile(fileName);
        boolean trial = Boolean.parseBoolean(sTrial);
        return trial;
    }

    public void saveIfLoadedGame(Boolean bool) {
        String sBool = Boolean.toString(bool);
        FileUtils.writeFile("ifLoadedGame.txt", sBool);
    }

    public Boolean readIfLoadedGame(String fileName) {
        String sBool = FileUtils.readFile(fileName);
        boolean bool = Boolean.parseBoolean(sBool);
        return bool;
    }

    public void saveAVCoorWorld(int x, int y) {
        String sX = Integer.toString(x);
        String sY = Integer.toString(y);
        String s = sX + " " + sY;
        FileUtils.writeFile("worldAvCoor.txt", s);
    }

    public ArrayList<Integer> readAVCoorWorld() {
        String s = FileUtils.readFile("worldAvCoor.txt");
        String[] sArray = s.split(" ");
        int x = Integer.parseInt(sArray[0]);
        int y = Integer.parseInt(sArray[1]);
        ArrayList<Integer> array = new ArrayList<>();
        array.add(x);
        array.add(y);
        return array;
    }
}
