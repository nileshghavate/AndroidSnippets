<?php

ob_start();
session_start();
?>
<style>
    .navbar-default .navbar-nav > li > a:hover, .navbar-default .navbar-nav > li > a:focus
    {
      background-color:#e6e6e6 ;
      color:#000000 ;
    }
</style>
<nav class="navbar navbar-default ">   <!--navbar-fixed-top -->
  <div class="container-fluid">    <!-- style="background-image: url('images/bhcimage.jpg'); -->
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">

      <a href="#" class="navbar-brand" rel="home" title=""><img  src="images/logo/logo.jpg" alt="Logo"/></a></center>
    </div>
     <div class="page-header"><br> <br><br></div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="navbar1">


      <?php
      if(isset($_SESSION["logged_in"]) && $_SESSION["logged_in"] =="1")
      {

      ?>

      <ul class="nav navbar-nav">
        <li><a href="#"> </a></li>
        <li><a href="#"> </a></li>
        <li><a href="index.php">Home<span class="sr-only">(current)</span></a></li>
        <li><a href="aboutus.php">About Us</a></li>
        <li><a href="contactus.php">Contact Us</a></li>
      </ul>


        <div class="nav navbar-nav navbar-right">
            <a  href="logout.php" >Logout</a>

            <!-- <a   class="btn btn-default logout-user" data-toggle="modal" data-target="#logout-moda">Logout</a> -->
        </div>


      <?php
      }
      else
      {
      ?>
        <div class="nav navbar-nav navbar-right">
          <!--  <a  class="btn btn-default" data-toggle="modal" data-target="#login-modal">Login</a>  -->

         <a  class="btn btn-default" href="login.php">Login</a>

        </div>
     <?php
      }
      ?>


    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>


  <div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
        <div class="modal-dialog">
        <div class="loginmodal-container">
          <h1>Login to Your Account</h1><br>
          <form name="loginform" action="authenticate.php" method="POST">
          <input type="text" name="user" placeholder="Username">
          <input type="password" name="pass" placeholder="Password">
          <input type="submit" name="login" class="login loginmodal-submit" value="Login">
          </form>
          <div class="login-help">
          <a href="#">Register</a> - <a href="#">Forgot Password</a>
          </div>
        </div>
      </div>
</div>

<div class="modal bs-example-modal-sm" id="logout-modal" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-header"><h4>Logout <i class="glyphicon glyphicon-lock"></i></h4></div>
      <div class="modal-body"><i class="fa fa-question-circle"></i>  You have logged out successfully !!! </div>

      <div class="modal-footer"><a href="index.php" class="btn btn-primary btn-block">Close</a></div>
    </div>
  </div>
</div>
<script>

  $('.logout-user').click(function(e)
    {
        e.preventDefault();

       // alert("a");
        bootbox.dialog(
        {
        message: "<div class='alert alert-danger'><span class='glyphicon glyphicon glyphicon-log-out'></span> Are you sure you want to Logout?</div>",
        title: "<i class='glyphicon glyphicon-log-out'></i> Confirm Logout !!!",
        buttons:
        {
          success:
          {
            label: "No",
            className: "btn-success",
            callback: function()
            {
              $('.bootbox').modal('hide');
            }
          },

          danger:
          {
            label: "Logout",
            className: "btn-danger",
            callback: function()
            {

              $.ajax(
              {
              type: 'POST',
              url: 'logout.php',
             })

              .done(function(response)
              {

               window.location = "index.php";
              })

              .fail(function()
              {
                bootbox.alert('Error....');
              })

            }

          }
        }
        });
    });

    </script>

<!DOCTYPE html>
<head lang="en">
	<title>Home</title>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
			<link rel="icon" href="images/logo/logo_small.jpg">
	   	<link rel = "stylesheet" type = "text/css" href = "loginform.css">		<!-- Stylesheet only for login form -->
    	<link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    	<script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
    	<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="row">
			 <?php include 'notices.php';?>
			 <?php include 'sidebar.php';?>
	

	</div>

	<div class="panel panel-default">
  <div class="panel-body" style="padding: 5px"> TEST</div>
</div>
	<p style="padding:5px">
	</p>

</body>


