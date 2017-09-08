using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResultScore : MonoBehaviour {
    public GUIText resultScore;
	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        
	}
    public void Score()
    {
        GetComponent<GUIText>().text = resultScore.text;

    }
}
