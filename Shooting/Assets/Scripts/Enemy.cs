using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour {

    public int hp = 1;

    public int point = 100;
    Spaceship spaceship;

    private bool InScreen = false;
    private float delta = 0.5f; //うちはじめマージン

	// Use this for initialization
	IEnumerator Start()
    {
        Vector2 max = Camera.main.ViewportToWorldPoint(new Vector2(1, 1));
        Vector2 min = Camera.main.ViewportToWorldPoint(new Vector2(0, 0));
        spaceship = GetComponent<Spaceship>();

        Move(transform.up * -1);
        if (spaceship.canShot == false)
        {
            yield break;

        }
        while (true)
        {
            for (int i = 0; i < transform.childCount; i++)
            {
                Vector3 position = transform.position;

                if (InScreen == false) { 
                    if (position.x < (max.x + delta)&& position.y < (max.y + delta) && position.y >( min.y -delta) && position.x > (min.x - delta))
                    {
                        InScreen = true;
                    }
                }else{ 
                    Transform shotPosition = transform.GetChild(i);
                    spaceship.Shot(shotPosition);
                }
            }
            
            yield return new WaitForSeconds(spaceship.shotDelay);
        }
        
	}
    

    

    public void Move(Vector2 direction)
    {
        GetComponent<Rigidbody2D>().velocity = direction * spaceship.speed;
    }
	
    
    
    void OnTriggerEnter2D(Collider2D c)
    {
        string layerName = LayerMask.LayerToName(c.gameObject.layer);

        if (layerName != ("Bullet(Player)"))return;

        Transform playerBulletTransform = c.transform.parent;
        Bullet bullet = playerBulletTransform.GetComponent<Bullet>();
        hp = hp - bullet.power;

        Destroy(c.gameObject);
        if (hp <= 0)
        {
            FindObjectOfType<Score>().AddPoint(point);
            spaceship.Explosion();
            Destroy(gameObject);
        }else
        {
            spaceship.GetAnimator().SetTrigger("Damage");
        }
    }
    
	
}
