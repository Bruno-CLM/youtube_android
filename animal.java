@Entity(tableName = "animals")
public class Animal {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "animalId")
    private int mId;
    
    
