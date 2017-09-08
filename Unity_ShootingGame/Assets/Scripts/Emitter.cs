using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class Emitter : MonoBehaviour {


    public GameObject[] waves;

    private int currentWave;

    private Manager manager;

	// Use this for initialization
	IEnumerator Start () {
        if (waves.Length == 0)
        {
            yield break;
        }

        manager = FindObjectOfType<Manager>();

        while (true)
        {

            while(manager.IsPlaying() == false)
            {
                yield return new WaitForEndOfFrame();
            }
            
            GameObject g = (GameObject)Instantiate(waves[currentWave], transform.position, Quaternion.identity);
            

            g.transform.parent = transform;

            while(g.transform.childCount != 0)
            {
                yield return new WaitForEndOfFrame();
            }

            Destroy(g);

            if (manager.IsPlaying() == false)
                reset();

            if (waves.Length <= ++currentWave)
            {
                GameObject.Find("ScoreGUI").GetComponent<Score>().Save();
                SceneManager.LoadScene("Result");
                break;
            }
        }
		
	}
	

    public void reset()
    {
        currentWave -= 1;
    }
    public void Init()
    {
        currentWave = 0;
    }

    
	// Update is called once per frame
	void Update () {
		
	}
}
