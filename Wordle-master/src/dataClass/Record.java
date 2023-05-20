package dataClass;


public class Record {
    private long begin, end;
    private int duration;
    private int rows;
    private String state;
    private boolean stateBool;
    private String content;

    public Record() {}

    /**
     * Setup Record instance
     * @param content The content read from one line ofGameData.data.txt
     */
    public Record(String[] content) {
        int idx = 0;
        setBegin(Long.parseLong(content[idx++].substring("begin: ".length())));
        setEnd(Long.parseLong(content[idx++].substring("end: ".length())));
        String duration = content[idx].substring("duration: ".length(), content[idx].length() - 1);
        setDuration(Integer.parseInt(duration));
        idx++;
        String rows = content[idx++].substring("rows: ".length());
        setRows(Integer.parseInt(rows));
        setState(content[idx++].substring("state: ".length()));
        setStateBool(getState().equals("win"));
    }

    public long getBegin() {
        return begin;
    }

    public void setBegin(long begin) {
        this.begin = begin;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isStateBool() {
        return stateBool;
    }

    public void setStateBool(boolean stateBool) {
        this.stateBool = stateBool;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
