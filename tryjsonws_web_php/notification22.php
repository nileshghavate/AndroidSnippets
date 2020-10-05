
<!--
Notification Category

1. New Batches (CategoryId, Title, Message , Image)
2. notes uploaded (CategoryId, Title, Message , URL , Image)
3. Video uploaded (CategoryId, Title, Message , URL , Image)
4. Generic (CategoryId, Title, Message , URL , Image)
5. Mock test (CategoryId, Title, Message , Image)


-->

<?php include 'header.php';?>

<?php   //require "connect.php"; ?>
<html>
    <head>
        <title>Notification</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="images/logo/logo_small.ico">
        <link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.6.0/pure-min.css">

        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

        <script src="bootbox.min.js"></script>


        <style type="text/css">
            body{
            }
            div.container{
                width: 1000px;
                margin: 0 auto;
                position: relative;
            }
            legend{
                font-size: 30px;
                color: #555;
            }
            .btn_send{
                background: #00bcd4;
            }
            label{
                margin:10px 0px !important;
            }
            textarea{
                resize: none !important;
            }
            .fl_window{
                width: 400px;
                position: absolute;
                right: 0;
                top:100px;
            }
            pre, code {
                padding:10px 0px;
                box-sizing:border-box;
                -moz-box-sizing:border-box;
                webkit-box-sizing:border-box;
                display:block;
                white-space: pre-wrap;
                white-space: -moz-pre-wrap;
                white-space: -pre-wrap;
                white-space: -o-pre-wrap;
                word-wrap: break-word;
                width:100%; overflow-x:auto;
            }

        </style>
    </head>
    <body>


<div class="container-fluid col-md-12" >
<div class="row">

  <?php include 'sidebar.php';?>
  <?php // require 'connect.php';?>



<div class="col-md-10">
<div class="col-md-12">
<div class="panel panel-default ">
  <div class="panel-heading">  <strong> <p class="text-center ">  Send to Single Device </p> </strong></div>

  <?php

  if(isset($_POST['submit']))
  {
  // notification title
  $title = isset($_POST['title']) ? $_POST['title'] : '';
  $regId = isset($_POST['firebase_reg_id']) ? $_POST['firebase_reg_id'] : '';

  // notification message
  $message = isset($_POST['message']) ? $_POST['message'] : '';

  

  $token = $regId;

  // $msg = array
  // (
  //   'message'   => 'This is a message. ',
  //   'title'   => 'This is a title.',
  //   'subtitle'  => 'This is a subtitle. subtitle',
  //   'tickerText'  => 'Ticker text here...Ticker text here...',
  //   'vibrate' => 1,
  //   'largeIcon' => 'large_icon',
  //   'smallIcon' => 'small_icon'
  // );

  $n = array(
    "body"  => $message,
    "title" => $title,
      "text"  => "Click me to open an Activity!",
      "sound" => "warning"
  );


  // echo "<pre>";
  // echo "token: ".$token;
  // print_r($n);
  // echo "</pre>";
  $response = send_notification($token, $msg, $n);
  // echo $response;

   
  }

  function send_notification ($token, $message = "", $n)
  {
    $url = 'https://fcm.googleapis.com/fcm/send';
   

    $fields = array(
       'to'         => $token,
       'registration_id' => $token,
       'priority'   => "high",
       'notification' => $n,
       'data'     => $message
    );

    //var_dump($fields);

    $headers = array(
      'Authorization:key = AAAAw5bNdnM:APA91bFAd2xiGYXz2MIS3gdHthgrVNBK4HvJ-gCkRJMuh2kWVQ362O1Y2t0HvHje_ap_5yGlpZOS_RxR5VEsFAvQ4hJOWvTkkGGunjoyX1HHdnJTa22IZ6fssGZ6hrbx-0mj5cGfOawY',
      'Content-Type: application/json'
      );

     $ch = curl_init();
       curl_setopt($ch, CURLOPT_URL, $url);
       curl_setopt($ch, CURLOPT_POST, true);
       curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
       curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
       curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);  
       curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
       curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
       $result = curl_exec($ch);           
       if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
       }
       curl_close($ch);
       return $result;
  }



?>









    <div class="container">
        <div class="fl_window">
            <!-- <div><img src="http://api.androidhive.info/images/firebase_logo.png" width="200" alt="Firebase"/></div> -->
            <div>Output :</div>
            <br/>
            <?php if ($json != '') { ?>
                <label><b>Request:</b></label>
                <div class="json_preview">
                    <pre><?php echo json_encode($json) ?></pre>
                </div>
            <?php } ?>
            <br/>
            <?php if ($response != '') { ?>
                <label><b>Response:</b></label>
                <div class="json_preview">
                    <pre><?php echo json_encode($response) ?></pre>
                </div>
            <?php } ?>

        </div>

        <form class="pure-form pure-form-stacked" method="post"  enctype="multipart/form-data">
            <fieldset>

                <label for="title">Firebase Reg Id</label>
                <input type="text" id="firebase_reg_id" name="firebase_reg_id" class="pure-input-1-2" placeholder="Enter firebase_reg_id">


                <label for="title">Title</label>
                <input type="text" id="title" name="title" class="pure-input-1-2" placeholder="Enter title">

                <label for="message">Message</label>
                <textarea class="pure-input-1-2" rows="5" name="message" id="message" placeholder="Notification message!"></textarea>

     <!--            <label for="url">URL (If any)</label>
                <input type="text" id="url" name="url" class="pure-input-1-2" placeholder="Enter url">


                <label for="notification_image"> Upload Image (If any)</label>
                <input type="file" name="notification_image" >  
 -->
                <!-- <input name="include_image" id="include_image" type="checkbox"> Include image -->
                <button type="submit" name="submit" class="pure-button pure-button-primary btn_send">Send</button>
            </fieldset>
        </form>



    </div>

  </div>
</div>
</div>
      </div>
  </div>



    </body>
</html>

<script src="https://www.gstatic.com/firebasejs/5.0.2/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCCIXCVtAV5TgyXPJfV5jvEuqe9pfGtL-4",
    authDomain: "tryfirebase2-dea74.firebaseapp.com",
    databaseURL: "https://tryfirebase2-dea74.firebaseio.com",
    projectId: "tryfirebase2-dea74",
    storageBucket: "",
    messagingSenderId: "840048670323"
  };
  firebase.initializeApp(config);
</script>
