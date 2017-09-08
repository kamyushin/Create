using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Manager : MonoBehaviour {

    public GameObject player;

    public GameObject icon;


    public int PlayerCount;
    private int StartCount; 

    private GameObject title;
    private GameObject result;
    private GameObject score;

    private GameObject emitter;

    private bool gameOver = false;


	// Use this for initialization
	void Start () {
        title = GameObject.Find("Title");
        result = GameObject.Find("Result");
        score = result.transform.GetChild(1).gameObject;
        result.SetActive(false);
        StartCount = PlayerCount;
        emitter = GameObject.Find("Emitter");
	}
	
	// Update is called once per frame
	void Update () {
        
        if (gameOver == true && Input.GetKeyDown(KeyCode.Space)) { 
            GameReset();
        }


        if (IsPlaying() == false && Input.GetKeyDown(KeyCode.X) && gameOver == false){
            GameStart();
        }

    }

    public void GameStart()
    {
        
        title.SetActive(false);
        Instantiate(player, player.transform.position, player.transform.rotation);

        foreach (Transform n in GameObject.Find("PlayerCount").transform)
        {
            Destroy(n.gameObject);
        }
        for (int i = 0; i < PlayerCount-1; i++)
        {
            GameObject g =  Instantiate(icon, icon.transform.position - new Vector3(i * 0.3f, 0, 0), icon.transform.rotation);
            g.transform.parent = GameObject.Find("PlayerCount").transform;
        }

        Initialize();


    }

    public void GameOver()
    {
        PlayerCount--;
        if(PlayerCount == 0)
        {
            score.GetComponent<ResultScore>().Score();
            gameOver = true;
            FindObjectOfType<Score>().Save();
            result.SetActive(true);
        }


        if (gameOver == false)
        {
            FindObjectOfType<Score>().ScoreDown();
            title.SetActive(true);
        }
    }


    public void GameReset()
    {

        Initialize();
        emitter.GetComponent<Emitter>().Init();
        result.SetActive(false);
        gameOver = false;
        title.SetActive(true);
        PlayerCount = StartCount;

    }

    public void Initialize()
    {
        if (GameObject.Find("Bullet").transform.childCount != 0)
        {
            foreach (Transform n in GameObject.Find("Bullet").transform)
            {
                Destroy(n.gameObject);
            }
        }
        if (GameObject.Find("Emitter").transform.childCount != 0)
        {
            emitter.GetComponent<Emitter>().reset();
            foreach (Transform n in GameObject.Find("Emitter").transform.GetChild(0).transform)
            {
                Destroy(n.gameObject);
            }
        }
    }

    public bool IsPlaying()
    {
        if (gameOver == false)
            return title.activeSelf == false;
        else
            return false;
    }
}
