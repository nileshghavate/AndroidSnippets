<?php
session_start();

require 'connect.php';
require 'util.php';
//echo "ID=".$_POST["id_user"];

 if(isset($_POST["mid"]))
 {
      $output = '';

     $sql3 = "SELECT mTitle ,mStartDate ,mEndDate  FROM mcqtest WHERE isDelete=0 and mId=".$_POST["mid"];
     $result3=$conn->query($sql3);
      	if ($result3)
      	{

          $row=$result3->fetch_assoc();
           $message="Title : ".$row["mTitle"]."<br> Start Date:".$row["mStartDate"]."<br> End Date:".$row["mEndDate"];
            $dddd = date('Y-m-d');
           $res=notifytoall("501","Mock Test",$message,null,null,$dddd);
		       $output = "Notified Students successfully";
          echo $output;

		} else {
		    $output = "Error notifying studetns..Try later !!! ";
        echo $output;
		}
      //echo $output;
 }
 ?>
