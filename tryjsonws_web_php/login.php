<?php
session_start();
?>
<!DOCTYPE html>
<head lang="en">
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel = "stylesheet" type = "text/css" href = "login.css">      <!-- Stylesheet only for login form -->
        <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

</head>

<body padding-top: 80px>   <!-- This inline css is for fixed to top type header -->

<div class="container"
 >   <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to <br><font color="red">Test</font></h1>
            <div class="account-wall">
                <img class="profile-img" src="images/logo/logo_small.jpg" alt=" logo">


                    <h6 class="text-center" style="color: red">
                        <?php

                            if($_GET["status"]!="")
                                echo $_GET["status"];

                        ?>

                    </h6>

                <form class="form-signin" name="loginform" action="authenticate.php" method="POST">
                <!-- <input type="hidden" name="redirurl" value="<?php echo $_SERVER['HTTP_REFERER']; ?>" > -->
                <input type="text" class="form-control" name="user" placeholder="Username" required autofocus><br>
                <input type="password" class="form-control" name="pass" placeholder="Password" required>
                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Sign in</button>

                <a href="forgotpwd/request_reset.php" class="pull-right need-help">Forgot Password? </a><span class="clearfix"></span>
                </form>
            </div>
        </div>
    </div>
</div>

</body>

<?php include 'footer.php';?>
