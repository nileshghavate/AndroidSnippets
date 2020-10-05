<?php

session_start();
if(!isset($_SESSION['sdcid'])){
   header("Location:login.php");
}

// if(!isset($_SESSION['userId'])){
//    header("Location:login.php");
// }

session_destroy();

	header("Location: index.php");
exit();
?>
