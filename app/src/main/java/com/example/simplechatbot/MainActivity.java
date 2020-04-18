package com.example.simplechatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.AIServiceContextBuilder;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIError;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;
import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.AIServiceException;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import ai.api.model.Result;


public class MainActivity extends AppCompatActivity implements AIListener  {

    private AIService aiService;
    private AIDataService aiDataService;
    private  AIRequest aiRequest;
    EditText question;
    TextView ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question  =(EditText)findViewById(R.id.question);
        ans = (TextView)findViewById(R.id.textView);

        final AIConfiguration config = new AIConfiguration("a00aa80a92bb4867adcbb5b071c7ea66",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);
        aiService.setListener(this);
        aiDataService = new AIDataService(config);
        aiRequest = new AIRequest();
        aiService.startListening();


    }

    public void getreply(View view) {

        String message = question.getText().toString();


        if (!message.equals(""))
        {
            aiRequest.setQuery(message);
            new AsyncTask<AIRequest,Void,AIResponse>(){

                @Override
                protected AIResponse doInBackground(AIRequest... aiRequests) {
                    final AIRequest request = aiRequests[0];
                    try {
                        final AIResponse response = aiDataService.request(aiRequest);
                        System.out.println("respnse "+response);
                        return response;
                    } catch (AIServiceException e) {
                    }
                    return null;
                }
                @Override
                protected void onPostExecute(AIResponse response) {
                    if (response != null) {

                        Result result = response.getResult();
                        String reply = result.getFulfillment().getSpeech();
                        System.out.println("reply this "+reply);
                        ans.setText(reply);

                    }
                }
            }.execute(aiRequest);
        }
        else {


        }
    }

    /**
     * Event fires when entire process finished successfully, and returns result object
     *
     * @param result the result object, contains server answer
     */
    @Override
    public void onResult(AIResponse result) {

//        Result results = result.getResult();
//
//        String message = results.getResolvedQuery();
//        System.out.println("message "+message);
//
//        String reply = results.getFulfillment().getSpeech();
//        System.out.println("reply here "+reply);
////        ans.setText(reply);


    }

    /**
     * Event fires if something going wrong while recognition or access to the AI server
     *
     * @param error the error description object
     */
    @Override
    public void onError(AIError error) {

    }

    /**
     * Event fires every time sound level changed. Use it to create visual feedback. There is no guarantee that this method will
     * be called.
     *
     * @param level the new RMS dB value
     */
    @Override
    public void onAudioLevel(float level) {

    }

    /**
     * Event fires when recognition engine start listening
     */
    @Override
    public void onListeningStarted() {

    }

    /**
     * Event fires when recognition engine cancel listening
     */
    @Override
    public void onListeningCanceled() {

    }

    /**
     * Event fires when recognition engine finish listening
     */
    @Override
    public void onListeningFinished() {

    }




//    /**
//     * Event fires when entire process finished successfully, and returns result object
//     *
//     * @param result the result object, contains server answer
//     */
//    @Override
//    public void onResult(AIResponse result) {
//
//        Result r = result.getResult();
//        System.out.println("rrr "+r);
//
//    }
//
//    /**
//     * Event fires if something going wrong while recognition or access to the AI server
//     *
//     * @param error the error description object
//     */
//    @Override
//    public void onError(AIError error) {
//
//    }
//
//    /**
//     * Event fires every time sound level changed. Use it to create visual feedback. There is no guarantee that this method will
//     * be called.
//     *
//     * @param level the new RMS dB value
//     */
//    @Override
//    public void onAudioLevel(float level) {
//
//    }
//
//    /**
//     * Event fires when recognition engine start listening
//     */
//    @Override
//    public void onListeningStarted() {
//
//    }
//
//    /**
//     * Event fires when recognition engine cancel listening
//     */
//    @Override
//    public void onListeningCanceled() {
//
//    }
//
//    /**
//     * Event fires when recognition engine finish listening
//     */
//    @Override
//    public void onListeningFinished() {
//
//    }
}
