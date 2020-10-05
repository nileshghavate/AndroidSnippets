
<!--
Notification Category

1. New Batches (CategoryId, Title, Message , Image)
2. notes uploaded (CategoryId, Title, Message , URL , Image)
3. Video uploaded (CategoryId, Title, Message , URL , Image)
4. Generic (CategoryId, Title, Message , URL , Image)
5. Mock test (CategoryId, Title, Message , Image)


-->

<?php include 'header.php';?>

<?php   require "connect.php"; ?>
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

    			<?php include 'notices.php';?>
    			<?php include 'sidebar.php';?>
    			<?php include 'coursedetails.php'; ?>
    			<?php require 'connect.php';?>



    			<div class="col-md-10">
    				<div class="col-md-12">
    					<div class="panel panel-default ">
    						<div class="panel-heading">  <strong> <p class="text-center ">  Send to Single Device </p> </strong></div>

                <?php

                if(isset($_POST['submit']))
                {
                // notification title
                $title = isset($_POST['title']) ? $_POST['title'] : '';
                $regId = isset($_POST['regId']) ? $_POST['regId'] : '';

                // notification message
                $message = isset($_POST['message']) ? $_POST['message'] : '';

                $category = isset($_POST['category']) ? $_POST['category'] : '';
                $url = isset($_POST['url']) ? $_POST['url'] : '';

                // push type - single user / topic
                $push_type = isset($_POST['push_type']) ? $_POST['push_type'] : '';

                // whether to include to image or not
              //      $include_image = isset($_POST['include_image']) ? TRUE : FALSE;

              // echo "<br>".$title;
              // echo "<br>".$message;
              // echo "<br>".$category;
              // echo "<br>".$url;
              // echo "<br>".$push_type;


                $updatefilename = basename($_FILES["notification_image"]["name"]);

                $target_dir = "asset/notification";
                $target_file = $target_dir."/".$updatefilename;

              // echo "<br>video tmp : ".$_FILES["notification_image"]["tmp_name"];
              // echo "<br>TargetFile : ".$target_file;


                $uploadOk = 1;
                $filedetailadded=0;

                $notificationFileType = pathinfo($target_file,PATHINFO_EXTENSION);

              //  echo $notificationFileType;
                // Check file size
                if ($_FILES["notification_image"]["size"] > 50000)
                {
                  echo "<br>Sorry, your image is too large. Make sure its less than 50 KB";
                  $uploadOk = 0;
                }

               // echo "<br>type=".$videoFileType;
                // Allow certain file formats
                if($notificationFileType != "jpg" && $notificationFileType != "png" && $notificationFileType != "jpeg" )
                {
                  echo "<br>Sorry, only jpg,png,jpeg files are allowed.";
                  $uploadOk = 0;
                }

                //echo "ni=".$_FILES["notification_image"]["tmp_name"];
                //echo "tf=".$target_file;
                if ($uploadOk == 0)
                 {
                   echo "<br>Sorry, your file was not uploaded.";
                   // if everything is ok, try to upload file
                 }
                 elseif (move_uploaded_file($_FILES["notification_image"]["tmp_name"], $target_file))
                 {


                 }
               else
                    {
                      echo "<br>Sorry, there was an error uploading your file.[check folder permission]";
                      $target_file="null";
                    }




                    // logic to generate Id
                     $sqlspcl1 = "SELECT max(nId) as a FROM notification";
                     $resultspcl1 = $conn->query($sqlspcl1);
                     if ($resultspcl1->num_rows > 0)
                     {
                         $row1 = $resultspcl1->fetch_assoc();
                         $nId=$row1["a"]+1;
                     }
                     else
                     {
                         $nId=1;
                     }

                    $sql1="INSERT INTO notification(nId,nTitle,nCat,nDesc,nImage,nURL,dateOfCreation,lastModifiedDate) VALUES('$nId','$title','$category','$message','$target_file','$url',now(),now())";

                     if($conn->query($sql1)=== TRUE)
                     {
                       $sqlspcl1 = "SELECT dateOfCreation FROM notification where nId=".$nId;
                       $resultspcl1 = $conn->query($sqlspcl1);
                       if ($resultspcl1->num_rows > 0)
                       {
                           $row1 = $resultspcl1->fetch_assoc();
                                    $time=date("d/m/Y", strtotime($row1["dateOfCreation"]));
                                    //  $time=date('m/d/Y', $row1["dateOfCreation"]);

                       }
                       else
                       {
                           $time="";
                       }


                       if($regId=="all")
                       {
                         $mysqlquery="SELECT firebaseId FROM userprofile where isDelete=0 and isDisabled=0";
                         $mysqlresult=$conn->query($mysqlquery);
                         if($mysqlresult->num_rows>0)
                         {
                          while($myrow = $mysqlresult->fetch_assoc())
                          {
                           // echo "<br>".$myrow['firebaseId'];
                           $fcmId=$myrow['firebaseId'];
                           $response = sendNotification($push_type,$fcmId,$title,$category,$message,$url,$target_file,$time);
                          }
                         }
                       }
                       else {
                       $response = sendNotification($push_type,$regId,$title,$category,$message,$url,$target_file,$time);
                       }

                         //  echo "<br>Added notification in db.";
                     }
                     else
                     {
                    //				 echo "<br>Error inserting record into db.";
                              echo "<br>Error: " . $sql1 . "<br>" . $conn->error;
                     }

              }



              function sendNotification($push_type,$regId,$title,$cat,$message,$url,$image,$time){
                // Enabling error reporting
                error_reporting(-1);
                ini_set('display_errors', 'On');

                require_once __DIR__ . '/firebase/firebase.php';
                require_once __DIR__ . '/firebase/push.php';

                $firebase = new Firebase();
                $push = new Push();

                // optional payload
                $payload = array();
                $payload['cat'] = $cat;
                $payload['url'] = $url;
                $payload['timestamp'] = $time;

                $push->setTitle($title);
                $push->setMessage($message);
                if (isset($image)) {
                    $push->setImage($image);
                } else {
                    $push->setImage('');
                }

                $push->setIsBackground(FALSE);
                $push->setPayload($payload);


                $json = '';
                $response = '';

                if ($push_type == 'topic') {
                    $json = $push->getPush();
                    $response = $firebase->sendToTopic('global', $json);
                } else if ($push_type == 'individual') {
                    $json = $push->getPush();
                //    $regId = isset($_POST['regId']) ? $_POST['regId'] : '';
                  //   $registration_ids=array('dmWg9IIwAPw:APA91bFvbwtZdepceyohPXlAQaby3pfRXC2EqESxGP6h3A4B4V70KydZkLOM7F6V5vHw8YVlTFzx4338gRZELoTVMJ55W3H3Ug-Rn-iTomDI84kydNzP1d-stMAPrliaroOmev50ytu9', 'fFNTQj122VI:APA91bHlD2gduJKucWaYa8USFchLv9_TuZkXmJyllkuYuF3bXr01R4KHulIeRGMLO1Lf0hi2B8rtzvlXABuJ1dJHVn8C0iVLR_pIh4ogL84Q3TPau-vP7WjmJEzlATaEa__Mi1K7WsmO');
                  //   $response = $firebase->sendMultiple($registration_ids, $json);
                  $response = $firebase->send($regId, $json);
                }
              return $response;
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


                              <label for="redId">Firebase Reg Id</label>

                             <select  class="pure-input-1-2" id="regId" name="regId" >
                                 <option value="notallowed" disabled selected="selected">Select User</option>
                                              <option value='all'>ALL</option>
                                             <?php
                                                 $mysqlquery="SELECT fName, firebaseId FROM userprofile WHERE isDelete=0 and isDisabled=0";
                                                 $mysqlresult=$conn->query($mysqlquery);
                                                 if($mysqlresult->num_rows>0)
                                                 {
                                                  while($myrow = $mysqlresult->fetch_assoc())
                                                  {
                                                    echo "<option value='".$myrow['firebaseId']."'>".$myrow['fName']."</option>";
                                                  }
                                                 }
                                                 ?>

                             </select>

                              <!-- <input type="text" id="redId" name="regId" class="pure-input-1-2" placeholder="Enter firebase registration id"> -->

                              <label for="redId">Category</label>

                             <select  class="pure-input-1-2" id="category" name="category" >
                                 <option value="notallowed" disabled selected="selected">Select Category</option>
                                   <?php
                                       $mysqlquery="SELECT catId, catName FROM notificationcategory";
                                       $mysqlresult=$conn->query($mysqlquery);
                                       if($mysqlresult->num_rows>0)
                                       {
                                         while($myrow = $mysqlresult->fetch_assoc())
                                          {
                                            echo "<option value='".$myrow['catId']."'>".$myrow['catName']."</option>";
                                          }
                                       }
                                    ?>

                             </select>

                              <label for="title">Title</label>
                              <input type="text" id="title" name="title" class="pure-input-1-2" placeholder="Enter title">

                              <label for="message">Message</label>
                              <textarea class="pure-input-1-2" rows="5" name="message" id="message" placeholder="Notification message!"></textarea>

                              <label for="url">URL (If any)</label>
                              <input type="text" id="url" name="url" class="pure-input-1-2" placeholder="Enter url">


                              <label for="notification_image"> Upload Image (If any)</label>
                              <input type="file" name="notification_image" >  <!-- <input name="include_image" id="include_image" type="checkbox"> Include image -->

                              <input type="hidden" name="push_type" value="individual"/>
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
    apiKey: "AIzaSyDu_-OkN-imIF8l5SU_FGS3NTVq0-S0qv4",
    authDomain: "csat1-57678.firebaseapp.com",
    databaseURL: "https://csat1-57678.firebaseio.com",
    projectId: "csat1-57678",
    storageBucket: "",
    messagingSenderId: "839708584541"
  };
  firebase.initializeApp(config);
</script>
