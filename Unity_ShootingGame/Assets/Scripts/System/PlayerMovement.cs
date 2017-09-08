using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class PlayerMovement : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        
        if (Input.GetKey(KeyCode.Space)){
            if (SceneManager.GetActiveScene().name == ("Result"))
                SceneManager.LoadScene("Title");
            else
                SceneManager.LoadScene("Game");
        }
	}
}
