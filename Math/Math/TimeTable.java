import String.Str;

public class TimeTable {
    int maxNumber = 0;
    private int[][] timeTable;

    public TimeTable(int maxNumber) {
        set(maxNumber);
    }

    public int[][] get() {
        return timeTable;
    }

    public void set(int maxNumber) {
        this.maxNumber = maxNumber;
        timeTable = new int[maxNumber][maxNumber];

        for (int i = 0; i < maxNumber; i++) {
            for (int j = 0; j < maxNumber; j++) {
                timeTable[i][j] = (i+1) * (j+1);
            }
        }
    }

    public String[] toStringArray() {
        return toStringArray(6);
    }

    public String[] toStringArray(int paddingWidth) {
        String[] timeTableRow = new String[maxNumber+2];

        timeTableRow[0] = Str.fmt("{0,-3}|", "x");
        for (int i = 1; i <= maxNumber; i++) {
            timeTableRow[0] += Str.fmt("{0," + paddingWidth + "}", i);
        }
        timeTableRow[1] = new String(new char[4+(maxNumber*paddingWidth)]).replace("\0", "-");

        for (int i = 0; i < maxNumber; i++) {
            timeTableRow[i+2] = Str.fmt("{0,-3}|", i+1);
            for (int j = 0; j < maxNumber; j++) {
                timeTableRow[i+2] += Str.fmt("{0," + paddingWidth + "}", timeTable[i][j]);
            }
        }

        return timeTableRow;
    }

    public void print() {
        String[] timeTableRow = toStringArray();

        for (int i = 0; i < timeTableRow.length; i++) {
            Str.print(timeTableRow[i]);
        }
    }
}
