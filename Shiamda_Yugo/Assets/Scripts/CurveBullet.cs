using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CurveBullet : MonoBehaviour {

    public int speed = 10;

    private float alpha = 1.2f;

    public float lifetime = 5;
    // Use this for initialization

    public int power = 1;

    private GameObject Player;

    private Rigidbody2D r;

    private Vector2 startV;

    private bool zero = false;
    void Start()
    {
        r = GetComponent<Rigidbody2D>();
        Player = GameObject.Find("Player(Clone)");
        r.velocity = transform.up.normalized * speed;
        startV = r.velocity;
        Destroy(gameObject, lifetime);
    }

    // Update is called once per frame
    void Update()
    {
        if (Player != null)
        {
            if (zero == false)
                r.AddForce(-transform.up.normalized * alpha);
            if (Mathf.Abs(r.velocity.x) <= Mathf.Abs(startV.x * 0.1f) && Mathf.Abs(r.velocity.y) <= Mathf.Abs(startV.y * 0.1f))
            {
                zero = true;
                float rotation = GetAim(transform.position, Player.transform.position);
                transform.rotation = Quaternion.Euler(0, 0, -90 + rotation);

                r.velocity = transform.up.normalized * speed;
            }
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
