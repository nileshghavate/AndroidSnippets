<?php
ob_start();
session_start();

//require "connect.php";



$u=$_POST["user"];
$p=$_POST["pass"];

if("Nilesh"==$p)
  	{
      $id=session_id();
      $_SESSION["sdcid"] = $id;
   		$_SESSION["logged_in"] = 1;
      header('Location: dashboard.php');
  		exit();
 	  }
	else
	  {
  		header("Location: login.php?status=Invalid Username or Password");
      ob_end_flush();
    }

//$conn->close();


?>
