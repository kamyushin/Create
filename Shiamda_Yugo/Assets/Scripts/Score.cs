using UnityEngine;

public class Score : MonoBehaviour
{
    public GUIText scoreGUIText;
    
    public GUIText highScoreGUIText;
    
    private int score;
    
    public static int highScore;
    
    private string highScoreKey = "highScore";

    private static int game_count = 0;

    void Start()
    {
        if (game_count == 0)
        {
            PlayerPrefs.DeleteAll();
            game_count++;
        }
        
        Initialize();
    }

    void Update()
    {
        if (highScore < score)
        {
            highScore = score;
        }

        scoreGUIText.text = score.ToString();
        highScoreGUIText.text = "HighScore : " + highScore.ToString();
    }
    

    private void Initialize()
    {
        score = 0;
        

        highScore = PlayerPrefs.GetInt(highScoreKey, 0);
    }
    
    public void AddPoint(int point)
    {
        score = score + point;
    }
    
    public void Save()
    {
        PlayerPrefs.SetInt(highScoreKey, highScore);
        PlayerPrefs.Save();
        
        Initialize();
    }

    public void ScoreDown()
    {
        score -= 500;
        if (score <= 0)
            score = 0;
    }
}