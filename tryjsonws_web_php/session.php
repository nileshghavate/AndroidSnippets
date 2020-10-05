<?php
session_start();
/*
if(!isset($_SESSION['userId'])){
   header("Location:login.php");
}
*/

echo print_r($_SESSION);

?>