using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Bullet : MonoBehaviour {

    public int speed = 10;

    public float lifetime = 5;
    // Use this for initialization

    public int power = 1;
	void Start () {
        GetComponent<Rigidbody2D>().velocity = transform.up.normalized * speed;
        Destroy(gameObject, lifetime);
    }
	
	// Update is called once per frame
	void Update () {
        
		
	}
}
