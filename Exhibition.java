package com.example.agendiario;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = “exhibitions”)

public class Exhibition {
@PrimaryKey(autogenerate = true)
@NonNull
@ColumnInfo(name = “exhibitionId”)
private int mId;

private String mCreationDate;

@Nullable
@ColumnInfo(name = “exhibitionName”)
private String mName;

private String mLocation;

private Int mRanking;

public Exhibition(@Nullable String name, String CreationDate, String Location, int Ranking ){
mName = name;
mCreationDate = CreationDate;
mLocation = Location;
mRanking = Ranking;
}

public int getId() {
return mId;
}

public void setId(int id)  {
mId = id;
}

public string getName() {
return mName;
}

public void setName(String name){
mName=name;
}

public String getLocation(){
return mLocation;
}

public void setLocation(String location){
mLocation = location;
}

public int getRanking(){
return mRanking;
}

public void setRanking(int ranking){
mRanking = ranking;
}

 }
