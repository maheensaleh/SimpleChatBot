package com.example.simplechatbot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AIConfiguration config = new AIConfiguration("a00aa80a92bb4867adcbb5b071c7ea66",
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);

        aiService = AIService.getService(this, config);
        aiService.setListener(this);

        final AIDataService aiDataService = new AIDataService(config);

        final AIRequest aiRequest = new AIRequest();

        aiService.startListening();

        String message = "what do you know about mobile apps?";


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
                        System.out.println("reply "+reply);

                    }
                }
            }.execute(aiRequest);
        }
        else {
//               aiService.startListening();
        }





//        final AIConfiguration config = new AIConfiguration("<Client Access Code>",
//                AIConfiguration.SupportedLanguages.English,
//                AIConfiguration.RecognitionEngine.System);
//        AIDataService aiDataService = new AIDataService(this, config);
//        AIServiceContextBuilder customAIServiceContext = AIServiceContextBuilder.buildFromSessionId();
//        AIRequest aiRequest = new AIRequest();
//        aiRequest.setQuery("hello");
//
//        try {
//            return aiDataService.request(aiRequest, customAIServiceContext);
//        } catch (AIServiceException e) {
//            e.printStackTrace();
//        }
//

//        AIConfiguration config = new AIConfiguration("a00aa80a92bb4867adcbb5b071c7ea66",
//                AIConfiguration.SupportedLanguages.English,
//                AIConfiguration.RecognitionEngine.System);
//
//        AIService aiService = AIService.getService(this, config);
//        aiService.setListener(this);
//
//        try {
//            AIResponse response = aiService.textRequest(new AIRequest("text"));
//            System.out.println("response "+response);
//        } catch (AIServiceException e) {
//            e.printStackTrace();
//        }
//
//        aiService.startListening();




    }

    public void btn(View view) {
    }

    /**
     * Event fires when entire process finished successfully, and returns result object
     *
     * @param result the result object, contains server answer
     */
    @Override
    public void onResult(AIResponse result) {

        Result results = result.getResult();

        String message = results.getResolvedQuery();
        System.out.println("message "+message);

        String reply = results.getFulfillment().getSpeech();
        System.out.println("reply "+reply);


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
