﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ResultHighscore : MonoBehaviour {

	// Use this for initialization
	void Start () {
        GetComponent<GUIText>().text = Score.highScore.ToString();
    }
	
	// Update is called once per frame
	void Update () {
        
    }
}
