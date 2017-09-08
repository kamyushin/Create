using System.Collections;
using System.Collections.Generic;
using UnityEngine;


[RequireComponent(typeof(Rigidbody2D),typeof(Animator))]
public class Spaceship : MonoBehaviour {


    public float speed;

    public float shotDelay;

    public GameObject[] Bullets;

    

    public GameObject explosion;

    public bool canShot;

    private Animator animator;

    void Start()
    {
        animator = GetComponent<Animator>();
    }

    public void Explosion()
    {
        Instantiate(explosion, transform.position, transform.rotation);
    }
    public void Shot(Transform origin)
    {
        GameObject g;
        if (origin.gameObject.name == "Player(Clone)")
        {
            g = Instantiate(Bullets[0], origin.position, origin.rotation);
            g.transform.parent = GameObject.Find("Bullet").transform;
            return;
        }
        

        string[] s= origin.gameObject.name.Split('_');
        int number = int.Parse(s[1]);

        g = Instantiate(Bullets[number], origin.position, origin.rotation);

        g.transform.parent = GameObject.Find("Bullet").transform;
    }
   
    public Animator GetAnimator()
    {
        return animator;
    }
	
	// Update is called once per frame
	void Update () {
		
	}
}
