package sweeper;

class Flag {
    private Matrix flagMap;
    private int countOfCloseBoxes;

    void start() {
        flagMap = new Matrix(Box.CLOSED);
        countOfCloseBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box get(Coord coord) {
        return flagMap.get(coord);
    }

    public void setOpenedToBox(Coord coord) {
        flagMap.set(coord, Box.OPENED);
        countOfCloseBoxes--;
    }

    public void setFlagedToBox(Coord coord) {
        flagMap.set(coord, Box.FLAGED);
    }

    public void toggleFlagedToBox(Coord coord) {
        switch (flagMap.get(coord)) {
            case FLAGED:
                setClosedToBox(coord);
                break;
            case CLOSED:
                setFlagedToBox(coord);
                break;
        }
    }

    private void setClosedToBox(Coord coord) {
        flagMap.set(coord, Box.CLOSED);
    }

    public int getCountOfClosedBoxes() {
        return countOfCloseBoxes;
    }

    public void setBombedToBox(Coord coord) {
        flagMap.set(coord, Box.BOMBED);
    }

    void setOpenedToCloseBombBox(Coord coord) {
        if (flagMap.get(coord) == Box.CLOSED)
            flagMap.set(coord, Box.OPENED);
    }

    void setNobombToCFlagedSafeBox(Coord coord) {
        if (flagMap.get(coord) == Box.FLAGED)
            flagMap.set(coord, Box.NOBOMB);
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord))
            if (flagMap.get(around) == Box.FLAGED)
                count++;
        return count;
    }
}