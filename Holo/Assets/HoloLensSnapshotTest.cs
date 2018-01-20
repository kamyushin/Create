using UnityEngine;
using System.Collections;
using System.Collections.Generic;
using UnityEngine.XR.WSA.WebCam;
using UnityEngine.XR.WSA.Input;
using UnityEngine.UI;
using System.Linq;

public class HoloLensSnapshotTest : MonoBehaviour
{
    GestureRecognizer m_GestureRecognizer;
    GameObject m_Canvas = null;
    Renderer m_CanvasRenderer = null;
    PhotoCapture m_PhotoCaptureObj;
    CameraParameters m_CameraParameters;
    bool m_CapturingPhoto = false;
    Texture2D m_Texture = null;
    public GameObject g;
    public GameObject p;
    TextMesh t;
    [HideInInspector]
    public string filePath;
    [HideInInspector]
    public bool SAVE_PICTURE = false;
    [HideInInspector]
    public Texture2D targetTexture;

    
    void Start()
    {
        t = p.GetComponent<TextMesh>();
        Initialize();
    }

    void SetupGestureRecognizer()
    {
        m_GestureRecognizer = new GestureRecognizer();
        m_GestureRecognizer.SetRecognizableGestures(GestureSettings.Tap);
        m_GestureRecognizer.TappedEvent += OnTappedEvent;
        m_GestureRecognizer.StartCapturingGestures();

        m_CapturingPhoto = false;
    }

    void Initialize()
    {
        Debug.Log("Initializing...");
        t.text = "Initializing...";
        p.GetComponent<TextMesh>().text = "ini,,,";
        List<Resolution> resolutions = new List<Resolution>(PhotoCapture.SupportedResolutions);
        Debug.Log(resolutions.Count);
        Resolution selectedResolution = resolutions[0];

        m_CameraParameters = new CameraParameters(WebCamMode.PhotoMode);
        m_CameraParameters.cameraResolutionWidth = selectedResolution.width;
        m_CameraParameters.cameraResolutionHeight = selectedResolution.height;
        m_CameraParameters.hologramOpacity = 0.0f;
        m_CameraParameters.pixelFormat = CapturePixelFormat.BGRA32;

        m_Texture = new Texture2D(selectedResolution.width, selectedResolution.height, TextureFormat.BGRA32, false);

        PhotoCapture.CreateAsync(false, OnCreatedPhotoCaptureObject);
    }

    void OnCreatedPhotoCaptureObject(PhotoCapture captureObject)
    {
        m_PhotoCaptureObj = captureObject;
        m_PhotoCaptureObj.StartPhotoModeAsync(m_CameraParameters, OnStartPhotoMode);
    }

    void OnStartPhotoMode(PhotoCapture.PhotoCaptureResult result)
    {
        SetupGestureRecognizer();

        Debug.Log("Ready!");
        t.GetComponent<TextMesh>().text = "Ready!";
        Debug.Log("Air Tap to take a picture.");
        t.text = "Air Tap to take a picture.";
    }

    void OnStoppedPhotoMode(PhotoCapture.PhotoCaptureResult result)
    {
        m_PhotoCaptureObj.Dispose();
        m_PhotoCaptureObj = null;
    }

    void OnTappedEvent(InteractionSourceKind source, int tapCount, Ray headRay)
    {
        if (m_CapturingPhoto)
        {
            return;
        }
        string filename = string.Format(@"CapturedImage{0}_n.jpg", Time.time);
        filePath = System.IO.Path.Combine(Application.persistentDataPath, filename);

        m_CapturingPhoto = true;
        Debug.Log("Taking picture...");
        t.text = "Taking picture...";
        m_PhotoCaptureObj.TakePhotoAsync(onCapturedPhotoToMemory);
    }

    //保存
    /*
    void OnCapturedPhotoToDisk(PhotoCapture.PhotoCaptureResult result)
    {
        if (result.success)
        {
            SAVE_PICTURE = true;
            t.text = "Save Photo to disk";
            t.text = filePath;
            m_PhotoCaptureObj.StopPhotoModeAsync(OnStoppedPhotoMode);
        }
        
    }
    */

    //Texture2Dへ変換
    void onCapturedPhotoToMemory(PhotoCapture.PhotoCaptureResult result,PhotoCaptureFrame photoCaptureFrame)
    {
        if (result.success)
        {
            t.text = "Save Photo to disk";

            List<Resolution> resolutions = new List<Resolution>(PhotoCapture.SupportedResolutions);
            Resolution cameraResolution = resolutions[0];
            
            targetTexture = new Texture2D(cameraResolution.width, cameraResolution.height);
            photoCaptureFrame.UploadImageDataToTexture(targetTexture);

            t.text = "Change";
            Debug.Log("Change");
            
            SAVE_PICTURE = true;


        }

        m_PhotoCaptureObj.StopPhotoModeAsync(OnStoppedPhotoMode);
    }
}
