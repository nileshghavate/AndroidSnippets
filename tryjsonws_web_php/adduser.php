
<?php include 'header.php';?>

<!DOCTYPE html>
<head lang="en">
  <title>User</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  
  <link href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
  <script src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
  <script src="asset/js/bootbox.min.js"></script>

</head>
<body>

<div class="container-fluid col-md-12" >
<div class="row">
<?php include 'sidebar.php';?>
<?php require 'connect.php';?>



<div class="container">
<div id="adduser" style=" margin-top:50px" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
<div class="panel panel-info">
<div class="panel-heading">
<div class="panel-title"> Create Users </div>
</div>
<div class="panel-body" >
<form  class="form-horizontal" action="" method="post" >
      <div id="div_id_name" class="form-group required">
          <label for="id_name" class="control-label col-md-4  requiredField"> Name  <span class="asteriskField">*</span> </label>
          <div class="controls col-md-8 ">
              <input class="input-md textinput form-control" id="id_name" maxlength="30" name="name" required placeholder="Your Name" style="margin-bottom: 10px" type="text" />
          </div>
      </div>

      <div id="div_id_email" class="form-group required">
          <label for="id_email" class="control-label col-md-4  requiredField"> email  <span class="asteriskField">*</span> </label>
          <div class="controls col-md-8 ">
              <input class="input-md textinput form-control" id="id_email" maxlength="30" name="email" required placeholder="Your Last Name" style="margin-bottom: 10px" type="text" />
          </div>
      </div>

      <div id="div_id_gender" class="form-group required">
          <label for="id_gender" class="control-label col-md-4  requiredField"> Gender  </label>
          <div class="controls col-md-8 ">
              <input type="radio" id="male" name="gender" value="1">
              <label for="male">Male</label><br>
              <input type="radio" id="female" name="gender" value="2">
              <label for="female">Female</label><br>
              <input type="radio" id="other" name="gender" value="3">
              <label for="other">Other</label> 
          </div>
      </div>


      <div id="div_id_mobile" class="form-group required">
          <label for="id_mobile" class="control-label col-md-4  requiredField"> Mobile No. <span class="asteriskField">*</span> </label>
          <div class="controls col-md-8 ">
              <input class="input-md textinput form-control" id="id_mobile" maxlength="10" name="mobile" required placeholder="Enter Mobile Contact " style="margin-bottom: 10px" pattern="[0-9]{10}" type="text" />
          </div>
      </div>

      <div id="div_id_home" class="form-group required">
          <label for="id_home" class="control-label col-md-4  requiredField"> Home No. <span class="asteriskField">*</span> </label>
          <div class="controls col-md-8 ">
              <input class="input-md textinput form-control" id="id_home" maxlength="10" name="home" required placeholder="Enter Home Contact " style="margin-bottom: 10px" pattern="[0-9]{10}" type="text" />
          </div>
      </div>

      <div id="div_id_office" class="form-group required">
          <label for="id_office" class="control-label col-md-4  requiredField"> Office No. <span class="asteriskField">*</span> </label>
          <div class="controls col-md-8 ">
              <input class="input-md textinput form-control" id="id_office" maxlength="10" name="office" required placeholder="Enter Office Contact " style="margin-bottom: 10px" pattern="[0-9]{10}" type="text" />
          </div>
      </div>

      <div class="form-group">
          <div class="aab controls col-md-4 "></div>
          <div class="controls col-md-8 ">
              <input type="submit" name="submit" value="Submit" class="btn btn-primary btn btn-info" id="submit-id-submit" />
          </div>
      </div>
</form>

               

<?php
    if(isset($_POST['submit']))
    {
      global $logts;
      $name=$_POST["name"];
      $email=$_POST["email"];
      $gender=$_POST["gender"];
      $home=$_POST["home"];
      $mobile=$_POST["mobile"];
      $office=$_POST["office"];
      
    //  echo $gender;
        try{

            $stmt = $conn->prepare('INSERT INTO user_detail (name, email, gender)
            VALUES (:name, :email, :gender)');

          
            $stmt->bindParam(':name', $name);
            $stmt->bindParam(':email', $email);
            $stmt->bindParam(':gender', $gender);



            if($stmt->execute())
              {
                $user_id = $conn->lastInsertId();
                
                $stmt_1 = $conn->prepare('INSERT INTO user_contact (user_id ,mobile, home, office)
            VALUES (:user_id, :mobile, :home, :office)');

            $stmt_1->bindParam(':user_id', $user_id);
            $stmt_1->bindParam(':mobile', $mobile);
            $stmt_1->bindParam(':home', $home);
            $stmt_1->bindParam(':office', $office);      

            $stmt_1->execute();
            echo "User registered Successfully !!!";
             }
             else
             {
                  echo "Unable to register User!!";
             }


          }
          catch(PDOException $e) {
          echo $e->getMessage();
        }
    
   

        }


    ?>

</div>
</div>
</div>
</div>





</div>


</div>
</body>

