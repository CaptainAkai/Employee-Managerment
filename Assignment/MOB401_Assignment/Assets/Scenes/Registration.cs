using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.Networking;
// using UnityWebRequest;

public class Registration : MonoBehaviour
{

    public InputField nameField;
    public InputField passwordField;

    public Button submitButton;

    public void CallRegister() {
        StartCoroutine(Register());
    }

    IEnumerator Register() {
        
        UnityWebRequest www = UnityWebRequest.Get("http://localhost/sqlconnect/register.php");
        yield return www.SendWebRequest();
 
        if(www.isNetworkError || www.isHttpError) {
            Debug.Log(www.error);
        }
        else {
            // Show results as text
            Debug.Log(www.downloadHandler.text);
 
            // Or retrieve results as binary data
            byte[] results = www.downloadHandler.data;
        }
    }

    // IEnumerator Register() {

    //     using (UnityWebRequest webRequest = UnityWebRequest.Get(uri))
    //     {
    //         // Request and wait for the desired page.
    //         yield return webRequest.SendWebRequest();

    //         string[] pages = uri.Split('/');
    //         int page = pages.Length - 1;

    //         if (webRequest.isNetworkError)
    //         {
    //             Debug.Log(pages[page] + ": Error: " + webRequest.error);
    //         }
    //         else
    //         {
    //             Debug.Log(pages[page] + ":\nReceived: " + webRequest.downloadHandler.text);
    //         }
    //     }

    //     WWWForm form = new WWWForm();
    //     form.AddField("name", nameField.text);
    //     form.AddField("password", passwordField.text);

    //     string url = "http://localhost/sqlconnect/register.php";

        

    //     using (WWW www = new WWW(url, form)) {

    //         yield return www;
    //         if ( www.text == "0" ) {
    //             Debug.Log("User created successfully!");
    //             UnityEngine.SceneManagement.SceneManager.LoadScene(0);
    //         } else {
    //             Debug.Log("User creation failed. Error # " + www.error + " 0");
    //         }
    //         // if (www.isNetworkError || www.isHttpError)
    //         // {
    //         //     print(www.error);
    //         // }
    //         // else
    //         // {
    //         //     print(www.downloadHandler.text);
    //         // }

    //     }

    //     // WWW www = new WWW("http://localhost/register.php", form);

    //     // yield return www;

    //     // if (www.text == "0")
    //     // {
    //     //     Debug.Log("User created successfully!");
    //     //     UnityEngine.SceneManagement.SceneManager.LoadScene(0);
    //     // }
    //     // else
    //     // {
    //     //     Debug.Log("User creation failed. Error # " + www.text + "0");
    //     // }

    // }

    public void VerifyInputs() {
        submitButton.interactable = (nameField.text.Length >= 4 && passwordField.text.Length >= 4);
    }

}
