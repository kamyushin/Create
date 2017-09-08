using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class ShotDirection : MonoBehaviour {
    private GameObject Player;
    // Use this for initialization
    // Update is called once per frame

    private Manager manager;

    void Start()
    {
        Player = GameObject.Find("Player(Clone)");

    }
	void Update () {
        if (Player != null)
        {
            float rotation = GetAim(transform.position, Player.transform.position);
            transform.rotation = Quaternion.Euler(0, 0, -90 + rotation);
        }
        
        

	}
    public float GetAim(Vector2 p1, Vector2 p2)
    {
        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;
        float rad = Mathf.Atan2(dy, dx);
        return rad * Mathf.Rad2Deg;
    }
}
