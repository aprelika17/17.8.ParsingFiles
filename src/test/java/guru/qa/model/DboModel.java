package guru.qa.model;

import com.google.gson.annotations.SerializedName;

public class DboModel {
    public Integer id;
    public String value;
    public Boolean isMarried;
    @SerializedName("data")
    public Data data;

    public static class Data {
        public String surname;
        public String name;
        public String patronymic;
        public String gender;
        public String[] hobby;
    }


}
