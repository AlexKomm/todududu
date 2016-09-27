package tk.alexkomm.todududu.data.local;

class Db {

    private Db() {
    }

    static class Item {
        // Tables info
        static final String TABLE_NAME = "items";

        // Table columns
        static final String COLUMN_ID = "id";
        static final String COLUMN_NOTE = "note";
        static final String COLUMN_DATE = "date";
        static final String COLUMN_COLOR = "color";
        static final String COLUMN_REMINDER = "reminder";

        static final String CREATE_SQL = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOTE + " TEXT NOT NULL, " +
                COLUMN_DATE + " INTEGER, " +
                COLUMN_COLOR + " INTEGER, " +
                COLUMN_REMINDER + " INTEGER NOT NULL DEFAULT 0" +
                " ); ";
    }
}
