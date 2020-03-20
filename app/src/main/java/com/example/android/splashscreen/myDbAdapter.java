//package com.example.android.splashscreen;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;
//
//import java.util.ArrayList;
//
//public class myDbAdapter {
//    myDbHelper myHelper;
//    private static final String TAG = "myDbAdapter";
//    public myDbAdapter(Context context) {
//        myHelper = new myDbHelper(context);
//    }
//
//    public long insertMatchData(String event_id, String team_home_id, String team_home,
//                                String match_time, String team_away_id, String team_away,
//                                Integer home_score, Integer away_score, Boolean past, String date) {
//        Log.d(TAG, "sempak ");
//        SQLiteDatabase dbb = myHelper.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(myDbHelper.EVENT_ID, event_id);
//        contentValues.put(myDbHelper.TEAM_HOME_ID, team_home_id);
//        contentValues.put(myDbHelper.TEAM_HOME, team_home);
//        contentValues.put(myDbHelper.MATCH_TIME, match_time);
//        contentValues.put(myDbHelper.TEAM_AWAY_ID, team_away_id);
//        contentValues.put(myDbHelper.TEAM_AWAY, team_away);
//        contentValues.put(myDbHelper.HOME_SCORE, home_score);
//        contentValues.put(myDbHelper.AWAY_SCORE, away_score);
//        if (past.equals(1)) {
//            contentValues.put(myDbHelper.PAST, new Integer(1));
//        } else {
//            contentValues.put(myDbHelper.PAST, new Integer(0));
//        }
//        contentValues.put(myDbHelper.PAST, past);
//        contentValues.put(myDbHelper.DATE, date);
//        long id = dbb.insert(myDbHelper.TABLE_MATCH, null, contentValues);
//        return id;
//    }
//
////    public long insertTeamData(String team_id, String team_name, String team_logo) {
////        SQLiteDatabase dbb = myHelper.getWritableDatabase();
////        ContentValues contentValues = new ContentValues();
////        contentValues.put(myDbHelper.TEAM_ID, team_id);
////        contentValues.put(myDbHelper.TEAM_NAME, team_name);
////        contentValues.put(myDbHelper.TEAM_LOGO, team_logo);
////        long id = dbb.insert(myDbHelper.TABLE_TEAM, null, contentValues);
////        return id;
////    }
//
//    public ArrayList<MatchData> getMatchData() {
//        SQLiteDatabase db = myHelper.getWritableDatabase();
//        Log.d(TAG, "kuda ");
//        String[] columns = {myDbHelper.ID, myDbHelper.EVENT_ID, myDbHelper.TEAM_HOME_ID, myDbHelper.TEAM_HOME,
//                myDbHelper.MATCH_TIME, myDbHelper.TEAM_AWAY_ID, myDbHelper.TEAM_AWAY, myDbHelper.HOME_SCORE,
//                myDbHelper.AWAY_SCORE, myDbHelper.PAST, myDbHelper.DATE};
//        Log.d(TAG, "y ");
//        Cursor cursor = db.query(myDbHelper.TABLE_MATCH, columns, null, null, null, null, null);
//        Log.d(TAG, "x ");
//        ArrayList<MatchData> data = new ArrayList<MatchData>();
//        Log.d(TAG, "z ");
//
//        while (cursor.moveToNext()) {
//            int id = cursor.getInt(cursor.getColumnIndex(myDbHelper.ID));
//            String event_id = cursor.getString(cursor.getColumnIndex(myDbHelper.EVENT_ID));
//            String team_home_id = cursor.getString(cursor.getColumnIndex(myDbHelper.TEAM_HOME_ID));
//            String team_home = cursor.getString(cursor.getColumnIndex(myDbHelper.TEAM_HOME));
//            String match_time = cursor.getString(cursor.getColumnIndex(myDbHelper.MATCH_TIME));
//            String team_away_id = cursor.getString(cursor.getColumnIndex(myDbHelper.TEAM_AWAY_ID));
//            String team_away = cursor.getString(cursor.getColumnIndex(myDbHelper.TEAM_AWAY));
//            Integer home_score = cursor.getInt(cursor.getColumnIndex(myDbHelper.HOME_SCORE));
//            Integer away_score = cursor.getInt(cursor.getColumnIndex(myDbHelper.AWAY_SCORE));
//            Integer past = cursor.getInt(cursor.getColumnIndex(myDbHelper.PAST));
//            Boolean lalu = true;
//            if (past.equals(1)) {
//                lalu = false;
//            } else {
//                lalu = true;
//            }
//            String date = cursor.getString(cursor.getColumnIndex(myDbHelper.DATE));
//            Model model = new Model("", "", team_home, team_away, match_time);
//
//            MatchData matchData = new MatchData(id, home_score, away_score, team_home_id, team_away_id,
//                    event_id, date, lalu, model);
//            data.add(matchData);
//        }
//        return data;
//    }
//    static class myDbHelper extends SQLiteOpenHelper {
//        private static final String DATABASE_NAME = "BolaSepak";    // Database Name
//        private static final String TABLE_MATCH = "match";   // Table Name
//        private static final int DATABASE_Version = 1;    // Database Version
//        private static final String ID="_id";     // Column I (Primary Key)
//        private static final String EVENT_ID = "EventId";
//        private static final String TEAM_HOME_ID = "TeamHomeId";
//        private static final String TEAM_HOME= "TeamHome";
//        private static final String MATCH_TIME= "MatchTime";
//        private static final String TEAM_AWAY_ID = "TeamAwayId";
//        private static final String TEAM_AWAY= "TeamAway";
//        private static final String HOME_SCORE = "HomeScore";
//        private static final String AWAY_SCORE = "AwayScore";
//        private static final String PAST= "Past";
//        private static final String DATE= "Date";
//        private static final String CREATE_TABLE_MATCH = "CREATE TABLE "+TABLE_MATCH+
//                " ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+EVENT_ID+" VARCHAR(255) ,"+
//                TEAM_HOME_ID+" VARCHAR(255) ,"+ TEAM_HOME+" VARCHAR(255) ,"+MATCH_TIME+" VARCHAR(255) ,"
//                +TEAM_AWAY_ID+" VARCHAR(255) ,"+TEAM_AWAY+" VARCHAR(255) ,"+HOME_SCORE+" INTEGER ,"
//                +MATCH_TIME+" VARCHAR(255) ,"+AWAY_SCORE+" INTEGER ," +PAST+" INTEGER ,"+DATE+" VARCHAR(255));";
//        private static final String DROP_TABLE_MATCH ="DROP TABLE IF EXISTS "+TABLE_MATCH;
//        private Context context;
//
//        public myDbHelper(Context context) {
//            super(context, DATABASE_NAME, null, DATABASE_Version);
//            this.context=context;
//        }
//        public void onCreate(SQLiteDatabase db) {
//
//            try {
//                Log.d(TAG, "Haiii ");
//                db.execSQL(CREATE_TABLE_MATCH);
//                Log.d(TAG, "Hello ");
//            } catch (Exception e) {
//                Log.d(TAG, "Jing ");
//                Message.message(context,""+e);
//            }
//        }
//        @Override
//        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            try {
//                Message.message(context,"OnUpgrade");
//                db.execSQL(DROP_TABLE_MATCH);
//                onCreate(db);
//            }catch (Exception e) {
//                Message.message(context,""+e);
//            }
//        }
//    }
//
//}
