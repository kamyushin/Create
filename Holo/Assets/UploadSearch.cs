using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using System;
using UnityEngine.Networking;

public class UploadSearch : MonoBehaviour {
    HoloLensSnapshotTest data;
    Texture2D image;
    private string uploadUrl = "http://upload.gyazo.com/api/upload";
    [HideInInspector]
    public bool state = false;
    // Use this for initialization

    public GameObject p;

    TextMesh t;

    public GameObject Plane;

    [Serializable]
    class gyazoRes
    {
        public int image_id;
        public string permalink_url;
        public string thumb_url;
        public string url;
        public string type;
    }

    void Start () {
        data = GetComponent<HoloLensSnapshotTest>();
        t = p.GetComponent<TextMesh>();
        
	}
	
	// Update is called once per frame
	void Update () {
		if (data.SAVE_PICTURE && state == false)
        {

            if (data.targetTexture != null)
            {
                Plane.GetComponent<Renderer>().material.mainTexture = data.targetTexture;
                image = data.targetTexture;
                Debug.Log("Start");
                StartCoroutine(UploadAndSearch());
            }


            state = true;
        }
	}

    IEnumerator UploadAndSearch()
    {
        yield return new WaitForEndOfFrame();

        Debug.Log("t");
        string fileName = string.Format(@"Image_{0:yyyy-MM-dd_hh-mm-ss-tt}.png", DateTime.Now);
        byte[] bytes = image.EncodeToPNG(); //Can also encode to jpg, just make sure to change the file extensions down below
        Debug.Log(bytes.Length);
        WWWForm form = new WWWForm();
        form.AddField("access_token", "8ad10a63b2ccbe1f9c9db7362aea6e39b511b37b8b909f682528c3bf6dc13666");
        form.AddBinaryData("imagedata", bytes, fileName, "multipart/form-data");


        WWW w = new WWW(uploadUrl, form);
        

            UnityWebRequest request = UnityWebRequest.Post(uploadUrl, form);

            Debug.Log("send");
            //yield return request.SendWebRequest();
        yield return w;
        yield return new WaitForSeconds(2);

        
            if (!string.IsNullOrEmpty(w.error))
            {
                // error
                Debug.Log(false);
            }
            else
            {
                // success
                Debug.Log("Success");
                Debug.Log(w.isDone);
                Debug.Log(w.url);

            t.text = w.text;
                
                Debug.Log("a");
                var json = JsonUtility.FromJson<gyazoRes>(w.text);
                string googleUrl = "http://www.google.com/searchbyimage?image_url=" + json.url;
                Application.OpenURL(googleUrl);
            }
        
        /*


        if (!string.IsNullOrEmpty(request.error))
        {
            Debug.Log(request.error);

        }
        else
        {
            Debug.Log(true);
        }
        */
    }
    
    
}
