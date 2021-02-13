package com.first.liptonapp;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.widget.EditText;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MySendSMSservice extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SMS = "com.first.liptonapp.action.FOO";
    private static final String ACTION_WHATSAPP = "com.first.liptonapp.action.BAZ";

    // TODO: Rename parameters
    private static final String MOBILE_NUMBER = "com.first.liptonapp.extra.PARAM2";
    private static final String MESSAGE = "Hello";

    public MySendSMSservice() {
        super("MySendSMSservice");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSMS(Context context, EditText Enter_Mobile_Number, String message) {
        //List<String> numbers = new ArrayList<String>();
        Intent intent = new Intent(context, MySendSMSservice.class);
        intent.setAction(ACTION_SMS);
        intent.putExtra(MOBILE_NUMBER, Enter_Mobile_Number.getText().toString());
        intent.putExtra(MESSAGE, message);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionWHATSAPP(Context context, String Enter_Mobile_Number, String message) {
        Intent intent = new Intent(context, MySendSMSservice.class);
        intent.setAction(ACTION_WHATSAPP);
        intent.putExtra(MOBILE_NUMBER, Enter_Mobile_Number);
        intent.putExtra(MESSAGE, message);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_WHATSAPP.equals(action)) {
                final String Enter_Mobile_Number = intent.getStringExtra(MOBILE_NUMBER);
                final String message = intent.getStringExtra(MESSAGE);
                try {
                    handleActionWHATSAPP(Enter_Mobile_Number, message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionWHATSAPP(String Enter_Mobile_Number, String message /*,String image*/) throws InterruptedException, UnsupportedEncodingException {
        // TODO: Handle action Baz


            PackageManager packageManager = getApplicationContext().getPackageManager();

                    //SmsManager smsManager = SmsManager.getDefault();
                    //smsManager.sendTextMessage(Enter_Mobile_Number, null, message = "Hello", null, null);
                    String number = Enter_Mobile_Number;
                    String url = "https://api.whatsapp.com/send?phone=" + number + "&text=" + URLEncoder.encode(message,"UTF-8");
                    Intent whatsappIntent = new Intent(Intent.ACTION_VIEW);
                    //Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                    whatsappIntent.setPackage("com.whatsapp");
                    whatsappIntent.setData(Uri.parse(url));
                    whatsappIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (whatsappIntent.resolveActivity(packageManager) != null) {
                        getApplicationContext().startActivity(whatsappIntent);
                        Thread.sleep(10000);
                    }


        /*catch(Exception e){
            //sendBroadcastMessage("Result: "+e.toString());
            }*/
        }
        /*private void sendBroadcastMessage(String message){
        Intent localIntent = new Intent("my.own.broadcast");
        localIntent.pytExtra*/
    }







