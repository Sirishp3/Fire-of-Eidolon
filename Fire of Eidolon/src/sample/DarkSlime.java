package sample;

public class DarkSlime implements ChamberTile{
    int row = -1;
    int column = -1;

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public void setRow(int r) {
        row = r;
    }

    @Override
    public void setColumn(int c) {
        column = c;
    }
}
