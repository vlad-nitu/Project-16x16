package sidescroller;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Options {
	
	private static final String SAVELOCATION = "Assets/options.json";
	
	public static int moveLeftKey;
	public static int moveRightKey;
	public static int jumpKey;
	public static int attackKey;
	public static int dashKey;
	public static int targetFrameRate;

	@SuppressWarnings("unchecked")
	public static void load()
	{
		JSONParser parser = new JSONParser();
		
		try
		{
			Object obj = parser.parse(new FileReader(SAVELOCATION));
			JSONObject jsonObject = (JSONObject) obj;

			moveLeftKey = (int) (long) jsonObject.getOrDefault("moveLeftKey", 37l);
			moveRightKey = (int) (long) jsonObject.getOrDefault("moveRightKey", 39l);
			jumpKey = (int) (long) jsonObject.getOrDefault("jumpKey", 38l);
			attackKey = (int) (long) jsonObject.getOrDefault("attackKey", 40l);
			dashKey = (int) (long) jsonObject.getOrDefault("dashKey", 16l);
			targetFrameRate = (int) (long) jsonObject.getOrDefault("targetFrameRate", 60l);
			
		} catch (FileNotFoundException e) {
			loadDefault();
		} catch (IOException e)
		{
			loadDefault();
		} catch (ParseException e)
		{
			loadDefault();
		}
	}
	
	private static void loadDefault()
	{
		moveLeftKey = 37;     	//A = 65	LeftArrow = 37
		moveRightKey = 39;		//D = 68	RightArrow = 39
		jumpKey = 38;			//W = 87	UpArrow = 38
		attackKey = 40;			//S = 83    DownArrow = 40
		dashKey = 16;			//Shift = 16
		targetFrameRate = 60;
	}
	
	@SuppressWarnings("unchecked")
	public static void save()
	{
		JSONObject save = new JSONObject();
		
		save.put("moveLeftKey", moveLeftKey);
		save.put("moveRightKey", moveRightKey);
		save.put("jumpKey", jumpKey);
		save.put("attackKey", attackKey);
		save.put("dashKey", dashKey);
		save.put("targetFrameRate", targetFrameRate);
		
		try (FileWriter file = new FileWriter(SAVELOCATION))
		{
			file.write(save.toJSONString());
			file.flush();
		} catch (IOException e)
		{
			//e.printStackTrace();
		}
	}
}