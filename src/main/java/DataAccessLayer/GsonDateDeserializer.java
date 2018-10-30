package DataAccessLayer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is responsible for deserializing dates in JSON string.
 * GSON does not allow to parse same date types, then
 * we wrote our own deserializer.
 */
public class GsonDateDeserializer implements JsonDeserializer<Date> {

    private SimpleDateFormat format1 = new SimpleDateFormat("MMMdd,yyyyHH:mm:ssa");
    private SimpleDateFormat format2 = new SimpleDateFormat("HH:mm:ss");

    /**
     * Main function to deserialize date strings
     * @param json element where Date was stored
     * @param typeOfT JSON type
     * @param context deserialization context
     * @return date object
     * @throws JsonParseException parse exception when invalid syntax
     */
    @Override
    public Date deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        try {
            String j = json.getAsJsonPrimitive().getAsString();
            return parseDate(j);
        } catch (ParseException e) {
            throw new JsonParseException(e.getMessage(), e);
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        if (dateString != null && dateString.trim().length() > 0) {
            try {
                return format1.parse(dateString);
            } catch (ParseException pe) {
                return format2.parse(dateString);
            }
        } else {
            return null;
        }
    }
}
