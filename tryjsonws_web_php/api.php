<?php
//ini_set('display_errors', 1);
//ini_set('display_startup_errors', 1);
//error_reporting(E_ALL);


require "connect.php";


// //header("Content-Type: application/json; charset=UTF-8");

// $json = file_get_contents('php://input');
// $data = json_decode($json);


// date_default_timezone_set('Asia/Kolkata');
// $logts = date('Y-m-d H:i:s');
// $conn = $conn;

// // For POST with JSON input
// global $k;
// $k = $_SERVER['HTTP_AUTHORIZATION'];
// $app_authorization="Basic UmVjaGFyZ2U6UmVjaGFyZ2Ux";

// if($k == $app_authorization)
// {

//   if($data->method=="registeruser")
//   {
//     $json = registeruser($conn,$data);
//   }
//   elseif ($data->method=="listuser")
//   {
//     $json = listuser($conn,$data);
//   }
//   else
//     $json = array("data" => "", "success" => "False", "message" => "Method Not Found");
// } 
// else {
  
//   $json = array("data" => "", "success" => "False", "message" => "Key MisMatch");
// }


listuser($conn);

 function listuser($conn){




      $select_stmt = $conn->prepare("SELECT ud.user_id as user_id, name , email, gender, mobile, home, office FROM user_detail as ud, user_contact as uc where ud.user_id = uc.user_id");
      $select_stmt->execute(); 

      if($select_stmt-> rowCount() > 0){
  
      $user_list = array();
      
      while($row = $select_stmt->fetch())
      {
        $contact_list = array("mobile" => $row["mobile"], "home" => $row["home"],"office" => $row["office"]);

        if($row["gender"]==1)
        {
          $g = "Male";
        }
        elseif($row["gender"]==2)
        {
          $g = "Female";
        }
        else
        {
          $g = "Other";
        }
        array_push($user_list,["id" => $row["user_id"], "name" => $row["name"], "email" => $row["email"], "gender" => $g, "contact" => $contact_list]);
      }
      $list = $user_list;
      $json = array("users" =>$list);
      }
      else
      {
        $json = array("users" =>"not found");
      }
//$conn->close();
//header('Content-type: application/json');
echo json_encode($json);

}


// // /* Output header */
// header('Content-type: application/json');
//  echo json_encode($json);

?>
